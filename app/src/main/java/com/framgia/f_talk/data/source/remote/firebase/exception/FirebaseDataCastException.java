package com.framgia.f_talk.data.source.remote.firebase.exception;

public class FirebaseDataCastException extends Exception {
    public FirebaseDataCastException() {
    }

    public FirebaseDataCastException(String message) {
        super(message);
    }

    public FirebaseDataCastException(String message, Throwable cause) {
        super(message, cause);
    }

    public FirebaseDataCastException(Throwable cause) {
        super(cause);
    }
}
