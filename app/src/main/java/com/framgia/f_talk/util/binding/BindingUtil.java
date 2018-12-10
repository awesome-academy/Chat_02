package com.framgia.f_talk.util.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.f_talk.screen.home.recent.RecentAdapter;
import com.framgia.f_talk.screen.home.recent.RecentItemViewModel;

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

    @BindingAdapter("imageSrc")
    public static void setImageSrc(ImageView imageView, String url) {
        Context context = imageView.getContext();
        RequestOptions requestOptions = new RequestOptions()
                .circleCrop();
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }
}
