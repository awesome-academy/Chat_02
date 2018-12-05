package com.framgia.f_talk.util.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
}
