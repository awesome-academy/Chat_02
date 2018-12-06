package com.framgia.f_talk.screen.login;

import android.os.Bundle;

import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.R;
import com.google.android.gms.common.SignInButton;

public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SignInButton signInButton = findViewById(R.id.button_google_sign_in);
        signInButton.setSize(SignInButton.SIZE_WIDE);
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }
}
