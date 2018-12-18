package com.framgia.f_talk.screen.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.framgia.f_talk.screen.home.group.GroupFragment;
import com.framgia.f_talk.screen.home.me.MeFragment;
import com.framgia.f_talk.screen.home.recent.RecentFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {
    public static final int NUMBER_OF_HOME_TAB = 3;
    public static final int RECENT_TAB_INDEX = 0;
    public static final int GROUP_TAB_INDEX = 1;
    public static final int ME_TAB_INDEX = 2;
    private int mTabCount;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        mTabCount = 0;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case RECENT_TAB_INDEX:
                return RecentFragment.newInstance();
            case GROUP_TAB_INDEX:
                return GroupFragment.newInstance();
            case ME_TAB_INDEX:
                return MeFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setTabCount(int tabCount) {
        mTabCount = tabCount;
    }

}
