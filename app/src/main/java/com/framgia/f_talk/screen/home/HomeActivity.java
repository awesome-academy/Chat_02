package com.framgia.f_talk.screen.home;

import android.os.Bundle;

import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.R;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
