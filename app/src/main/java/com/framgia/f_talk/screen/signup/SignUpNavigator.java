package com.framgia.f_talk.screen.signup;

public interface SignUpNavigator {

    void provideNameAndEmail();

    void onFullNameInvalid();

    void onEmailInvalid();

    void moveToPasswordActivity();
}
