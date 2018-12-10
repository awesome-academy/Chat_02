package com.framgia.f_talk.data.source.remote.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.CompletableEmitter;

public class FirebaseCompletableTask
        implements OnFailureListener, OnSuccessListener, OnCompleteListener {

    private final CompletableEmitter mCompletableEmitter;

    private FirebaseCompletableTask(CompletableEmitter completableEmitter) {
        this.mCompletableEmitter = completableEmitter;
    }

    public static <T> void assignOnTask(CompletableEmitter completableEmitter, Task<T> task) {
        FirebaseCompletableTask handler = new FirebaseCompletableTask(completableEmitter);
        task.addOnFailureListener(handler);
        task.addOnSuccessListener(handler);
        try {
            task.addOnCompleteListener(handler);
        } catch (Throwable ignored) {
        }
    }


    @Override
    public void onFailure(@NonNull Exception e) {
        if (!mCompletableEmitter.isDisposed())
            mCompletableEmitter.onError(e);
    }

    @Override
    public void onComplete(@NonNull Task task) {
        mCompletableEmitter.onComplete();
    }

    @Override
    public void onSuccess(Object o) {
        mCompletableEmitter.onComplete();
    }
}
