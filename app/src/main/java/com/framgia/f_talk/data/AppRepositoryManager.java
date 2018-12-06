package com.framgia.f_talk.data;

import com.framgia.f_talk.data.source.remote.AuthenticationSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;

@Singleton
public class AppRepositoryManager implements RepositoryManager {
    private final AuthenticationSource mAuthenticationSource;

    @Inject
    public AppRepositoryManager(AuthenticationSource authenticationSource) {
        mAuthenticationSource = authenticationSource;
    }

    @Override
    public Maybe<AuthResult> signInWithEmailAndPassword(FirebaseAuth firebaseAuth
            , String email, String password) {
        return mAuthenticationSource.signInWithEmailAndPassword(firebaseAuth,
                email, password);
    }

    @Override
    public Maybe<AuthResult> signInWithCredential(FirebaseAuth firebaseAuth
            , AuthCredential credential) {
        return mAuthenticationSource.signInWithCredential(firebaseAuth, credential);
    }

    @Override
    public Maybe<AuthResult> createUserWithEmailAndPassword(FirebaseAuth firebaseAuth
            , String email, String password) {
        return mAuthenticationSource.createUserWithEmailAndPassword(firebaseAuth,
                email, password);
    }
}
