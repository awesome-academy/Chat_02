package com.framgia.f_talk.screen.chat;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.data.model.Message;
import com.framgia.f_talk.data.model.User;
import com.framgia.f_talk.data.source.remote.firebase.EventType;
import com.framgia.f_talk.util.Constant;
import com.framgia.f_talk.util.StringUtil;
import com.framgia.f_talk.util.rx.SchedulerProvider;
import com.google.firebase.database.FirebaseDatabase;

public class ChatViewModel extends BaseViewModel<ChatNavigator> {

    private final ObservableList<ChatItemViewModel> mChatItemViewModels
            = new ObservableArrayList<>();
    private ObservableField<String> mFriendName = new ObservableField<>();
    private ObservableField<String> mFriendAvatarUrl = new ObservableField<>();

    public ChatViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }

    public void fetchData(FirebaseDatabase firebaseDatabase, String userId, String friendId) {
        fetchFriendAvatarUrl(firebaseDatabase, friendId);
        fetchMessage(firebaseDatabase, userId, friendId);
    }

    public void sendMessage(FirebaseDatabase firebaseDatabase, Message message) {
        getCompositeDisposable().add(getRepositoryManager().setValue(firebaseDatabase.getReference()
                .child(Constant.PRIVATE_MESSAGE_DIR).child(StringUtil
                        .makePrivateChatDir(message.getSenderId(), message.getReceiverId()))
                .push(), message)
                .subscribe(() -> getNavigator().onSendMessageSuccess(),
                        throwable -> getNavigator().onSendMessageError()));
    }


    public void onSendButtonClick() {
        getNavigator().getMessage();
    }

    public void onAddImageClick() {

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
        getCompositeDisposable().add(getRepositoryManager().observeChildEvent(
                firebaseDatabase.getReference().child(Constant.PRIVATE_MESSAGE_DIR)
                        .child(StringUtil.makePrivateChatDir(userId, friendId)))
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
        getCompositeDisposable().add(getRepositoryManager().observeSingleValueEvent(
                firebaseDatabase.getReference().child(Constant.USER_DATABASE_DIR)
                        .child(friendId), User.class
        ).subscribe(user -> mFriendAvatarUrl.set(user.getAvatarUrl())));
    }

}
