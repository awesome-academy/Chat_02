package com.framgia.f_talk.screen.chat;

public class ChatEmptyItemViewModel {
    private final ChatEmptyItemViewModelListener mListener;

    public ChatEmptyItemViewModel(ChatEmptyItemViewModelListener listener) {
        mListener = listener;
    }

    public interface ChatEmptyItemViewModelListener {
        void onRetryClick();
    }
}
