package com.framgia.f_talk.screen.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.R;
import com.framgia.f_talk.databinding.ActivityHomeBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel>
        implements HomeNavigator, BottomNavigationView.OnNavigationItemSelectedListener,
        HasSupportFragmentInjector, ViewPager.OnPageChangeListener {
    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;
    @Inject
    HomeViewModel mHomeViewModel;
    @Inject
    HomePagerAdapter mHomePagerAdapter;
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
        mHomePagerAdapter.setTabCount(HomePagerAdapter.NUMBER_OF_HOME_TAB);
        mActivityHomeBinding.viewPagerHome.setAdapter(mHomePagerAdapter);
        mActivityHomeBinding.viewPagerHome.setOnPageChangeListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_recent:
                mActivityHomeBinding.viewPagerHome
                        .setCurrentItem(HomePagerAdapter.RECENT_TAB_INDEX);
                return true;
            case R.id.navigation_group:
                mActivityHomeBinding.viewPagerHome
                        .setCurrentItem(HomePagerAdapter.GROUP_TAB_INDEX);
                return true;
            case R.id.navigation_me:
                mActivityHomeBinding.viewPagerHome
                        .setCurrentItem(HomePagerAdapter.ME_TAB_INDEX);
                return true;
            default:
                return false;
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case HomePagerAdapter.RECENT_TAB_INDEX:
                mActivityHomeBinding.bottomNavigation.setSelectedItemId(R.id.navigation_recent);
                break;
            case HomePagerAdapter.GROUP_TAB_INDEX:
                mActivityHomeBinding.bottomNavigation.setSelectedItemId(R.id.navigation_group);
                break;
            case HomePagerAdapter.ME_TAB_INDEX:
                mActivityHomeBinding.bottomNavigation.setSelectedItemId(R.id.navigation_me);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
