package com.framgia.f_talk.util.di.module;

import android.app.Application;
import android.content.Context;

import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.AppSchedulerProvider;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    RepositoryManager getRepositoryManager(RepositoryManager repositoryManager) {
        return repositoryManager;
    }
}
