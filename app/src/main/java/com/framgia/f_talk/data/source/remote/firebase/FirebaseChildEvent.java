package com.framgia.f_talk.data.source.remote.firebase;

import android.support.annotation.NonNull;

public class FirebaseChildEvent<T> {

    private @EventType
    int mEventType;
    private String mKey;
    private T mValue;
    private String mPreviousChildName;

    public FirebaseChildEvent(@NonNull String key, @NonNull T value,
                              @NonNull String previousChildName, @EventType int eventType) {
        this.mKey = key;
        this.mValue = value;
        this.mPreviousChildName = previousChildName;
        this.mEventType = eventType;
    }


    public FirebaseChildEvent(@NonNull String key, @NonNull T data, @EventType int eventType) {
        this.mKey = key;
        this.mValue = data;
        this.mEventType = eventType;
    }

    @NonNull
    public String getKey() {
        return mKey;
    }

    @NonNull
    public T getValue() {
        return mValue;
    }

    @NonNull
    public String getPreviousChildName() {
        return mPreviousChildName;
    }

    public int getEventType() {
        return mEventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FirebaseChildEvent<?> that = (FirebaseChildEvent<?>) o;

        if (mEventType != that.mEventType) return false;
        if (mValue != null ? !mValue.equals(that.mValue) : that.mValue != null) return false;
        return mPreviousChildName != null ? mPreviousChildName.equals(that.mPreviousChildName) :
                that.mPreviousChildName == null;

    }
}

