package com.framgia.f_talk.screen.home.recent;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseFragment;
import com.framgia.f_talk.R;
import com.framgia.f_talk.databinding.FragmentRecentBinding;

import javax.inject.Inject;

public class RecentFragment extends BaseFragment<FragmentRecentBinding, RecentViewModel>
        implements RecentNavigator, RecentAdapter.RecentAdapterListener {
    FragmentRecentBinding mFragmentRecentBinding;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    RecentAdapter mRecentAdapter;
    @Inject
    RecentViewModel mRecentViewModel;
    @Inject
    ViewModelProvider.Factory mFactory;

    public static RecentFragment newInstance() {
        Bundle bundle = new Bundle();
        RecentFragment recentFragment = new RecentFragment();
        recentFragment.setArguments(bundle);
        return recentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecentViewModel.setNavigator(this);
        mRecentAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentRecentBinding = getViewDataBinding();
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentRecentBinding.recyclerRecent.setLayoutManager(mLayoutManager);
        mFragmentRecentBinding.recyclerRecent.setAdapter(mRecentAdapter);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recent;
    }

    @Override
    public RecentViewModel getViewModel() {
        return mRecentViewModel;
    }

    @Override
    public void onRetryClick() {

    }
}
