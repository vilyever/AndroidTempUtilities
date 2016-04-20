package com.vilyever.temputilities.UI.LoadingView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import com.vilyever.unitconversion.DimenConverter;

/**
 * LoadingAngularVariationDrawable
 * AndroidTempUtilities <com.vilyever.androidtemputilities.utilities.UI.LoadingView>
 * Created by vilyever on 2016/4/14.
 * Feature:
 */
public class LoadingAngularVariationDrawable extends LoadingDrawable {
    final LoadingAngularVariationDrawable self = this;

    private static final long DefaultDuration = 1500L;

    private static final int DefaultColor = Color.parseColor("#1E90FF");
    private static final float DefaultRadius = DimenConverter.dpToPixel(25);
    private static final float DefaultTorusWidth = DimenConverter.dpToPixel(5);
    private static final int DefaultMinAngular = 10;
    private static final int DefaultMaxAngular = 300;
    private static final int DefaultLeapAngularEachRepeat = 360 * 2 + 120;


    /* Constructors */


    /* Public Methods */


    /* Properties */
    private int minAngular = DefaultMinAngular;
    public LoadingAngularVariationDrawable setMinAngular(int minAngular) {
        minAngular = Math.max(minAngular, 0);
        minAngular = Math.min(minAngular, 360);
        minAngular = Math.min(minAngular, getMaxAngular());
        this.minAngular = minAngular;
        return this;
    }
    public int getMinAngular() {
        return this.minAngular;
    }

    private int maxAngular = DefaultMaxAngular;
    public LoadingAngularVariationDrawable setMaxAngular(int maxAngular) {
        maxAngular = Math.max(maxAngular, 0);
        maxAngular = Math.min(maxAngular, 360);
        maxAngular = Math.max(maxAngular, getMinAngular());
        this.maxAngular = maxAngular;
        return this;
    }
    public int getMaxAngular() {
        return this.maxAngular;
    }

    private int leapAngular = DefaultLeapAngularEachRepeat;
    public LoadingAngularVariationDrawable setLeapAngular(int leapAngular) {
        this.leapAngular = leapAngular;
        return this;
    }
    public int getLeapAngular() {
        return this.leapAngular;
    }

    private float radius = DefaultRadius;
    public LoadingAngularVariationDrawable setRadius(float radius) {
        this.radius = radius;
        getHolderView().requestLayout();
        return this;
    }
    public float getRadius() {
        return this.radius;
    }

    private float torusWidth = DefaultTorusWidth;
    public LoadingAngularVariationDrawable setTorusWidth(float torusWidth) {
        this.torusWidth = torusWidth;
        getAnimationPaint().setStrokeWidth(this.torusWidth);
        getHolderView().requestLayout();
        return this;
    }
    public float getTorusWidth() {
        return this.torusWidth;
    }

    private int color = DefaultColor;
    public LoadingAngularVariationDrawable setColor(int color) {
        this.color = color;
        getAnimationPaint().setColor(this.color);
        return this;
    }
    public int getColor() {
        return this.color;
    }

    /**
     * 动画绘制矩形
     * 因onDraw调用频繁，不宜在onDraw内new新对象
     */
    private RectF animationRect;
    private RectF getAnimationRect() {
        if (this.animationRect == null) {
            this.animationRect = new RectF();
        }
        return this.animationRect;
    }

    private Path animationPath;
    private Path getAnimationPath() {
        if (this.animationPath == null) {
            this.animationPath = new Path();
        }
        return this.animationPath;
    }

    private Paint animationPaint;
    private Paint getAnimationPaint() {
        if (this.animationPaint == null) {
            this.animationPaint = new Paint();
            this.animationPaint.setAntiAlias(true);
            this.animationPaint.setDither(true);
            this.animationPaint.setStyle(Paint.Style.STROKE);
            this.animationPaint.setStrokeJoin(Paint.Join.ROUND);
            this.animationPaint.setStrokeCap(Paint.Cap.ROUND);
            this.animationPaint.setStrokeWidth(getTorusWidth());
            this.animationPaint.setColor(getColor());
        }
        return this.animationPaint;
    }

    /* Overrides */
    @Override
    public int getPreferWidth() {
        return (int) ((getRadius() + getTorusWidth()) * 2);
    }

    @Override
    public int getPreferHeight() {
        return getPreferWidth();
    }

    @Override
    public void draw(Canvas canvas) {
        final int saveCount = canvas.save();

        int centerX = getHolderView().getWidth() / 2;
        int centerY = getHolderView().getHeight() / 2;

        float radius = getRadius();
        radius = Math.min(radius, getHolderView().getWidth());
        radius = Math.min(radius, getHolderView().getHeight());

        radius -= getTorusWidth() / 2;

        getAnimationRect().set(centerX - radius,
                               centerY - radius,
                               centerX + radius,
                               centerY + radius);

        float startAngle = getLeapAngular() * (getAnimationRepeatTimes()) % 360;
        float forwardAngle = 0.0f;
        float sweepAngle = 0.0f;

        float slowProgress = 0.15f;
        float fastProgress = 1.0f - slowProgress * 2.0f;
        float slowLeapAngle = getLeapAngular() * 0.1f;
        float collapseLeapAngle = getLeapAngular() * 0.1f;
        float fastLeapAngle = getLeapAngular() - slowLeapAngle - collapseLeapAngle;

        float minAngular = Math.min(getMinAngular(), getLeapAngular());
        float maxAngular = Math.min(getMaxAngular(), fastLeapAngle);

        if (getProgress() < slowProgress) {
            float progress = getProgress() / slowProgress;
            forwardAngle = (startAngle + slowLeapAngle * progress) % 360;
            if (getAnimationRepeatTimes() == 0) {
                sweepAngle = (minAngular * progress) % 360;
            }
            else {
                sweepAngle = minAngular;
            }
        }
        else if (getProgress() < fastProgress) {
            float progress = (getProgress() - slowProgress) / (fastProgress - slowProgress);
            forwardAngle = (startAngle + slowLeapAngle + fastLeapAngle * progress) % 360;
            sweepAngle = (minAngular + (maxAngular - minAngular) * progress) % 360;
        }
        else {
            float progress = (getProgress() - fastProgress) / (1.0f - fastProgress);
            forwardAngle = (startAngle + slowLeapAngle + fastLeapAngle + collapseLeapAngle * progress) % 360;
            sweepAngle = (maxAngular - (maxAngular - minAngular) * progress) % 360;
        }

        getAnimationPath().reset();
        getAnimationPath().addArc(getAnimationRect(), forwardAngle, -sweepAngle);

        canvas.drawPath(getAnimationPath(), getAnimationPaint());

        canvas.restoreToCount(saveCount);
    }

    @Override
    protected long internalGainAnimationDuration() {
        return DefaultDuration;
    }

    /* Delegates */
    
    
    /* Private Methods */
    
}