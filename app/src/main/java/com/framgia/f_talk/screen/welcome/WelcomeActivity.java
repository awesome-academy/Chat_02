package com.framgia.f_talk.screen.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.R;
import com.framgia.f_talk.screen.login.LoginActivity;
import com.framgia.f_talk.screen.signup.SignUpActivity;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        findViewById(R.id.text_get_started).setOnClickListener(this);
        findViewById(R.id.text_log_in).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_get_started:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.text_log_in:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default:
                break;
        }
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
