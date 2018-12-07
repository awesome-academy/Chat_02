package com.framgia.f_talk.screen.splash;

import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {
    @Provides
    SplashViewModel provideSplashViewModel(RepositoryManager repositoryManager,
                                           SchedulerProvider schedulerProvider) {
        return new SplashViewModel(repositoryManager, schedulerProvider);
    }
}
