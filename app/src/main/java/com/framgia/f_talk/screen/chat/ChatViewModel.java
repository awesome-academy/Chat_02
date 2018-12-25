package com.framgia.f_talk.screen.chat;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.net.Uri;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.data.model.Group;
import com.framgia.f_talk.data.model.Message;
import com.framgia.f_talk.data.model.User;
import com.framgia.f_talk.data.source.remote.firebase.EventType;
import com.framgia.f_talk.util.Constant;
import com.framgia.f_talk.util.StringUtil;
import com.framgia.f_talk.util.rx.SchedulerProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

import io.reactivex.Maybe;
import io.reactivex.functions.Function;

public class ChatViewModel extends BaseViewModel<ChatNavigator> {

    private final ObservableList<ChatItemViewModel> mChatItemViewModels
            = new ObservableArrayList<>();
    private ObservableField<String> mFriendName = new ObservableField<>();
    private ObservableField<String> mFriendAvatarUrl = new ObservableField<>();
    private boolean mIsGroupMessage;

    public ChatViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }

    public void fetchData(FirebaseDatabase firebaseDatabase, String userId, String friendId) {
        mIsGroupMessage = friendId.length() > userId.length();
        fetchFriendAvatarUrl(firebaseDatabase, friendId);
        fetchMessage(firebaseDatabase, userId, friendId);
    }

    public void sendMessage(FirebaseDatabase firebaseDatabase, Message message) {
        DatabaseReference databaseReference;
        if (mIsGroupMessage) {
            databaseReference = firebaseDatabase.getReference().child(Constant.GROUP_MESSAGE_DIR)
                    .child(message.getReceiverId());
        } else {
            databaseReference = firebaseDatabase.getReference().child(Constant.PRIVATE_MESSAGE_DIR)
                    .child(StringUtil.makePrivateChatDir(message.getSenderId(),
                            message.getReceiverId()));
        }
        getCompositeDisposable().add(getRepositoryManager().setValue(databaseReference
                .push(), message)
                .subscribe(() -> getNavigator().onSendMessageSuccess(),
                        throwable -> getNavigator().onSendMessageError()));
    }

    public void uploadImageToStorage(FirebaseStorage firebaseStorage, String userId, Uri image) {
        long timeStamp = Calendar.getInstance().getTimeInMillis();
        StorageReference storageReference = firebaseStorage.getReference()
                .child(Constant.IMAGE_STORAGE_DIR)
                .child(userId)
                .child(String.valueOf(timeStamp));
        getCompositeDisposable().add(getRepositoryManager().putFile(storageReference, image)
                .filter(taskSnapshot -> taskSnapshot.getTask().isSuccessful())
                .flatMap((Function<UploadTask.TaskSnapshot, Maybe<Uri>>)
                        taskSnapshot -> getRepositoryManager().getDownloadUrl(storageReference))
                .subscribe(uri -> getNavigator().onUploadImageSuccess(uri)));
    }


    public void onSendButtonClick() {
        getNavigator().getMessage();
    }

    public void onAddImageClick() {
        getNavigator().chooseImage();
    }

    public void onBackButtonClick() {
        getNavigator().onBack();
    }

    public ObservableList<ChatItemViewModel> getChatItemViewModels() {
        return mChatItemViewModels;
    }

    public void setFriendName(String friendName) {
        mFriendName.set(friendName);
    }

    public ObservableField<String> getFriendName() {
        return mFriendName;
    }

    public ObservableField<String> getFriendAvatarUrl() {
        return mFriendAvatarUrl;
    }

    private void fetchMessage(FirebaseDatabase firebaseDatabase, String userId, String friendId) {
        DatabaseReference databaseReference;
        if (mIsGroupMessage) {
            databaseReference = firebaseDatabase.getReference()
                    .child(Constant.GROUP_MESSAGE_DIR).child(friendId);
        } else {
            databaseReference = firebaseDatabase.getReference().child(Constant.PRIVATE_MESSAGE_DIR)
                    .child(StringUtil.makePrivateChatDir(userId, friendId));
        }
        getCompositeDisposable().add(getRepositoryManager().observeChildEvent(databaseReference)
                .subscribe(dataSnapshotFirebaseChildEvent -> {
                    if (dataSnapshotFirebaseChildEvent.getEventType() == EventType.ADDED) {
                        Message message = dataSnapshotFirebaseChildEvent.getValue()
                                .getValue(Message.class);
                        ChatItemViewModel chatItemViewModel =
                                new ChatItemViewModel(message.getContent(),
                                        StringUtil.makeTimeStamp(message.getTimeStamp()),
                                        message.getSenderId(), message.getMessageContentType());
                        mChatItemViewModels.add(chatItemViewModel);
                        getNavigator().scrollTo(mChatItemViewModels.size() - 1);
                    }
                }));
    }

    private void fetchFriendAvatarUrl(FirebaseDatabase firebaseDatabase, String friendId) {
        if (mIsGroupMessage) {
            getCompositeDisposable().add(getRepositoryManager().observeSingleValueEvent(
                    firebaseDatabase.getReference().child(Constant.GROUP_DATABASE_DIR)
                            .child(friendId), Group.class
            ).subscribe(group -> mFriendAvatarUrl.set(group.getGroupAvatar())));
        } else {
            getCompositeDisposable().add(getRepositoryManager().observeSingleValueEvent(
                    firebaseDatabase.getReference().child(Constant.USER_DATABASE_DIR)
                            .child(friendId), User.class
            ).subscribe(user -> mFriendAvatarUrl.set(user.getAvatarUrl())));
        }
    }

}
