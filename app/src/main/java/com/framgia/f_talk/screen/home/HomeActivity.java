package com.framgia.f_talk.screen.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.R;
import com.framgia.f_talk.databinding.ActivityHomeBinding;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel>
        implements HomeNavigator, BottomNavigationView.OnNavigationItemSelectedListener {
    @Inject
    HomeViewModel mHomeViewModel;
    private ActivityHomeBinding mActivityHomeBinding;

    public static Intent getIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        return mHomeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeViewModel.setNavigator(this);
        mActivityHomeBinding = getViewDataBinding();
        setUpView();
    }

    private void setUpView() {
        mActivityHomeBinding.bottomNavigation.setOnNavigationItemSelectedListener(this);
        mActivityHomeBinding.searchView.setFocusable(true);
        mActivityHomeBinding.searchView.setIconifiedByDefault(true);
        mActivityHomeBinding.searchView.setOnSearchClickListener(v ->
                mActivityHomeBinding.textSearchHint.setVisibility(View.GONE));
        mActivityHomeBinding.searchView.setOnCloseListener(() -> {
            mActivityHomeBinding.textSearchHint.setVisibility(View.VISIBLE);
            return false;
        });
        mActivityHomeBinding.textSearchHint.setOnClickListener(v ->
                mActivityHomeBinding.searchView.setIconified(false));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_recent:
                return true;
            case R.id.navigation_group:
                return true;
            case R.id.navigation_me:
                return true;
            default:
                return false;
        }
    }
}
