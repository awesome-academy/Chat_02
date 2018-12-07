package com.framgia.f_talk.screen.signup;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

public class SignUpViewModel extends BaseViewModel<SignUpNavigator> {
    public SignUpViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }
}
