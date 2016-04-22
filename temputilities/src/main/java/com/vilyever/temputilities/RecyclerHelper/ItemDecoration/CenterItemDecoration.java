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
public class CenterItemDecoration extends DividerItemDecoration {
    final CenterItemDecoration self = this;

    /* Constructors */
    public CenterItemDecoration() {
        this(false);
    }

    public CenterItemDecoration(boolean isSeparate) {
        this(isSeparate, 0, 0);
    }

    public CenterItemDecoration(int innerSpace) {
        this(false, innerSpace, 0);
    }

    public CenterItemDecoration(boolean isSeparate, int outerSpace) {
        this(isSeparate, 0, outerSpace);
    }

    public CenterItemDecoration(boolean isSeparate, int innerSpace, int outerSpace) {
        this.separate = isSeparate;
        this.innerSpace = innerSpace;
        this.outerSpace = outerSpace;
    }
    
    /* Properties */
    private boolean separate;
    public CenterItemDecoration setSeparate(boolean separate) {
        this.separate = separate;
        return this;
    }
    public boolean isSeparate() {
        return this.separate;
    }

    private int innerSpace;
    public CenterItemDecoration setInnerSpace(int innerSpace) {
        this.innerSpace = innerSpace;
        return this;
    }
    public int getInnerSpace() {
        return this.innerSpace;
    }

    private int outerSpace;
    public CenterItemDecoration setOuterSpace(int outerSpace) {
        this.outerSpace = outerSpace;
        return this;
    }
    public int getOuterSpace() {
        return this.outerSpace;
    }

    /* Overrides */
    @Override
    public boolean isEdgeSpaceEqualInnerSpace() {
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (!parent.getLayoutManager().getClass().equals(LinearLayoutManager.class)) {
            return;
        }

        int position = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        int parentHorizontalSpace = parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight();
        int parentVerticalSpace = parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom();

        int itemLength = 0;
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        boolean isVertical = layoutManager.getOrientation() == LinearLayoutManager.VERTICAL;
        if (isVertical) {
            view.measure(View.MeasureSpec.makeMeasureSpec(parentHorizontalSpace, View.MeasureSpec.AT_MOST), View.MeasureSpec.UNSPECIFIED);
            itemLength = view.getMeasuredHeight() + layoutManager.getTopDecorationHeight(view) + layoutManager.getBottomDecorationHeight(view);
            if (itemLength * itemCount > parentVerticalSpace) {
                return;
            }
        }
        else {
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.makeMeasureSpec(parentVerticalSpace, View.MeasureSpec.AT_MOST));
            itemLength = view.getMeasuredWidth() + layoutManager.getLeftDecorationWidth(view) + layoutManager.getRightDecorationWidth(view);
            if (itemLength * itemCount > parentHorizontalSpace) {
                return;
            }
        }

        boolean isSeparate = isSeparate();
        if (itemCount == 1) {
            isSeparate = false;
        }

        if (!isSeparate) {
            if (isVertical) {
                setVerticalSpace(getInnerSpace());

                int outerSpace = (parentVerticalSpace - itemLength * itemCount - getInnerSpace() * (itemCount - 1)) / 2;
                setEdgeSpace(0, outerSpace, 0, outerSpace);
            }
            else {
                setHorizontalSpace(getInnerSpace());
                int outerSpace = (parentHorizontalSpace - itemLength * itemCount - getInnerSpace() * (itemCount - 1)) / 2;
                setEdgeSpace(outerSpace, 0, outerSpace, 0);
            }
        }
        else {
            if (isVertical) {
                setEdgeSpace(0, getOuterSpace(), 0, getOuterSpace());
                int innerSpace = itemCount <= 1 ? 0 : (parentVerticalSpace - itemLength * itemCount - getOuterSpace() * 2) / (itemCount - 1);
                setVerticalSpace(innerSpace);
            }
            else {
                setEdgeSpace(getOuterSpace(), 0, getOuterSpace(), 0);
                int innerSpace = itemCount <= 1 ? 0 : (parentHorizontalSpace - itemLength * itemCount - getOuterSpace() * 2) / (itemCount - 1);
                setHorizontalSpace(innerSpace);
            }
        }

        super.getItemOffsets(outRect, view, parent, state);
    }
    
    
}