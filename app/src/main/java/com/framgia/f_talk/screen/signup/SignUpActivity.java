package com.framgia.f_talk.screen.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.R;
import com.framgia.f_talk.databinding.ActivitySignUpBinding;
import com.google.android.gms.common.SignInButton;

import javax.inject.Inject;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding, SignUpViewModel>
        implements SignUpNavigator {
    @Inject
    SignUpViewModel mSignUpViewModel;
    private ActivitySignUpBinding mActivitySignUpBinding;

    public static Intent getIntent(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    public SignUpViewModel getViewModel() {
        return mSignUpViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignUpViewModel.setNavigator(this);
        mActivitySignUpBinding = getViewDataBinding();
        mActivitySignUpBinding.buttonGoogleSignIn.setSize(SignInButton.SIZE_WIDE);
    }


    @Override
    public void signUpWithEmailAndPassword() {

    }

    @Override
    public void onDataValid() {

    }

    @Override
    public void onDataInvalid() {

    }
}
