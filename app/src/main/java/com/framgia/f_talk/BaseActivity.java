package com.framgia.f_talk;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import dagger.android.AndroidInjection;

@SuppressLint("Registered")
public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
        extends AppCompatActivity implements BaseFragment.Callback {
    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;
    private V mViewModel;

    public abstract int getBindingVariable();

    public abstract @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void hideKeybroad() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager != null) {
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void showLoading() {
        hideLoading();
        // TODO: 12/6/18 Show progress dialog
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    public void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState, persistentState);
        performDataBinding();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void addFragmentToActivity(@NonNull Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(fragment, tag);
        transaction.commit();
    }

    public void addFragmentToActivity(@NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}
