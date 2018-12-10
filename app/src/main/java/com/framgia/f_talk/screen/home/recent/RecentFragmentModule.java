package com.framgia.f_talk.screen.home.recent;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.framgia.f_talk.ViewModelProviderFactory;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public class RecentFragmentModule {
    @Provides
    RecentViewModel recentViewModel(RepositoryManager repositoryManager
            , SchedulerProvider schedulerProvider) {
        return new RecentViewModel(repositoryManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLayoutManager(RecentFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    RecentAdapter provideRecentAdapter() {
        return new RecentAdapter();
    }

    @Provides
    ViewModelProvider.Factory provideRecentViewModel(RecentViewModel recentViewModel){
        return new ViewModelProviderFactory<>(recentViewModel);
    }
}
