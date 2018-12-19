package com.framgia.f_talk.screen.home.group;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.f_talk.BaseViewHolder;
import com.framgia.f_talk.databinding.ItemGroupEmptyViewBinding;
import com.framgia.f_talk.databinding.ItemGroupViewBinding;
import com.framgia.f_talk.screen.chat.ChatActivity;
import com.framgia.f_talk.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_EMPTY = 0;

    private static final int VIEW_TYPE_NORMAL = 1;

    private List<GroupItemViewModel> mGroupItemViewModels;

    private GroupAdapterListener mListener;

    public GroupAdapter() {
        mGroupItemViewModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case VIEW_TYPE_NORMAL:
                ItemGroupViewBinding groupViewBinding = ItemGroupViewBinding
                        .inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
                return new GroupViewHolder(groupViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemGroupEmptyViewBinding emptyViewBinding = ItemGroupEmptyViewBinding
                        .inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
                return new GroupEmptyViewHolder(emptyViewBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);

    }

    @Override
    public int getItemCount() {
        return mGroupItemViewModels.isEmpty() ? 1 : mGroupItemViewModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mGroupItemViewModels.isEmpty() ? VIEW_TYPE_EMPTY : VIEW_TYPE_NORMAL;
    }

    public void addItems(List<GroupItemViewModel> groupItemViewModels) {
        mGroupItemViewModels.addAll(groupItemViewModels);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mGroupItemViewModels.clear();
        notifyDataSetChanged();
    }

    public void setListener(GroupAdapterListener listener) {
        mListener = listener;
    }

    public class GroupViewHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemGroupViewBinding mBinding;
        private GroupItemViewModel mGroupItemViewModel;

        public GroupViewHolder(ItemGroupViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final GroupItemViewModel groupItemViewModel = mGroupItemViewModels.get(position);
            mBinding.setViewModel(groupItemViewModel);
            mBinding.executePendingBindings();
            mGroupItemViewModel = mGroupItemViewModels.get(position);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = ChatActivity.newIntent(v.getContext());
            intent.putExtra(Constant.EXTRA_RECEIVER_ID, mGroupItemViewModel.mGroupId.get());
            intent.putExtra(Constant.EXTRA_RECEIVER_NAME, mGroupItemViewModel.mReceiverName.get());
            v.getContext().startActivity(intent);
        }
    }

    public class GroupEmptyViewHolder extends BaseViewHolder
            implements GroupEmptyItemViewModel.GroupEmptyItemViewModelListener {
        private final ItemGroupEmptyViewBinding mBinding;

        public GroupEmptyViewHolder(ItemGroupEmptyViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            GroupEmptyItemViewModel emptyItemViewModel = new GroupEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }

    interface GroupAdapterListener {
        void onRetryClick();
    }
}
