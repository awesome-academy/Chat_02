package com.framgia.f_talk.util.di.builder;

import com.framgia.f_talk.screen.splash.SplashActivity;
import com.framgia.f_talk.screen.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();
}
