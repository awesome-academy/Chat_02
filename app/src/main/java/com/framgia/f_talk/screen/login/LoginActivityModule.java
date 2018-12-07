package com.framgia.f_talk.screen.login;

import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {
    @Provides
    LoginViewModel provideLoginViewModel(RepositoryManager repositoryManager,
                                         SchedulerProvider schedulerProvider) {
        return new LoginViewModel(repositoryManager, schedulerProvider);
    }
}
