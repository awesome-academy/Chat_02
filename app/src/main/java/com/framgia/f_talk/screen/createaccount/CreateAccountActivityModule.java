package com.framgia.f_talk.screen.createaccount;

import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateAccountActivityModule {
    @Provides
    CreateAccountViewModel provideCreateAccountViewModel(RepositoryManager repositoryManager,
                                                         SchedulerProvider schedulerProvider) {
        return new CreateAccountViewModel(repositoryManager, schedulerProvider);
    }
}
