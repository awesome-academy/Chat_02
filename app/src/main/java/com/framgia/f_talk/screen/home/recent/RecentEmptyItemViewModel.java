package com.framgia.f_talk.screen.home.recent;

public class RecentEmptyItemViewModel {
    private final RecentEmptyItemViewModelListener mListener;

    public RecentEmptyItemViewModel(RecentEmptyItemViewModelListener listener) {
        mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface RecentEmptyItemViewModelListener {
        void onRetryClick();
    }
}
