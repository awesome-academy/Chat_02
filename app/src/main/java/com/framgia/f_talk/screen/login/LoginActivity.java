package com.framgia.f_talk.screen.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.R;
import com.framgia.f_talk.databinding.ActivityLoginBinding;
import com.google.android.gms.common.SignInButton;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>
        implements LoginNavigator {
    @Inject
    LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;

    public static Intent getIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        return mLoginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginViewModel.setNavigator(this);
        mActivityLoginBinding = getViewDataBinding();
        mActivityLoginBinding.buttonGoogleSignIn.setSize(SignInButton.SIZE_WIDE);
    }

    @Override
    public void loginWithEmailAndPassword() {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure() {

    }

    @Override
    public void onEmailInvalid() {
        mActivityLoginBinding.textInputEmail.setError(getString(R.string.msg_email_invalid));
    }

    @Override
    public void onPasswordInvalid() {
        mActivityLoginBinding.textInputPassword.setError(getString(R.string.msg_password_invalid));
    }
}
