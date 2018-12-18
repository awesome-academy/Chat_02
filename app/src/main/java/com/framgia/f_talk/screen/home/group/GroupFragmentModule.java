package com.framgia.f_talk.screen.home.group;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.framgia.f_talk.ViewModelProviderFactory;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class GroupFragmentModule {
    @Provides
    GroupViewModel mGroupViewModel(RepositoryManager repositoryManager,
                                   SchedulerProvider schedulerProvider) {
        return new GroupViewModel(repositoryManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLayoutManager(GroupFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    GroupAdapter provideGroupAdapter() {
        return new GroupAdapter();
    }

    @Provides
    ViewModelProvider.Factory provideGroupViewModel(GroupViewModel groupViewModel) {
        return new ViewModelProviderFactory<>(groupViewModel);
    }
}
