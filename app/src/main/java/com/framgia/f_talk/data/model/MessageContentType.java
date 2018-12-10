package com.framgia.f_talk.data.model;

import android.support.annotation.IntDef;

@IntDef({
        MessageContentType.TEXT,
        MessageContentType.IMAGE,
        MessageContentType.EMOIJ
})
public @interface MessageContentType {
    int TEXT = 0;
    int IMAGE = 1;
    int EMOIJ = 2;
}
