package com.framgia.f_talk.screen.home.recent;

import android.databinding.ObservableField;

public class RecentItemViewModel {
    public final ObservableField<String> mReceiverName = new ObservableField<>();
    public final ObservableField<String> mAvatarUrl = new ObservableField<>();
    public final ObservableField<String> mContent = new ObservableField<>();
    public final ObservableField<String> mTimeStamp = new ObservableField<>();

    public RecentItemViewModel(String receiverName, String avatarUrl
            , String content, String timeStamp) {
        mReceiverName.set(receiverName);
        mAvatarUrl.set(avatarUrl);
        mContent.set(content);
        mTimeStamp.set(timeStamp);
    }
}
