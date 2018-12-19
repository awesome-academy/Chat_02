package com.framgia.f_talk.screen.home.group;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.data.model.Group;
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
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class GroupViewModel extends BaseViewModel<GroupNavigator> {
    private final List<Group> mGroups = new ArrayList<>();
    private final ObservableList<GroupItemViewModel> mGroupItemViewModels
            = new ObservableArrayList<>();

    public GroupViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }

    public void fetchGroupMessage(FirebaseDatabase firebaseDatabase, FirebaseAuth firebaseAuth) {
        getCompositeDisposable().add(getRepositoryManager().observeValueEvent(firebaseDatabase
                .getReference().child(Constant.USER_DATABASE_DIR)
                .child(firebaseAuth.getInstance().getUid()), User.class)
                .flatMap((Function<User, Flowable<String>>) user
                        -> Flowable.fromIterable(user.getGroupIds()))
                .flatMap((Function<String, Flowable<Group>>) s
                        -> getRepositoryManager().observeValueEvent(firebaseDatabase
                        .getReference().child(Constant.GROUP_DATABASE_DIR).child(s), Group.class))
                .flatMap((Function<Group, Flowable<FirebaseChildEvent<DataSnapshot>>>) group -> {
                    if (!mGroups.contains(group)) mGroups.add(group);
                    return getRepositoryManager().observeChildEvent(firebaseDatabase.getReference()
                            .child(Constant.GROUP_MESSAGE_DIR).child(group.getGroupId())
                            .orderByKey().limitToLast(1));
                })
                .flatMap((Function<FirebaseChildEvent<DataSnapshot>, Flowable<Message>>)
                        dataSnapshotFirebaseChildEvent -> {
                            Message message = dataSnapshotFirebaseChildEvent.getValue()
                                    .getValue(Message.class);
                            return Flowable.just(message);
                        })
                .flatMap((Function<Message, Flowable<GroupItemViewModel>>) message -> {
                    GroupItemViewModel groupItemViewModel = null;
                    for (Group group : mGroups) {
                        if (group.getGroupId().equals(message.getReceiverId())) {
                            groupItemViewModel = new GroupItemViewModel(group.getGroupName(),
                                    group.getGroupAvatar(),
                                    message.getContent(),
                                    StringUtil.makeTimeStamp(message.getTimeStamp()),
                                    group.getGroupId(), message.getTimeStamp());
                            break;
                        }
                    }
                    return Flowable.just(groupItemViewModel);
                })
                .subscribe(groupItemViewModel -> {
                    mGroupItemViewModels.remove(groupItemViewModel);
                    mGroupItemViewModels.add(groupItemViewModel);
                    Collections.sort(mGroupItemViewModels);
                }));
    }

    public ObservableList<GroupItemViewModel> getGroupItemViewModels() {
        return mGroupItemViewModels;
    }

    public void addRecentItemViewModelToList(ObservableList<GroupItemViewModel> models) {
        mGroupItemViewModels.addAll(models);
    }
}
