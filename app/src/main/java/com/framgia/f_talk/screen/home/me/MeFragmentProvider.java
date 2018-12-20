package com.framgia.f_talk.screen.home.me;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MeFragmentProvider {
    @ContributesAndroidInjector(modules = MeFragmentModule.class)
    abstract MeFragment provideMeFragmentFactory();
}
