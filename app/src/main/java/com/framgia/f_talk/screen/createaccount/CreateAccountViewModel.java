package com.framgia.f_talk.screen.createaccount;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.data.model.User;
import com.framgia.f_talk.util.Constant;
import com.framgia.f_talk.util.StringUtil;
import com.framgia.f_talk.util.rx.SchedulerProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountViewModel extends BaseViewModel<CreateAccountNavigator> {
    public CreateAccountViewModel(RepositoryManager repositoryManager,
                                  SchedulerProvider schedulerProvider) {
        super(repositoryManager, schedulerProvider);
    }

    public void onCreateAccountClick() {
        getNavigator().createAccountWithEmailAndPassWord();
    }

    public void createAccountWithEmailAndPassword(FirebaseAuth firebaseAuth,
                                                  String email, String password) {
        getCompositeDisposable().add(getRepositoryManager().createUserWithEmailAndPassword(firebaseAuth, email, password)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(authResult -> {
                            if (authResult.getUser() != null)
                                getNavigator().onCreateAccountSuccess();
                        }, throwable -> getNavigator().onCreateAccountFailure(),
                        () -> getNavigator().onCreateAccountFailure()));
    }

    public void checkPassword(String password, String confirmPassword) {
        if (!StringUtil.isPasswordValid(password)) {
            getNavigator().onPasswordLengthInvalid();
            return;
        }
        if (!password.equals(confirmPassword)) {
            getNavigator().onPasswordNotMatch();
            return;
        }
        getNavigator().onPasswordValid();
    }

    public void createUserInfo(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase,
                               String email, String fullName) {
        String uid = firebaseAuth.getUid();
        User user = new User(uid, fullName, email, null);
        getCompositeDisposable().add(getRepositoryManager()
                .setValue(firebaseDatabase.getReference()
                        .child(StringUtil.append(Constant.USER_DATABASE_DIR, uid)), user)
                .subscribe(() -> getNavigator().onCreateUserInfoSuccess(),
                        throwable -> getNavigator().onCreateUserInfoFailure()));
    }
}
