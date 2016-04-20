package com.vilyever.temputilities.UI.LoadingView;

import android.graphics.Canvas;
import android.graphics.Color;

import com.vilyever.unitconversion.DimenConverter;

/**
 * LoadingColorBarDrawable
 * AndroidTempUtilities <com.vilyever.androidtemputilities.utilities.UI.LoadingView>
 * Created by vilyever on 2016/4/15.
 * Feature:
 */
public class LoadingColorBarDrawable extends LoadingDrawable {
    final LoadingColorBarDrawable self = this;

    private static final long DefaultDuration = 500L;

    private static final int DefaultWidth = DimenConverter.dpToPixel(320);
    private static final int DefaultHeight = DimenConverter.dpToPixel(3);

    /* Constructors */
    public LoadingColorBarDrawable() {
        this(Color.parseColor("#F44336"),
             Color.parseColor("#FFEB3B"),
             Color.parseColor("#03A9F4"),
             Color.parseColor("#4CAF50")
             );
    }

    public LoadingColorBarDrawable(int ... colors) {
        super();
        setColors(colors);
    }

    /* Public Methods */


    /* Properties */
    private int[] colors;
    public LoadingColorBarDrawable setColors(int ... colors) {
        this.colors = colors;
        return this;
    }
    public int[] getColors() {
        return this.colors;
    }
    
    /* Overrides */
    @Override
    public int getPreferWidth() {
        return DefaultWidth;
    }

    @Override
    public int getPreferHeight() {
        return DefaultHeight;
    }

    @Override
    public void draw(Canvas canvas) {
        if (getColors() == null) {
            return;
        }

        final int saveCount = canvas.save();

        int index = getAnimationRepeatTimes() % getColors().length;

        if (getAnimationRepeatTimes() != 0) {
            int preIndex = index - 1;
            preIndex = preIndex >= 0 ? preIndex : (getColors().length - 1);
            canvas.drawColor(getColors()[preIndex]);
        }

        int centerX = getHolderView().getWidth() / 2;
        int centerY = getHolderView().getHeight() / 2;
        float drawWidth = getHolderView().getWidth() / 2.0f * getProgress();
        canvas.clipRect(centerX - drawWidth, 0, centerX + drawWidth, getHolderView().getHeight());
        canvas.drawColor(getColors()[index]);

        canvas.restoreToCount(saveCount);
    }

    @Override
    protected long internalGainAnimationDuration() {
        return DefaultDuration;
    }
    
    /* Delegates */
    
    
    /* Private Methods */
    
}