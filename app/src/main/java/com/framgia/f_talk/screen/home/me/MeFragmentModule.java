package com.framgia.f_talk.screen.home.me;

import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class MeFragmentModule {
    @Provides
    MeViewModel provideAboutViewModel(RepositoryManager repositoryManager,
                                      SchedulerProvider schedulerProvider) {
        return new MeViewModel(repositoryManager, schedulerProvider);
    }
}
