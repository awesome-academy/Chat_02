package com.framgia.f_talk.screen.home.group;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class GroupFragmentProvider {
    @ContributesAndroidInjector(modules = GroupFragmentModule.class)
    abstract GroupFragment provideGroupFragment();
}
