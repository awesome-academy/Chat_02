package com.framgia.f_talk.util.di.module;

import android.app.Application;
import android.content.Context;

import com.framgia.f_talk.data.AppRepositoryManager;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.data.source.remote.AppAuthenticationSource;
import com.framgia.f_talk.data.source.remote.AppRealtimeDatabaseSource;
import com.framgia.f_talk.data.source.remote.AppStorageSource;
import com.framgia.f_talk.data.source.remote.AuthenticationSource;
import com.framgia.f_talk.data.source.remote.RealtimeDatabaseSource;
import com.framgia.f_talk.data.source.remote.StorageSource;
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
    RepositoryManager provideRepositoryManager(AppRepositoryManager appRepositoryManager) {
        return appRepositoryManager;
    }

    @Provides
    @Singleton
    AuthenticationSource provideAuthenticationSource(AppAuthenticationSource
                                                             appAuthenticationSource) {
        return appAuthenticationSource;
    }

    @Provides
    @Singleton
    RealtimeDatabaseSource provideRealtimeDatabaseSource(AppRealtimeDatabaseSource
                                                                 appRealtimeDatabaseSource) {
        return appRealtimeDatabaseSource;
    }

    @Provides
    @Singleton
    StorageSource provideStorageSource(AppStorageSource appStorageSource) {
        return appStorageSource;
    }
}
