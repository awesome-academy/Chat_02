package com.framgia.f_talk;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<N> extends ViewModel {
    private final RepositoryManager mRepositoryManager;
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private final SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private WeakReference<N> mNavigator;

    public BaseViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        mRepositoryManager = repositoryManager;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public RepositoryManager getRepositoryManager() {
        return mRepositoryManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public WeakReference<N> getNavigator() {
        return mNavigator;
    }

    public void setNavigator(WeakReference<N> navigator) {
        mNavigator = navigator;
    }
}
