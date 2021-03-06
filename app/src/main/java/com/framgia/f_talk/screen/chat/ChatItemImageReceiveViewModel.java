package com.framgia.f_talk.screen.chat;

import android.databinding.ObservableField;

public class ChatItemImageReceiveViewModel {
    public final ObservableField<String> mContent;
    public final ObservableField<String> mTimeStamp;
    public final ObservableField<String> mSenderId;
    public final ObservableField<Integer> mMessageType;

    public ChatItemImageReceiveViewModel(ChatItemViewModel chatItemViewModel) {
        mContent = chatItemViewModel.mContent;
        mTimeStamp = chatItemViewModel.mTimeStamp;
        mSenderId = chatItemViewModel.mSenderId;
        mMessageType = chatItemViewModel.mMessageType;
    }
}
