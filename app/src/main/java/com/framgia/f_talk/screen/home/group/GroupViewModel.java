package com.framgia.f_talk.screen.home.group;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

public class GroupViewModel extends BaseViewModel<GroupNavigator> {
    public GroupViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }
}
