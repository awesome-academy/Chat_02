package com.framgia.f_talk.data.model;

import android.support.annotation.NonNull;

import java.util.Calendar;

public class Message implements Comparable<Message> {
    private String mSenderId;
    @MessageAccessType
    private int mMessageAccessType;
    private String mReceiverId;
    @MessageContentType
    private int mMessageContentType;
    private String mContent;
    private long mTimeStamp;

    public Message() {

    }

    public Message(String senderId, @MessageAccessType int messageAccessType, String receiverId,
                   @MessageContentType int messageContentType, String content) {
        mSenderId = senderId;
        mMessageAccessType = messageAccessType;
        mReceiverId = receiverId;
        mMessageContentType = messageContentType;
        mContent = content;
        mTimeStamp = Calendar.getInstance().getTimeInMillis();
    }

    public String getSenderId() {
        return mSenderId;
    }

    public int getMessageAccessType() {
        return mMessageAccessType;
    }

    public String getReceiverId() {
        return mReceiverId;
    }

    public int getMessageContentType() {
        return mMessageContentType;
    }

    public String getContent() {
        return mContent;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    @Override
    public int compareTo(@NonNull Message message) {
        return message.getTimeStamp() > this.getTimeStamp() ? 1 : -1;
    }
}
