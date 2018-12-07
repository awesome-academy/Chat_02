package com.framgia.f_talk.screen.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.R;
import com.framgia.f_talk.databinding.ActivityWelcomeBinding;
import com.framgia.f_talk.screen.login.LoginActivity;
import com.framgia.f_talk.screen.signup.SignUpActivity;

import javax.inject.Inject;

public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding, WelcomeViewModel>
        implements WelcomeNavigator {
    @Inject
    WelcomeViewModel mWelcomeViewModel;

    public static Intent getIntent(Context context) {
        return new Intent(context, WelcomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWelcomeViewModel.setNavigator(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public WelcomeViewModel getViewModel() {
        return mWelcomeViewModel;
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getIntent(this);
        startActivity(intent);
    }

    @Override
    public void openSignUpActivity() {
        Intent intent = SignUpActivity.getIntent(this);
        startActivity(intent);
    }

}
