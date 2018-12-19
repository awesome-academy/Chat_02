package com.framgia.f_talk.screen.signup;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.data.model.User;
import com.framgia.f_talk.util.Constant;
import com.framgia.f_talk.util.StringUtil;
import com.framgia.f_talk.util.rx.SchedulerProvider;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpViewModel extends BaseViewModel<SignUpNavigator> {
    public SignUpViewModel(RepositoryManager repositoryManager, SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }

    public void onNextClick() {
        getNavigator().provideNameAndEmail();
    }

    public void setUserData(String fullName, String email) {
        if (!StringUtil.isFullNameValid(fullName)) {
            getNavigator().onFullNameInvalid();
            return;
        }
        if (!StringUtil.isEmailValid(email)) {
            getNavigator().onEmailInvalid();
            return;
        }
        getNavigator().moveToCreateAccountActivity();
    }

    public void onGoogleSignUpClick() {
        getNavigator().signUpWithGoogle();
    }

    public void signUpWithGoogle(FirebaseAuth firebaseAuth, AuthCredential credential) {
        getCompositeDisposable().add(getRepositoryManager()
                .signInWithCredential(firebaseAuth, credential)
                .subscribe(authResult -> {
                            if (authResult.getUser() != null)
                                getNavigator().onSignUpGoogleSuccess();
                        },
                        throwable -> getNavigator().onSignUpFailure(),
                        () -> getNavigator().onSignUpFailure()));
    }

    public void signUpWithFacebook(FirebaseAuth firebaseAuth, AuthCredential credential) {
        getCompositeDisposable().add(getRepositoryManager()
                .signInWithCredential(firebaseAuth, credential)
                .subscribe(authResult -> {
                            if (authResult.getUser() != null)
                                getNavigator().onSignUpFacebookSuccess();
                        },
                        throwable -> getNavigator().onSignUpFailure(),
                        () -> getNavigator().onSignUpFailure()));
    }

    public void createUserInfo(FirebaseDatabase firebaseDatabase, FirebaseUser firebaseUser) {
        String uid = firebaseUser.getUid();
        String fullName = firebaseUser.getDisplayName();
        String email = firebaseUser.getEmail();
        String avatarUrl = Objects.requireNonNull(firebaseUser.getPhotoUrl()).toString();
        User user = new User(uid, fullName, email, avatarUrl);
        getCompositeDisposable().add(getRepositoryManager()
                .setValue(firebaseDatabase.getReference()
                        .child(StringUtil.append(Constant.USER_DATABASE_DIR, uid)), user)
                .subscribe(() -> getNavigator().onCreateUserInfoSuccess(),
                        throwable -> getNavigator().onCreateUserInfoFailure()));
    }
}
