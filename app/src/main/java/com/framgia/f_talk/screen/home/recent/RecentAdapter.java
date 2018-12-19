package com.framgia.f_talk.screen.home.recent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.f_talk.BaseViewHolder;
import com.framgia.f_talk.databinding.ItemRecentEmptyViewBinding;
import com.framgia.f_talk.databinding.ItemRecentViewBinding;
import com.framgia.f_talk.screen.chat.ChatActivity;
import com.framgia.f_talk.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private List<RecentItemViewModel> mRecentItemViewModels;
    private RecentAdapterListener mListener;

    public RecentAdapter() {
        mRecentItemViewModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case VIEW_TYPE_NORMAL:
                ItemRecentViewBinding recentViewBinding = ItemRecentViewBinding
                        .inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
                return new RecentViewHolder(recentViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemRecentEmptyViewBinding emptyViewBinding = ItemRecentEmptyViewBinding
                        .inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
                return new RecentEmptyViewHolder(emptyViewBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return mRecentItemViewModels.isEmpty() ? 1 : mRecentItemViewModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mRecentItemViewModels.isEmpty() ? VIEW_TYPE_EMPTY : VIEW_TYPE_NORMAL;
    }

    public void addItems(List<RecentItemViewModel> recentItemViewModels) {
        mRecentItemViewModels.addAll(recentItemViewModels);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mRecentItemViewModels.clear();
        notifyDataSetChanged();
    }

    public void setListener(RecentAdapterListener listener) {
        mListener = listener;
    }

    public class RecentViewHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemRecentViewBinding mBinding;
        private RecentItemViewModel mRecentItemViewModel;

        public RecentViewHolder(ItemRecentViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final RecentItemViewModel recentItemViewModel = mRecentItemViewModels.get(position);
            mBinding.setViewModel(recentItemViewModel);
            mBinding.executePendingBindings();
            mRecentItemViewModel = mRecentItemViewModels.get(position);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = ChatActivity.newIntent(v.getContext());
            intent.putExtra(Constant.EXTRA_RECEIVER_ID, mRecentItemViewModel.mFriendId.get());
            intent.putExtra(Constant.EXTRA_RECEIVER_NAME, mRecentItemViewModel.mReceiverName.get());
            v.getContext().startActivity(intent);
        }
    }

    public class RecentEmptyViewHolder extends BaseViewHolder
            implements RecentEmptyItemViewModel.RecentEmptyItemViewModelListener {
        private final ItemRecentEmptyViewBinding mBinding;

        public RecentEmptyViewHolder(ItemRecentEmptyViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            RecentEmptyItemViewModel emptyItemViewModel = new RecentEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }

    interface RecentAdapterListener {
        void onRetryClick();
    }
}
