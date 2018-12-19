package com.framgia.f_talk.screen.home.group;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

public class GroupItemViewModel implements Comparable<GroupItemViewModel> {
    public final ObservableField<String> mReceiverName = new ObservableField<>();
    public final ObservableField<String> mAvatarUrl = new ObservableField<>();
    public final ObservableField<String> mContent = new ObservableField<>();
    public final ObservableField<String> mTimeStamp = new ObservableField<>();
    public final ObservableField<String> mGroupId = new ObservableField<>();
    public final ObservableField<Long> mTimeInMili = new ObservableField<>();

    public GroupItemViewModel(String receiverName, String avatarUrl
            , String content, String timeStamp, String groupId, long timeInMili) {
        mReceiverName.set(receiverName);
        mAvatarUrl.set(avatarUrl);
        mContent.set(content);
        mTimeStamp.set(timeStamp);
        mGroupId.set(groupId);
        mTimeInMili.set(timeInMili);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!GroupItemViewModel.class.isAssignableFrom(obj.getClass())) return false;
        final GroupItemViewModel model = (GroupItemViewModel) obj;
        return mGroupId.get().equals(model.mGroupId.get());
    }

    @Override
    public int compareTo(@NonNull GroupItemViewModel o) {
        return o.mTimeInMili.get().compareTo(mTimeInMili.get());
    }
}
