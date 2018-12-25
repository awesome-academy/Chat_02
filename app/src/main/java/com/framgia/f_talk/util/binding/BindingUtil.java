package com.framgia.f_talk.util.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.f_talk.R;
import com.framgia.f_talk.screen.chat.ChatAdapter;
import com.framgia.f_talk.screen.chat.ChatItemViewModel;
import com.framgia.f_talk.screen.home.group.GroupAdapter;
import com.framgia.f_talk.screen.home.group.GroupItemViewModel;
import com.framgia.f_talk.screen.home.recent.RecentAdapter;
import com.framgia.f_talk.screen.home.recent.RecentItemViewModel;
import com.framgia.f_talk.util.Constant;

import java.util.List;

public class BindingUtil {
    @BindingAdapter({"layoutManager"})
    public static void setLayoutManagerForRecyclerView(RecyclerView recyclerView,
                                                       RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"setVisibility"})
    public static void setVisibleForView(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
            return;
        }
        view.setVisibility(View.GONE);
    }

    @BindingAdapter({"adapter"})
    public static void addRecentItem(RecyclerView recyclerView, List<RecentItemViewModel>
            recentItemViewModels) {
        RecentAdapter adapter = (RecentAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(recentItemViewModels);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addGroupItem(RecyclerView recyclerView, List<GroupItemViewModel>
            groupItemViewModels) {
        GroupAdapter adapter = (GroupAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(groupItemViewModels);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addChatItem(RecyclerView recyclerView, List<ChatItemViewModel>
            chatItemViewModels) {
        ChatAdapter adapter = (ChatAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(chatItemViewModels);
        }
    }

    @BindingAdapter("imageSrc")
    public static void setImageSrc(ImageView imageView, String url) {
        Context context = imageView.getContext();
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.image_recent_default)
                .error(R.drawable.image_recent_default)
                .circleCrop();
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    @BindingAdapter("imageMessageContentSrc")
    public static void setImageMessageContentSrc(ImageView imageView, String url) {
        Context context = imageView.getContext();
        RequestOptions requestOptions = new RequestOptions().transforms(new CenterCrop(),
                new RoundedCorners(Constant.IMAGE_MESSAGE_ROUNDING_RADIUS))
                .placeholder(R.drawable.image_recent_default)
                .error(R.drawable.image_recent_default);
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }
}