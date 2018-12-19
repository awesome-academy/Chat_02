package com.framgia.f_talk.screen.home.group;

public class GroupEmptyItemViewModel {
    private final GroupEmptyItemViewModelListener mListener;

    public GroupEmptyItemViewModel(GroupEmptyItemViewModelListener listener) {
        mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface GroupEmptyItemViewModelListener {
        void onRetryClick();
    }
}
