package com.framgia.f_talk.screen.signup;

public interface SignUpNavigator {

    void provideNameAndEmail();

    void signUpWithGoogle();

    void onSignUpGoogleSuccess();

    void onSignUpFacebookSuccess();

    void onSignUpFailure();

    void onFullNameInvalid();

    void onEmailInvalid();

    void moveToCreateAccountActivity();

    void onCreateUserInfoSuccess();

    void onCreateUserInfoFailure();
}
