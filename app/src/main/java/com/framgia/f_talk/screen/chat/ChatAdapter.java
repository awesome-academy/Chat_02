package com.framgia.f_talk.screen.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.f_talk.BaseViewHolder;
import com.framgia.f_talk.data.model.MessageContentType;
import com.framgia.f_talk.databinding.ItemChatEmpyViewBinding;
import com.framgia.f_talk.databinding.ItemReceivedImageViewBinding;
import com.framgia.f_talk.databinding.ItemReceivedTextViewBinding;
import com.framgia.f_talk.databinding.ItemSentImageViewBinding;
import com.framgia.f_talk.databinding.ItemSentTextViewBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_SEND_TEXT = 1;
    public static final int VIEW_TYPE_SEND_IMAGE = 2;
    public static final int VIEW_TYPE_RECEIVE_TEXT = 3;
    public static final int VIEW_TYPE_RECEIVE_IMAGE = 4;
    private final List<ChatItemViewModel> mChatItemViewModels;
    private String mUserId;
    private ChatAdapterListener mListener;

    public ChatAdapter() {
        mChatItemViewModels = new ArrayList<>();
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    @Override
    public int getItemCount() {
        if (!mChatItemViewModels.isEmpty()) {
            return mChatItemViewModels.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mChatItemViewModels.isEmpty()) return VIEW_TYPE_EMPTY;
        if (mChatItemViewModels.get(position).mSenderId.get().equals(mUserId))
            return mChatItemViewModels.get(position).mMessageType.get().intValue()
                    == MessageContentType.TEXT ? VIEW_TYPE_SEND_TEXT : VIEW_TYPE_SEND_IMAGE;
        else {
            return mChatItemViewModels.get(position).mMessageType.get().intValue()
                    == MessageContentType.TEXT ? VIEW_TYPE_RECEIVE_TEXT : VIEW_TYPE_RECEIVE_IMAGE;
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case VIEW_TYPE_SEND_TEXT:
                ItemSentTextViewBinding itemSentTextViewBinding =
                        ItemSentTextViewBinding.inflate(LayoutInflater
                                .from(viewGroup.getContext()), viewGroup, false);
                return new ItemTextSendHolder(itemSentTextViewBinding);
            case VIEW_TYPE_SEND_IMAGE:
                ItemSentImageViewBinding itemSentImageViewBinding =
                        ItemSentImageViewBinding.inflate(LayoutInflater
                                .from(viewGroup.getContext()), viewGroup, false);
                return new ItemImageSendHolder(itemSentImageViewBinding);
            case VIEW_TYPE_RECEIVE_TEXT:
                ItemReceivedTextViewBinding itemReceivedTextViewBinding =
                        ItemReceivedTextViewBinding.inflate(LayoutInflater
                                .from(viewGroup.getContext()), viewGroup, false);
                return new ItemTextReceiveHolder(itemReceivedTextViewBinding);
            case VIEW_TYPE_RECEIVE_IMAGE:
                ItemReceivedImageViewBinding itemReceivedImageViewBinding =
                        ItemReceivedImageViewBinding.inflate(LayoutInflater
                                .from(viewGroup.getContext()), viewGroup, false);
                return new ItemImageReceiveHolder(itemReceivedImageViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemChatEmpyViewBinding itemChatEmpyViewBinding =
                        ItemChatEmpyViewBinding.inflate(LayoutInflater
                                .from(viewGroup.getContext()), viewGroup, false);
                return new EmptyViewHoder(itemChatEmpyViewBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    public void addItems(List<ChatItemViewModel> chatItemViewModels) {
        mChatItemViewModels.addAll(chatItemViewModels);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mChatItemViewModels.clear();
        notifyDataSetChanged();
    }

    public void setListener(ChatAdapterListener listener) {
        mListener = listener;
    }

    public interface ChatAdapterListener {
        void onRetryClick();
    }

    public class EmptyViewHoder extends BaseViewHolder implements
            ChatEmptyItemViewModel.ChatEmptyItemViewModelListener {
        private final ItemChatEmpyViewBinding mBinding;

        public EmptyViewHoder(ItemChatEmpyViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            ChatEmptyItemViewModel emptyItemViewModel = new ChatEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }

    public class ItemTextSendHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemSentTextViewBinding mBinding;

        public ItemTextSendHolder(ItemSentTextViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.textMessage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mBinding.textTime.setVisibility(mBinding.textTime.getVisibility() == View.VISIBLE ?
                    View.GONE : View.VISIBLE);
        }

        @Override
        public void onBind(int position) {
            ChatItemTextSendViewModel chatItemTextSendViewModel =
                    new ChatItemTextSendViewModel(mChatItemViewModels.get(position));
            mBinding.setViewModel(chatItemTextSendViewModel);
            mBinding.executePendingBindings();
        }
    }

    public class ItemImageSendHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemSentImageViewBinding mBinding;

        public ItemImageSendHolder(ItemSentImageViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public void onBind(int position) {
            ChatItemImageSendViewModel chatItemImageSendViewModel =
                    new ChatItemImageSendViewModel(mChatItemViewModels.get(position));
            mBinding.setViewModel(chatItemImageSendViewModel);
            mBinding.executePendingBindings();
        }
    }

    public class ItemTextReceiveHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemReceivedTextViewBinding mBinding;

        public ItemTextReceiveHolder(ItemReceivedTextViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.textMessage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mBinding.textTime.setVisibility(mBinding.textTime.getVisibility() == View.VISIBLE ?
                    View.GONE : View.VISIBLE);
        }

        @Override
        public void onBind(int position) {
            ChatItemTextReceiveViewModel chatItemTextReceiveViewModel =
                    new ChatItemTextReceiveViewModel(mChatItemViewModels.get(position));
            mBinding.setViewModel(chatItemTextReceiveViewModel);
            mBinding.executePendingBindings();
        }
    }

    public class ItemImageReceiveHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemReceivedImageViewBinding mBinding;

        public ItemImageReceiveHolder(ItemReceivedImageViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public void onBind(int position) {
            ChatItemImageReceiveViewModel chatItemImageReceiveViewModel =
                    new ChatItemImageReceiveViewModel(mChatItemViewModels.get(position));
            mBinding.setViewModel(chatItemImageReceiveViewModel);
            mBinding.executePendingBindings();
        }
    }
}
