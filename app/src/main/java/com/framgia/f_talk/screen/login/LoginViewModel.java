package com.framgia.f_talk.screen.login;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.StringUtil;
import com.framgia.f_talk.util.rx.SchedulerProvider;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {
    public LoginViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }

    public void onLoginClick() {
        getNavigator().loginWithEmailAndPassword();
    }

    public void onGoogleLoginClick() {
        getNavigator().loginWithGoogle();
    }

    public void loginWithEmail(FirebaseAuth firebaseAuth, String email, String password) {
        if (!StringUtil.isEmailValid(email)) {
            getNavigator().onEmailInvalid();
            return;
        }
        if (!StringUtil.isPasswordValid(password)) {
            getNavigator().onPasswordInvalid();
            return;
        }
        getCompositeDisposable().add(getRepositoryManager()
                .signInWithEmailAndPassword(firebaseAuth, email, password)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(authResult -> {
                            if (authResult.getUser() != null) getNavigator().onLoginSuccess();
                        }, throwable -> getNavigator().onLoginFailure()
                        , () -> getNavigator().onLoginFailure()
                ));
    }

    public void loginWithGoogle(FirebaseAuth firebaseAuth, AuthCredential credential) {
        getCompositeDisposable().add(getRepositoryManager()
                .signInWithCredential(firebaseAuth, credential)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(authResult -> {
                            if (authResult.getUser() != null) getNavigator().onLoginSuccess();
                        },
                        throwable -> getNavigator().onLoginFailure(),
                        () -> getNavigator().onLoginFailure()));
    }

    public void loginWithFacebook(FirebaseAuth firebaseAuth, AuthCredential credential) {
        getCompositeDisposable().add(getRepositoryManager()
                .signInWithCredential(firebaseAuth, credential)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(authResult -> {
                            if (authResult.getUser() != null) getNavigator().onLoginSuccess();
                        },
                        throwable -> getNavigator().onLoginFailure(),
                        () -> getNavigator().onLoginFailure()));
    }
}
