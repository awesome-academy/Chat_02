package com.framgia.f_talk.screen.createaccount;

public interface CreateAccountNavigator {

    void createAccountWithEmailAndPassWord();

    void onCreateAccountSuccess();

    void onCreateAccountFailure();

    void onPasswordLengthInvalid();

    void onPasswordNotMatch();

    void onPasswordValid();

    void onCreateUserInfoSuccess();

    void onCreateUserInfoFailure();
}
