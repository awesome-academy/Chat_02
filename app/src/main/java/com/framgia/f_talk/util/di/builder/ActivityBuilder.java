package com.framgia.f_talk.util.di.builder;

import com.framgia.f_talk.screen.createaccount.CreateAccountActivity;
import com.framgia.f_talk.screen.createaccount.CreateAccountActivityModule;
import com.framgia.f_talk.screen.home.HomeActivity;
import com.framgia.f_talk.screen.home.HomeActivityModule;
import com.framgia.f_talk.screen.login.LoginActivity;
import com.framgia.f_talk.screen.login.LoginActivityModule;
import com.framgia.f_talk.screen.signup.SignUpActivity;
import com.framgia.f_talk.screen.signup.SignUpActivityModule;
import com.framgia.f_talk.screen.splash.SplashActivity;
import com.framgia.f_talk.screen.splash.SplashActivityModule;
import com.framgia.f_talk.screen.welcome.WelcomeActivity;
import com.framgia.f_talk.screen.welcome.WelcomeActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = WelcomeActivityModule.class)
    abstract WelcomeActivity bindWelcomeActivity();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = SignUpActivityModule.class)
    abstract SignUpActivity bindSignUpActivity();

    @ContributesAndroidInjector(modules = CreateAccountActivityModule.class)
    abstract CreateAccountActivity bindCreateAccountActivity();

    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity bindHomeActivity();
}
