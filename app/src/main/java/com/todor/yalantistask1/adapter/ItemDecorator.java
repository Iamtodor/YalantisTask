package com.todor.yalantistask1.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.todor.yalantistask1.App;

public class ItemDecorator extends RecyclerView.ItemDecoration {

    private int space;

    public ItemDecorator() {
        space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, App.getContext().getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = 0;
            outRect.right = space;
        } else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.right = 0;
            outRect.left = space;
        } else {
            outRect.left = space;
            outRect.right = space;
        }
    }

}
