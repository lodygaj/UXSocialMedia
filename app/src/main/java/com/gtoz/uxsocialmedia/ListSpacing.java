package com.gtoz.uxsocialmedia;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ListSpacing extends RecyclerView.ItemDecoration {
    private final int verticalSpace; // This is the space between an item above and below
    private final int horizontalSpace; // This is the space between an item and the edges

    public ListSpacing(int vertSpace, int horiSpace) {
        verticalSpace = vertSpace;
        horizontalSpace = horiSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = outRect.right = horizontalSpace;
        outRect.bottom = verticalSpace;
    }
}

