package com.ldd.transport.common;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 分割线 底部添加
 */
public class BottomItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public BottomItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
    }
}
