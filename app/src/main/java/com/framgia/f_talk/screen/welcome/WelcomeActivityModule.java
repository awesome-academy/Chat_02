package com.framgia.f_talk.screen.welcome;

import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class WelcomeActivityModule {
    @Provides
    WelcomeViewModel provideWelcomeViewModel(RepositoryManager repositoryManager,
                                             SchedulerProvider schedulerProvider) {
        return new WelcomeViewModel(repositoryManager, schedulerProvider);
    }
}
