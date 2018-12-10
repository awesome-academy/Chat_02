package com.framgia.f_talk.screen.home.recent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecentFragmentProvider {
    @ContributesAndroidInjector(modules = RecentFragmentModule.class)
    abstract RecentFragment provideRecentFragment();
}
