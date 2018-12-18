package com.framgia.f_talk.screen.createaccount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.R;
import com.framgia.f_talk.databinding.ActivityCreateAccountBinding;
import com.framgia.f_talk.screen.home.HomeActivity;
import com.framgia.f_talk.util.Constant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

public class CreateAccountActivity
        extends BaseActivity<ActivityCreateAccountBinding, CreateAccountViewModel>
        implements CreateAccountNavigator {

    @Inject
    CreateAccountViewModel mCreateAccountViewModel;

    private ActivityCreateAccountBinding mActivityCreateAccountBinding;

    private String mFullName;

    private String mEmail;

    public static Intent getIntent(Context context) {
        return new Intent(context, CreateAccountActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_account;
    }

    @Override
    public CreateAccountViewModel getViewModel() {
        return mCreateAccountViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCreateAccountViewModel.setNavigator(this);
        mActivityCreateAccountBinding = getViewDataBinding();
        mFullName = getIntent().getExtras().getString(Constant.EXTRA_FULL_NAME);
        mEmail = getIntent().getExtras().getString(Constant.EXTRA_EMAIL);
    }

    @Override
    public void createAccountWithEmailAndPassWord() {
        mActivityCreateAccountBinding.textInputPassword.setError(null);
        mActivityCreateAccountBinding.textInputConfirmPassword.setError(null);
        String password = mActivityCreateAccountBinding
                .textInputPassword.getEditText().getText().toString();
        String confirmPassword = mActivityCreateAccountBinding
                .textInputConfirmPassword.getEditText().getText().toString();
        mCreateAccountViewModel.checkPassword(password, confirmPassword);
    }

    @Override
    public void onCreateAccountSuccess() {
        mCreateAccountViewModel.createUserInfo(FirebaseAuth.getInstance(),
                FirebaseDatabase.getInstance(), mEmail, mFullName);
    }

    @Override
    public void onCreateAccountFailure() {
        Toast.makeText(this, getString(R.string.msg_create_account_fail), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordLengthInvalid() {
        mActivityCreateAccountBinding.textInputPassword
                .setError(getString(R.string.msg_password_invalid));
    }

    @Override
    public void onPasswordNotMatch() {
        mActivityCreateAccountBinding.textInputConfirmPassword
                .setError(getString(R.string.msg_password_not_match));
    }

    @Override
    public void onPasswordValid() {
        String password = mActivityCreateAccountBinding
                .textInputPassword.getEditText().getText().toString();
        mCreateAccountViewModel.createAccountWithEmailAndPassword(FirebaseAuth.getInstance(),
                mEmail, password);
    }

    @Override
    public void onCreateUserInfoSuccess() {
        startActivity(HomeActivity.getIntent(this));
        finish();
    }

    @Override
    public void onCreateUserInfoFailure() {
        Toast.makeText(this, getString(R.string.msg_create_account_fail), Toast.LENGTH_SHORT).show();
    }
}
