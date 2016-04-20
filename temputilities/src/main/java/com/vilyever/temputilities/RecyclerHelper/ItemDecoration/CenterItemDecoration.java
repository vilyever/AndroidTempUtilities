package com.vilyever.temputilities.RecyclerHelper.ItemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * CenterItemDecoration
 * ESchoolbag <com.ftet.base>
 * Created by vilyever on 2016/3/10.
 * Feature:
 * 若item总宽度不能填满RecyclerView，则将所有item居中
 * 注意：暂时只支持{@link LinearLayoutManager}
 * 注意：此ItemDecoration假设所有item包含其他ItemDecoration的宽高都相同
 * 注意：若RecyclerView不止有一个ItemDecoration，需将此ItemDecoration放置到最后
 */
public class CenterItemDecoration extends RecyclerView.ItemDecoration {
    final CenterItemDecoration self = this;
    
    
    /* Overrides */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //We can supply forced insets for each item view here in the Rect
        int position = parent.getChildAdapterPosition(view);

        int left, top, right, bottom;
        left = top = right = bottom = 0;

        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();

            if (position == 0) {
                if (layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    int itemWidth = view.getLayoutParams().width;
                    if (itemWidth >= 0) {
                        view.measure(View.MeasureSpec.makeMeasureSpec(itemWidth, View.MeasureSpec.EXACTLY), View.MeasureSpec.UNSPECIFIED);
                    }
                    else {
                        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                    }

                    itemWidth = view.getMeasuredWidth() + layoutManager.getLeftDecorationWidth(view) + layoutManager.getRightDecorationWidth(view);

                    if (!layoutManager.getReverseLayout()) {
                        left = Math.max(left, (parent.getWidth() - itemWidth * parent.getAdapter().getItemCount()) / 2);
                    }
                    else {
                        right = Math.max(right, (parent.getWidth() - itemWidth * parent.getAdapter().getItemCount()) / 2);
                    }
                }
                else {
                    int itemHeight = view.getLayoutParams().height;
                    if (itemHeight >= 0) {
                        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.makeMeasureSpec(itemHeight, View.MeasureSpec.EXACTLY));
                    }
                    else {
                        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                    }

                    itemHeight = view.getMeasuredHeight() + layoutManager.getTopDecorationHeight(view) + layoutManager.getBottomDecorationHeight(view);

                    if (!layoutManager.getReverseLayout()) {
                        top = Math.max(top, (parent.getHeight() - itemHeight * parent.getAdapter().getItemCount()) / 2);
                    }
                    else {
                        bottom = Math.max(bottom, (parent.getHeight() - itemHeight * parent.getAdapter().getItemCount()) / 2);
                    }
                }
            }
        }

        outRect.set(left, top, right, bottom);
    }
    
    
}