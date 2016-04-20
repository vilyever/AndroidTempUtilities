package com.vilyever.temputilities.UI.LoadingView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * LoadingView
 * AndroidTempUtilities <com.vilyever.androidtemputilities.utilities.UI.LoadingView>
 * Created by vilyever on 2016/4/14.
 * Feature:
 */
public class LoadingView extends View {
    final LoadingView self = this;
    
    
    /* Constructors */
    public LoadingView(Context context) {
        super(context);
        init();
    }
    
    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    /* Public Methods */


    /* Properties */
    private LoadingDrawable loadingDrawable;
    public LoadingView setLoadingDrawable(LoadingDrawable loadingDrawable) {
        if (this.loadingDrawable != null) {
            this.loadingDrawable.stop();
            this.loadingDrawable.setHolderView(null);
        }
        this.loadingDrawable = loadingDrawable;
        requestLayout();
        if (this.loadingDrawable != null) {
            this.loadingDrawable.setHolderView(this);
            this.loadingDrawable.start();
        }
        return this;
    }
    public <T extends LoadingDrawable> T getLoadingDrawable() {
        if (this.loadingDrawable == null) {
            this.loadingDrawable = new LoadingAngularVariationDrawable();
            this.loadingDrawable.setHolderView(this);
            this.loadingDrawable.start();
        }
        return (T) this.loadingDrawable;
    }

    private boolean interceptTouchEvent = true;
    protected LoadingView setInterceptTouchEvent(boolean interceptTouchEvent) {
        this.interceptTouchEvent = interceptTouchEvent;
        return this;
    }
    public boolean shouldInterceptTouchEvent() {
        return this.interceptTouchEvent;
    }

    /* Overrides */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        getLoadingDrawable().draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return shouldInterceptTouchEvent();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = widthSize;
        int height = heightSize;

        if (widthMode != MeasureSpec.EXACTLY) {
            width = getLoadingDrawable().getPreferWidth();
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }
        if (heightMode != MeasureSpec.EXACTLY) {
            height = getLoadingDrawable().getPreferHeight();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }

        setMeasuredDimension(width, height);
    }
    
    /* Delegates */
    
    
    /* Private Methods */
    private void init() {
        
    }
    
}