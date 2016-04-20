package com.vilyever.temputilities.UI.LoadingView;

import android.graphics.ColorFilter;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * LoadingDrawable
 * AndroidTempUtilities <com.vilyever.androidtemputilities.utilities.UI.LoadingView>
 * Created by vilyever on 2016/4/14.
 * Feature:
 */
public abstract class LoadingDrawable extends Drawable implements Animatable, Animation.AnimationListener {
    final LoadingDrawable self = this;

    protected final static int DefaultAnimationDuration = 1000;
    
    /* Constructors */

    /* Public Methods */
    public abstract int getPreferWidth();
    public abstract int getPreferHeight();

    /* Properties */
    private LoadingView holderView;
    public LoadingDrawable setHolderView(LoadingView holderView) {
        this.holderView = holderView;
        return this;
    }
    public LoadingView getHolderView() {
        return this.holderView;
    }

    private boolean animated;
    protected LoadingDrawable setAnimated(boolean animated) {
        this.animated = animated;
        return this;
    }
    protected boolean isAnimated() {
        return this.animated;
    }

    private Animation progressAnimation;
    protected final Animation getProgressAnimation() {
        if (this.progressAnimation == null) {
            this.progressAnimation = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    self.setProgress(interpolatedTime);
                }
            };
            this.progressAnimation.setAnimationListener(this);

            internalSetupProgressAnimation(this.progressAnimation);
        }
        return this.progressAnimation;
    }

    private float progress;
    @CallSuper
    protected LoadingDrawable setProgress(float progress) {
        this.progress = progress;
        ViewCompat.postInvalidateOnAnimation(getHolderView());
        return this;
    }
    protected float getProgress() {
        return this.progress;
    }

    private int animationRepeatTimes;
    protected LoadingDrawable setAnimationRepeatTimes(int animationRepeatTimes) {
        this.animationRepeatTimes = animationRepeatTimes;
        return this;
    }
    protected int getAnimationRepeatTimes() {
        return this.animationRepeatTimes;
    }

    private long animationDuration;
    public LoadingDrawable setAnimationDuration(long animationDuration) {
        this.animationDuration = animationDuration;
        getProgressAnimation().setDuration(animationDuration);
        return this;
    }
    public long getAnimationDuration() {
        return this.animationDuration;
    }

    /* Overrides */
    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        if (visible && isAnimated()) {
            start();
        }
        return super.setVisible(visible, restart);
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
    
    /* Delegates */
    /** {@link Animatable} */
    @Override
    @CallSuper
    public void start() {
        getProgressAnimation().reset();
        setAnimationRepeatTimes(0);
        setAnimated(true);
        getHolderView().startAnimation(getProgressAnimation());
    }

    @Override
    @CallSuper
    public void stop() {
        getProgressAnimation().cancel();
        getHolderView().clearAnimation();
        setAnimated(false);
    }

    @Override
    public boolean isRunning() {
        return isAnimated();
    }

    /** {@link Animation.AnimationListener} */
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    @CallSuper
    public void onAnimationRepeat(Animation animation) {
        setAnimationRepeatTimes(getAnimationRepeatTimes() + 1);
    }

    /* Protected Methods */
    @CallSuper
    protected void internalSetupProgressAnimation(Animation progressAnimation) {
        progressAnimation.setDuration(internalGainAnimationDuration());
        progressAnimation.setRepeatCount(internalGainAnimationRepeatCount());
        progressAnimation.setRepeatMode(internalGainAnimationRepeatMode());
        progressAnimation.setInterpolator(internalGainAnimationInterpolator());
    }

    protected long internalGainAnimationDuration() {
        return DefaultAnimationDuration;
    }
    protected int internalGainAnimationRepeatCount() {
        return Animation.INFINITE;
    }
    protected int internalGainAnimationRepeatMode() {
        return Animation.RESTART;
    }
    protected Interpolator internalGainAnimationInterpolator() {
        return new LinearInterpolator();
    }

    /* Private Methods */
    
}