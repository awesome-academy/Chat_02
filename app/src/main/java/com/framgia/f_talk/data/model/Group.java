package com.framgia.f_talk.data.model;

import com.framgia.f_talk.util.StringUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Group {
    private String mGroupId;
    private String mGroupName;
    private String mGroupAvatar;
    private String mGroupDescription;
    private List<String> mMemberUIds;

    public Group() {

    }

    public Group(String creatorId, String groupName, String groupAvatar) {
        mGroupId = StringUtil.append(creatorId,
                String.valueOf(Calendar.getInstance().getTimeInMillis()));
        mGroupName = groupName;
        mGroupAvatar = groupAvatar;
        mMemberUIds = new ArrayList<>();
        mMemberUIds.add(creatorId);
    }

    public String getGroupId() {
        return mGroupId;
    }

    public String getGroupName() {
        return mGroupName;
    }

    public String getGroupAvatar() {
        return mGroupAvatar;
    }

    public String getGroupDescription() {
        return mGroupDescription;
    }

    public List<String> getMemberUIds() {
        return mMemberUIds;
    }

    public void setGroupName(String groupName) {
        mGroupName = groupName;
    }

    public void setGroupAvatar(String groupAvatar) {
        mGroupAvatar = groupAvatar;
    }

    public void setGroupDescription(String groupDescription) {
        mGroupDescription = groupDescription;
    }

    public void setGroupId(String groupId) {
        mGroupId = groupId;
    }

    public void setMemberUIds(List<String> memberUIds) {
        mMemberUIds = memberUIds;
    }

    public void addMember(String uId) {
        if (!mMemberUIds.contains(uId)) mMemberUIds.add(uId);
    }

    public void removeMember(String uId) {
        mMemberUIds.remove(uId);
    }

    public int getNumberGroupMember() {
        return mMemberUIds.size();
    }
}
