package com.framgia.f_talk.screen.welcome;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

public class WelcomeViewModel extends BaseViewModel<WelcomeNavigator> {
    public WelcomeViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }

    public void onGetStartedClick() {
        getNavigator().openSignUpActivity();
    }

    public void onLoginClick() {
        getNavigator().openLoginActivity();
    }
}
