package com.framgia.f_talk.screen.home;

import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {
    @Provides
    HomeViewModel provideHomeViewModel(RepositoryManager repositoryManager,
                                       SchedulerProvider schedulerProvider) {
        return new HomeViewModel(repositoryManager, schedulerProvider);
    }
}
