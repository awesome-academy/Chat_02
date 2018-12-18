package com.framgia.f_talk.screen.home.recent;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

public class RecentViewModel extends BaseViewModel<RecentNavigator> {
    public RecentViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }
}
