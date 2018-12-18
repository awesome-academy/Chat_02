package com.framgia.f_talk.screen.home.group;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseFragment;
import com.framgia.f_talk.R;
import com.framgia.f_talk.databinding.FragmentGroupBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

public class GroupFragment extends BaseFragment<FragmentGroupBinding, GroupViewModel>
        implements GroupNavigator, GroupAdapter.GroupAdapterListener {
    FragmentGroupBinding mFragmentGroupBinding;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    GroupAdapter mGroupAdapter;
    @Inject
    GroupViewModel mGroupViewModel;
    @Inject
    ViewModelProvider.Factory mFactory;

    public static GroupFragment newInstance() {
        Bundle bundle = new Bundle();
        GroupFragment groupFragment = new GroupFragment();
        groupFragment.setArguments(bundle);
        return groupFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGroupViewModel.setNavigator(this);
        mGroupAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentGroupBinding = getViewDataBinding();
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentGroupBinding.recyclerGroup.setLayoutManager(mLayoutManager);
        mFragmentGroupBinding.recyclerGroup.setAdapter(mGroupAdapter);
        mGroupViewModel.fetchGroupMessage(FirebaseDatabase.getInstance(),
                FirebaseAuth.getInstance());
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_group;
    }

    @Override
    public GroupViewModel getViewModel() {
        return mGroupViewModel;
    }

    @Override
    public void onRetryClick() {

    }
}
