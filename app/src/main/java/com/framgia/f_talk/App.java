package com.framgia.f_talk;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.framgia.f_talk.util.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends MultiDexApplication implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> mInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mInjector;
    }
}
