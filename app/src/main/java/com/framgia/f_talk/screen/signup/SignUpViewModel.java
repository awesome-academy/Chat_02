package com.framgia.f_talk.screen.signup;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.StringUtil;
import com.framgia.f_talk.util.rx.SchedulerProvider;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpViewModel extends BaseViewModel<SignUpNavigator> {
    public SignUpViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }

    public void onNextClick() {
        getNavigator().provideNameAndEmail();
    }

    public void setUserData(String fullName, String email) {
        if (!StringUtil.isFullNameValid(fullName)) {
            getNavigator().onFullNameInvalid();
            return;
        }
        if (!StringUtil.isEmailValid(email)) {
            getNavigator().onEmailInvalid();
            return;
        }
        getNavigator().moveToCreateAccountActivity();
    }

    public void onGoogleSignUpClick() {
        getNavigator().signUpWithGoogle();
    }

    public void signUpWithGoogle(FirebaseAuth firebaseAuth, AuthCredential credential) {
        getCompositeDisposable().add(getRepositoryManager()
                .signInWithCredential(firebaseAuth, credential)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(authResult -> {
                            if (authResult.getUser() != null)
                                getNavigator().onSignUpGoogleSuccess();
                        },
                        throwable -> getNavigator().onSignUpFailure(),
                        () -> getNavigator().onSignUpFailure()));
    }

    public void signUpWithFacebook(FirebaseAuth firebaseAuth, AuthCredential credential) {
        getCompositeDisposable().add(getRepositoryManager()
                .signInWithCredential(firebaseAuth, credential)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(authResult -> {
                            if (authResult.getUser() != null)
                                getNavigator().onSignUpFacebookSuccess();
                        },
                        throwable -> getNavigator().onSignUpFailure(),
                        () -> getNavigator().onSignUpFailure()));
    }
}
