package com.framgia.f_talk.data.source.remote.firebase;

import android.support.annotation.IntDef;

@IntDef({
        EventType.ADDED,
        EventType.CHANGED,
        EventType.REMOVED,
        EventType.MOVED
})

public @interface EventType {
    int ADDED = 0;
    int CHANGED = 1;
    int REMOVED = 2;
    int MOVED = 3;
}
