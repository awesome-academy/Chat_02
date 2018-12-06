package com.framgia.f_talk.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.screen.home.HomeActivity;
import com.framgia.f_talk.screen.welcome.WelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            startActivity(new Intent(this, WelcomeActivity.class));
        else startActivity(new Intent(this, HomeActivity.class));
        finish();
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
