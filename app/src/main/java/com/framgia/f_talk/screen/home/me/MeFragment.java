package com.framgia.f_talk.screen.home.me;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseFragment;
import com.framgia.f_talk.R;
import com.framgia.f_talk.databinding.FragmentMeBinding;

import javax.inject.Inject;

public class MeFragment extends BaseFragment<FragmentMeBinding, MeViewModel>
        implements MeNavigator {
    @Inject
    MeViewModel mMeViewModel;

    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeViewModel.setNavigator(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public MeViewModel getViewModel() {
        return mMeViewModel;
    }

    @Override
    public void moveToEditInfoActivity() {

    }

    @Override
    public void moveToYourPhotoActivity() {

    }

    @Override
    public void moveToSettingActivity() {

    }

    @Override
    public void moveToYourFriendActivity() {

    }

    @Override
    public void moveToFindFriendActivity() {

    }

    @Override
    public void moveToFriendRequestActivity() {

    }
}
