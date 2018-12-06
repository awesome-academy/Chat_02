package com.framgia.f_talk.data.source.remote;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.Maybe;

public interface AuthenticationSource {

    Maybe<AuthResult> signInWithEmailAndPassword(FirebaseAuth firebaseAuth,
                                                 String email, String password);

    Maybe<AuthResult> signInWithCredential(final FirebaseAuth firebaseAuth,
                                           final AuthCredential credential);

    Maybe<AuthResult> createUserWithEmailAndPassword(FirebaseAuth firebaseAuth,
                                                     String email, String password);
}
