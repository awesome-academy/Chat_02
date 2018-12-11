package com.framgia.f_talk.data.model;

import android.support.annotation.IntDef;

@IntDef({
        MessageAccessType.PRIVATE,
        MessageAccessType.PUBLIC
})

public @interface MessageAccessType {
    int PRIVATE = 0;
    int PUBLIC = 1;
}
