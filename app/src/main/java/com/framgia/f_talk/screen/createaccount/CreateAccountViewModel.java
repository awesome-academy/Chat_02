package com.framgia.f_talk.screen.createaccount;

import com.framgia.f_talk.BaseViewModel;
import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.StringUtil;
import com.framgia.f_talk.util.rx.SchedulerProvider;
import com.google.firebase.auth.FirebaseAuth;

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
        getRepositoryManager().createUserWithEmailAndPassword(firebaseAuth, email, password)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(authResult -> {
                            if (authResult.getUser() != null)
                                getNavigator().onCreateAccountSuccess();
                        }, throwable -> getNavigator().onCreateAccountFailure(),
                        () -> getNavigator().onCreateAccountFailure());
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
}
