package com.framgia.f_talk.data.source.remote.firebase;

import android.support.annotation.NonNull;

import com.framgia.f_talk.data.source.remote.firebase.exception.FirebaseNullDataException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.MaybeEmitter;

public class FirebaseTask<T> implements OnSuccessListener<T>, OnFailureListener,
        OnCompleteListener<T> {
    private final MaybeEmitter<? super T> mEmitter;

    private FirebaseTask(MaybeEmitter<? super T> emitter) {
        mEmitter = emitter;
    }

    public static <T> void assignOnTask(MaybeEmitter<? super T> emitter, Task<T> task) {
        FirebaseTask handler = new FirebaseTask(emitter);
        task.addOnSuccessListener(handler);
        task.addOnFailureListener(handler);
        try {
            task.addOnCompleteListener(handler);
        } catch (Throwable ignored) {
        }
    }

    @Override
    public void onComplete(@NonNull Task<T> task) {
        mEmitter.onComplete();
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        if (!mEmitter.isDisposed()) {
            mEmitter.onError(e);
        }
    }

    @Override
    public void onSuccess(T t) {
        if (t != null) {
            mEmitter.onSuccess(t);
        } else {
            mEmitter.onError(new FirebaseNullDataException());
        }
    }
}
