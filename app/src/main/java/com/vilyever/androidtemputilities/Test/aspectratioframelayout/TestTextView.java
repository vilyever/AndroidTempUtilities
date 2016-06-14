package com.vilyever.androidtemputilities.Test.aspectratioframelayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * TestTextView
 * Created by vilyever on 2016/6/14.
 * Feature:
 */
public class TestTextView extends TextView {
    final TestTextView self = this;
    
    
    /* Constructors */
    public TestTextView(Context context) {
        super(context);
        internalInit();
    }
    
    public TestTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public TestTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        internalInit();
    }
    
    /* Public Methods */
    
    
    /* Properties */
    
    
    /* Overrides */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        final int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int UNSPECIFIEDMode = MeasureSpec.UNSPECIFIED;
        int EXACTLYMode = MeasureSpec.EXACTLY;
        int AT_MOSTMode = MeasureSpec.AT_MOST;

        int widthPadding = getPaddingLeft() + getPaddingRight();
        int heightPadding = getPaddingTop() + getPaddingBottom();

        int desireWidth = 0;
        int desireHeight = 0;

        if (heightSpecMode == MeasureSpec.EXACTLY) {
            desireHeight = heightSpecSize;
        }
        else {
            if (getLayoutParams().height >= 0) {
                desireHeight = getLayoutParams().height;
            }

            if (heightSpecMode == MeasureSpec.AT_MOST) {
                desireHeight = Math.min(desireHeight, heightSpecSize);
            }
        }

        if (widthSpecMode != MeasureSpec.EXACTLY) {
            desireWidth = (int) ((desireHeight - heightPadding) * 2 + widthPadding);
        }
        else {
            desireWidth = widthSpecSize;
        }

        if (widthSpecMode == MeasureSpec.AT_MOST) {
            desireWidth = Math.min(desireWidth, widthSpecSize);
        }

        setMeasuredDimension(desireWidth, desireHeight);

//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /* Delegates */
    
    
    /* Private Methods */
    private void internalInit() {
        
    }
    
}