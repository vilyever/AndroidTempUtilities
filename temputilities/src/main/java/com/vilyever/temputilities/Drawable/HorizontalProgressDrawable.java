package com.vilyever.temputilities.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * HorizontalProgressDrawable
 * ESchoolbag <com.ftet.recyclerdisplay.animation>
 * Created by vilyever on 2016/3/8.
 * Feature:
 */
public class HorizontalProgressDrawable extends Drawable implements Animatable {
    final HorizontalProgressDrawable self = this;

    private final static int DefaultAnimationDuration = 600;

    /* Constructors */
    public HorizontalProgressDrawable(View parent) {
        this.parent = parent;
    }
    
    /* Public Methods */

    /* Properties */
    private final View parent;
    protected View getParent() {
        return this.parent;
    }

    private int[] colors;
    protected HorizontalProgressDrawable setColors(int... colors) {
        this.colors = colors;
        return this;
    }
    protected int[] getColors() {
        return this.colors;
    }
    
    private Animation progressAnimation;
    protected Animation getProgressAnimation() {
        if (this.progressAnimation == null) {
            this.progressAnimation = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    self.setProgress(interpolatedTime);
                }
            };
            this.progressAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    self.setAnimationIndex((getAnimationIndex() + 1) % getColors().length);
                    self.setFirstRunAnimation(false);
                }
            });
            this.progressAnimation.setDuration(DefaultAnimationDuration);
            this.progressAnimation.setRepeatCount(Animation.INFINITE);
            this.progressAnimation.setRepeatMode(Animation.RESTART);
            this.progressAnimation.setInterpolator(new LinearInterpolator());
        } 
        return this.progressAnimation;
    }

    private boolean animating;
    protected HorizontalProgressDrawable setAnimating(boolean animating) {
        this.animating = animating;
        return this;
    }
    protected boolean isAnimating() {
        return this.animating;
    }

    private float progress;
    protected HorizontalProgressDrawable setProgress(float progress) {
        this.progress = progress;
        invalidateSelf();
        return this;
    }
    protected float getProgress() {
        return this.progress;
    }
    
    private int animationIndex;
    protected HorizontalProgressDrawable setAnimationIndex(int animationIndex) {
        this.animationIndex = animationIndex;
        return this; 
    }
    protected int getAnimationIndex() {
        return this.animationIndex;
    }

    private boolean firstRunAnimation;
    protected HorizontalProgressDrawable setFirstRunAnimation(boolean firstRunAnimation) {
        this.firstRunAnimation = firstRunAnimation;
        return this;
    }
    protected boolean isFirstRunAnimation() {
        return this.firstRunAnimation;
    }
    
    private Paint progressPaint;
    protected Paint getProgressPaint() { 
        if (this.progressPaint == null) {
            this.progressPaint = new Paint();
            this.progressPaint.setAntiAlias(true);
            this.progressPaint.setDither(true);
            this.progressPaint.setStyle(Paint.Style.FILL);
        }
        return this.progressPaint; 
    }
    
    
    /* Overrides */
    @Override
    public void draw(Canvas canvas) {
        if (getColors() == null) {
            return;
        }

        setAnimationIndex(getAnimationIndex() % getColors().length);
        int currentColor = getColors()[getAnimationIndex()];
        int preColor = getColors()[(getAnimationIndex() - 1 < 0 ? getColors().length - 1 : getAnimationIndex() - 1) % getColors().length];

        final Rect bounds = getBounds();
        RectF block = new RectF(0, 0, bounds.width(), bounds.height());

        getProgressPaint().setColor(preColor);
        canvas.drawRect(block, getProgressPaint());

        float width = bounds.width() * getProgress();
        block.left = (bounds.width() - width) / 2.0f;
        block.right = block.left + width;

        getProgressPaint().setColor(currentColor);
        canvas.drawRect(block, getProgressPaint());
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        if (visible && isAnimating()) {
            start();
        }
        return super.setVisible(visible, restart);
    }

    /* Delegates */
    /** {@link Animatable} */
    @Override
    public void start() {
        getProgressAnimation().reset();
        setAnimationIndex(0);
        setFirstRunAnimation(true);
        setAnimating(true);
        getParent().startAnimation(getProgressAnimation());
    }

    @Override
    public void stop() {
        getParent().clearAnimation();
        setAnimating(false);
    }

    @Override
    public boolean isRunning() {
        return isAnimating();
    }
     
     
    /* Private Methods */
    
}