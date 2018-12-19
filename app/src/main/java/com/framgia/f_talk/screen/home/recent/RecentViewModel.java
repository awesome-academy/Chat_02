package com.framgia.f_talk.screen.home.recent;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.data.model.Message;
import com.framgia.f_talk.data.model.User;
import com.framgia.f_talk.data.source.remote.firebase.FirebaseChildEvent;
import com.framgia.f_talk.util.Constant;
import com.framgia.f_talk.util.StringUtil;
import com.framgia.f_talk.util.rx.SchedulerProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;


public class RecentViewModel extends BaseViewModel<RecentNavigator> {
    private final List<Message> mMessageList = new ObservableArrayList<>();
    private final List<User> mUsers = new ArrayList<>();
    private final ObservableList<RecentItemViewModel> mRecentItemViewModels
            = new ObservableArrayList<>();

    public RecentViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }

    public void fetchRecentMessage(FirebaseDatabase firebaseDatabase, FirebaseAuth firebaseAuth) {
        getCompositeDisposable().add(getRepositoryManager().observeChildEvent(
                firebaseDatabase.getReference().child(Constant.PRIVATE_MESSAGE_DIR))
                .filter(dataSnapshotFirebaseChildEvent
                        -> dataSnapshotFirebaseChildEvent.getKey().contains(firebaseAuth.getUid()))
                .flatMap((Function<FirebaseChildEvent<DataSnapshot>,
                        Flowable<FirebaseChildEvent<DataSnapshot>>>) dataSnapshotFirebaseChildEvent
                        -> getRepositoryManager().observeChildEvent(dataSnapshotFirebaseChildEvent
                        .getValue().getRef().orderByKey().limitToLast(1)))
                .flatMap((Function<FirebaseChildEvent<DataSnapshot>, Flowable<Message>>)
                        dataSnapshotFirebaseChildEvent -> {
                            Message message =
                                    dataSnapshotFirebaseChildEvent.getValue()
                                            .getValue(Message.class);
                            if (!mMessageList.contains(message)) mMessageList.add(message);
                            return Flowable.just(message);
                        })
                .flatMap((Function<Message, Flowable<String>>) message -> {
                    String receiverId = message.getSenderId().equals(firebaseAuth.getUid()) ?
                            message.getReceiverId() : message.getSenderId();
                    return Flowable.just(receiverId);
                })
                .flatMap((Function<String, Flowable<DataSnapshot>>) s ->
                        getRepositoryManager().observeValueEvent(firebaseDatabase.getReference()
                                .child(Constant.USER_DATABASE_DIR).child(s)))
                .flatMap((Function<DataSnapshot, Flowable<User>>) dataSnapshot -> {
                    User user = dataSnapshot.getValue(User.class);
                    if (!mUsers.contains(user)) mUsers.add(user);
                    return Flowable.just(user);
                })
                .flatMap((Function<User, Flowable<RecentItemViewModel>>) user -> {
                    RecentItemViewModel recentItemViewModel = null;
                    for (Message message : mMessageList) {
                        if (user.getUId().equals(message.getSenderId()) ||
                                user.getUId().equals(message.getReceiverId())) {
                            recentItemViewModel = new RecentItemViewModel(user.getFullName(),
                                    user.getAvatarUrl() == null ? Constant.NULL_URL :
                                            user.getAvatarUrl(), message.getContent(),
                                    StringUtil.makeTimeStamp(message.getTimeStamp()),
                                    user.getUId());
                            break;
                        }
                    }
                    return Flowable.just(recentItemViewModel);
                })
                .subscribe(recentItemViewModel -> {
                    if (!mRecentItemViewModels.contains(recentItemViewModel))
                        mRecentItemViewModels.add(recentItemViewModel);
                }));

    }

    public ObservableList<RecentItemViewModel> getRecentItemViewModels() {
        return mRecentItemViewModels;
    }

    public void addRecentItemViewModelToList(ObservableList<RecentItemViewModel> models) {
        mRecentItemViewModels.addAll(models);
    }
}
