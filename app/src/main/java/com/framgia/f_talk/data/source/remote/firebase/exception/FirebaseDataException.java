package com.framgia.f_talk.data.source.remote.firebase.exception;

import com.google.firebase.database.DatabaseError;

public class FirebaseDataException extends Exception {
    protected DatabaseError mError;

    public FirebaseDataException(DatabaseError error) {
        mError = error;
    }

    public DatabaseError getError() {
        return mError;
    }
}
