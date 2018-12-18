package com.framgia.f_talk.screen.chat;

import android.databinding.ObservableField;

import com.framgia.f_talk.data.model.MessageContentType;

public class ChatItemViewModel {
    public final ObservableField<String> mContent = new ObservableField<>();
    public final ObservableField<String> mTimeStamp = new ObservableField<>();
    public final ObservableField<String> mSenderId = new ObservableField<>();
    public final ObservableField<Integer> mMessageType = new ObservableField<>();

    public ChatItemViewModel(String content, String timeStamp, String senderId,
                             @MessageContentType int messageType) {
        mContent.set(content);
        mTimeStamp.set(timeStamp);
        mSenderId.set(senderId);
        mMessageType.set(messageType);
    }
}
