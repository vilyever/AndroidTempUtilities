package com.vilyever.temputilities.RecyclerHelper.RadioController;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * RadioItemLayoutManager
 * ESchoolbag <com.ftet.base.RadioController>
 * Created by vilyever on 2016/3/11.
 * Feature:
 */
public class RadioItemLayoutManager extends LinearLayoutManager {
    final RadioItemLayoutManager self = this;


    /* Constructors */
    public RadioItemLayoutManager(Context context) {
        super(context);
    }

    public RadioItemLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public RadioItemLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    
    
    /* Public Methods */
    
    
    /* Properties */
    private boolean layoutCenter;
    public RadioItemLayoutManager setLayoutCenter(boolean layoutCenter) {
        if (layoutCenter != this.layoutCenter) {
            this.layoutCenter = layoutCenter;
            setCenterOffsetPadding(0);
            requestLayout();
        }
        return this;
    }
    public boolean isLayoutCenter() {
        return this.layoutCenter;
    }

    private int centerOffsetPadding;
    protected RadioItemLayoutManager setCenterOffsetPadding(int centerOffsetPadding) {
        this.centerOffsetPadding = centerOffsetPadding;
        return this; 
    }
    protected int getCenterOffsetPadding() {
        return this.centerOffsetPadding;
    }
    
    
    /* Overrides */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (isLayoutCenter()) {
            detachAndScrapAttachedViews(recycler);

            setCenterOffsetPadding(0);

            int totalWidth = 0;
            int totalHeight = 0;

            for (int i = 0; i < getItemCount(); i++) {
                View addButtonScrap = recycler.getViewForPosition(i);
                RecyclerView.LayoutParams addButtonLayoutParams = (RecyclerView.LayoutParams) addButtonScrap.getLayoutParams();
                addView(addButtonScrap);
                measureChildWithMargins(addButtonScrap, 0, 0);

                totalWidth += getDecoratedMeasuredWidth(addButtonScrap) + addButtonLayoutParams.leftMargin + addButtonLayoutParams.rightMargin;
                totalHeight += getDecoratedMeasuredHeight(addButtonScrap) + addButtonLayoutParams.topMargin + addButtonLayoutParams.bottomMargin;

                detachAndScrapView(addButtonScrap, recycler);

                if (getOrientation() == HORIZONTAL) {
                    if (totalWidth >= getHorizontalSpace()) {
                        break;
                    }
                }
                else {
                    if (totalHeight >= getVerticalSpace()) {
                        break;
                    }
                }
            }

            if (getOrientation() == HORIZONTAL
                && totalWidth < getHorizontalSpace()) {
                setCenterOffsetPadding((getHorizontalSpace() - totalWidth) / 2);
            }
            else if (getOrientation() == VERTICAL
                     && totalHeight < getVerticalSpace()) {
                setCenterOffsetPadding((getVerticalSpace() - totalHeight) / 2);
            }
        }

        super.onLayoutChildren(recycler, state);
    }

    @Override
    public int getPaddingLeft() {
        if (getOrientation() == HORIZONTAL
                && !getReverseLayout()) {
            return super.getPaddingLeft() + getCenterOffsetPadding();
        }
        return super.getPaddingLeft();
    }

    @Override
    public int getPaddingStart() {
        if (getOrientation() == HORIZONTAL
            && !getReverseLayout()) {
            return super.getPaddingStart() + getCenterOffsetPadding();
        }
        return super.getPaddingStart();
    }

    @Override
    public int getPaddingTop() {
        if (getOrientation() == VERTICAL
            && !getReverseLayout()) {
            return super.getPaddingTop() + getCenterOffsetPadding();
        }
        return super.getPaddingTop();
    }

    @Override
    public int getPaddingRight() {
        if (getOrientation() == HORIZONTAL
            && !getReverseLayout()) {
            return super.getPaddingRight() + getCenterOffsetPadding();
        }
        return super.getPaddingRight();
    }

    @Override
    public int getPaddingEnd() {
        if (getOrientation() == HORIZONTAL
            && !getReverseLayout()) {
            return super.getPaddingEnd() + getCenterOffsetPadding();
        }
        return super.getPaddingEnd();
    }

    @Override
    public int getPaddingBottom() {
        if (getOrientation() == VERTICAL
            && !getReverseLayout()) {
            return super.getPaddingBottom() + getCenterOffsetPadding();
        }
        return super.getPaddingBottom();
    }

    /* Delegates */
     
     
    /* Private Methods */
    /**
     * 容器去除padding后的宽度
     *
     * @return 实际可摆放item的空间
     */
    private int getHorizontalSpace() {
        return getWidth() - getPaddingRight() - getPaddingLeft();
    }

    /**
     * 容器去除padding后的高度
     *
     * @return 实际可摆放item的空间
     */
    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }
    
}