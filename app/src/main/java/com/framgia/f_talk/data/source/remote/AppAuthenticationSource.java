package com.framgia.f_talk.data.source.remote;

import com.framgia.f_talk.data.source.remote.firebase.FirebaseTask;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;

@Singleton
public class AppAuthenticationSource implements AuthenticationSource {

    @Inject
    public AppAuthenticationSource() {

    }

    @Override
    public Maybe<AuthResult> signInWithEmailAndPassword(FirebaseAuth firebaseAuth,
                                                        String email, String password) {
        return Maybe.create(emitter -> FirebaseTask.assignOnTask(
                emitter, firebaseAuth.signInWithEmailAndPassword(email, password)));
    }

    @Override
    public Maybe<AuthResult> signInWithCredential(final FirebaseAuth firebaseAuth,
                                                  final AuthCredential credential) {
        return Maybe.create(emitter -> FirebaseTask.assignOnTask(
                emitter, firebaseAuth.signInWithCredential(credential)));
    }

    @Override
    public Maybe<AuthResult> createUserWithEmailAndPassword(FirebaseAuth firebaseAuth,
                                                            String email, String password) {
        return Maybe.create(emitter -> FirebaseTask.assignOnTask(
                emitter, firebaseAuth.createUserWithEmailAndPassword(email, password)));
    }
}
