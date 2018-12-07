package com.framgia.f_talk.screen.signup;

import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpActivityModule {
    @Provides
    SignUpViewModel provideSignUpViewModel(RepositoryManager repositoryManager,
                                           SchedulerProvider schedulerProvider) {
        return new SignUpViewModel(repositoryManager, schedulerProvider);
    }
}
