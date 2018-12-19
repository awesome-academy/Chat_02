package com.framgia.f_talk.screen.home.recent;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

public class RecentItemViewModel implements Comparable<RecentItemViewModel> {
    public final ObservableField<String> mReceiverName = new ObservableField<>();
    public final ObservableField<String> mAvatarUrl = new ObservableField<>();
    public final ObservableField<String> mContent = new ObservableField<>();
    public final ObservableField<String> mTimeStamp = new ObservableField<>();
    public final ObservableField<String> mFriendId = new ObservableField<>();
    public final ObservableField<Long> mTimeInMili = new ObservableField<>();

    public RecentItemViewModel(String receiverName, String avatarUrl
            , String content, String timeStamp, String friendId, long timeInMili) {
        mReceiverName.set(receiverName);
        mAvatarUrl.set(avatarUrl);
        mContent.set(content);
        mTimeStamp.set(timeStamp);
        mFriendId.set(friendId);
        mTimeInMili.set(timeInMili);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!RecentItemViewModel.class.isAssignableFrom(obj.getClass())) return false;
        final RecentItemViewModel model = (RecentItemViewModel) obj;
        return mFriendId.get().equals(model.mFriendId.get());
    }


    @Override
    public int compareTo(@NonNull RecentItemViewModel o) {
        return o.mTimeInMili.get().compareTo(mTimeInMili.get());
    }
}
