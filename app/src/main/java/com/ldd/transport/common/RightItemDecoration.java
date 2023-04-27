package com.ldd.transport.common;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 分割线 右侧添加
 */
public class RightItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public RightItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = space;
    }
}
