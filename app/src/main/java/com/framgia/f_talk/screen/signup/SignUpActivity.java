package com.framgia.f_talk.screen.signup;

import android.os.Bundle;

import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.R;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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
