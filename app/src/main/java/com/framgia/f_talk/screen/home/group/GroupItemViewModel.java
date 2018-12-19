package com.framgia.f_talk.screen.home.group;

import android.databinding.ObservableField;

public class GroupItemViewModel {
    public final ObservableField<String> mReceiverName = new ObservableField<>();
    public final ObservableField<String> mAvatarUrl = new ObservableField<>();
    public final ObservableField<String> mContent = new ObservableField<>();
    public final ObservableField<String> mTimeStamp = new ObservableField<>();
    public final ObservableField<String> mGroupId = new ObservableField<>();

    public GroupItemViewModel(String receiverName, String avatarUrl
            , String content, String timeStamp, String groupId) {
        mReceiverName.set(receiverName);
        mAvatarUrl.set(avatarUrl);
        mContent.set(content);
        mTimeStamp.set(timeStamp);
        mGroupId.set(groupId);
    }
}
