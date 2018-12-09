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
    public void provideNameAndEmail() {
        mActivitySignUpBinding.textInputFullName.setError(null);
        mActivitySignUpBinding.textInputEmail.setError(null);
        String fullName = mActivitySignUpBinding
                .textInputFullName.getEditText().getText().toString();
        String email = mActivitySignUpBinding
                .textInputEmail.getEditText().getText().toString();
        mSignUpViewModel.setUserData(fullName, email);
    }

    @Override
    public void onFullNameInvalid() {
        mActivitySignUpBinding.textInputFullName.setError(getString(R.string.msg_full_name_invalid));
    }

    @Override
    public void onEmailInvalid() {
        mActivitySignUpBinding.textInputEmail.setError(getString(R.string.msg_email_invalid));
    }

    @Override
    public void moveToPasswordActivity() {
        // TODO: 12/7/18 Move to Password Activity
    }

}
