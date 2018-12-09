package com.framgia.f_talk.screen.login;

public interface LoginNavigator {

    void loginWithEmailAndPassword();

    void loginWithGoogle();

    void onLoginSuccess();

    void onLoginFailure();

    void onEmailInvalid();

    void onPasswordInvalid();
}
