package com.framgia.f_talk.data.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String mUId;
    private String mFullName;
    private String mEmail;
    private String mAvatarUrl;
    private String mBio;
    private String mLocation;
    private String mDateOfBirth;
    private List<String> mGroupIds;
    private List<String> mFriendUIds;
    private List<String> mIncomeRequests;
    private List<String> mOutComeRequest;

    public User() {

    }

    public User(String UId, String fullName, String email, String avatarUrl) {
        mUId = UId;
        mFullName = fullName;
        mEmail = email;
        mAvatarUrl = avatarUrl;
        mGroupIds = new ArrayList<>();
        mFriendUIds = new ArrayList<>();
        mIncomeRequests = new ArrayList<>();
        mOutComeRequest = new ArrayList<>();
    }

    public String getUId() {
        return mUId;
    }

    public String getFullName() {
        return mFullName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public String getBio() {
        return mBio;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDateOfBirth() {
        return mDateOfBirth;
    }

    public List<String> getGroupIds() {
        return mGroupIds;
    }

    public List<String> getFriendUIds() {
        return mFriendUIds;
    }

    public List<String> getIncomeRequests() {
        return mIncomeRequests;
    }

    public List<String> getOutComeRequest() {
        return mOutComeRequest;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public void setDateOfBirth(String dateOfBirth) {
        mDateOfBirth = dateOfBirth;
    }

    public void joinGroup(String groupId) {
        if (!mGroupIds.contains(groupId)) mGroupIds.add(groupId);

    }

    public void leaveGroup(String groupId) {
        mGroupIds.remove(groupId);
    }

    public void sendRequest(String uId) {
        if (!mOutComeRequest.contains(uId)) mOutComeRequest.add(uId);
    }

    public void receiveRequest(String uId) {
        if (!mIncomeRequests.contains(uId)) mIncomeRequests.add(uId);
    }

    public void removeIncomeRequest(String uId) {
        mIncomeRequests.remove(uId);
    }

    public void removeOutcomeRequest(String uId) {
        mOutComeRequest.remove(uId);
    }

    public void acceptRequest(String uId) {
        mIncomeRequests.remove(uId);
        if (!mFriendUIds.contains(uId)) mFriendUIds.add(uId);
    }
}
