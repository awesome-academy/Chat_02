package com.framgia.f_talk.screen.splash;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;
import com.google.firebase.auth.FirebaseAuth;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {
    public SplashViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }

    public void decideNextActivity() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            getNavigator().openWelcomeActivity();
        else getNavigator().openMainActivity();
    }
}
