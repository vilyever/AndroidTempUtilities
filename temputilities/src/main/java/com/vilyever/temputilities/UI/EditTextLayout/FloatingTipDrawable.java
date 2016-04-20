package com.vilyever.temputilities.UI.EditTextLayout;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;

/**
 * FloatingTipDrawable
 * AndroidTempUtilities <com.vilyever.androidtemputilities.utilities.UI.EditTextLayout>
 * Created by vilyever on 2016/4/13.
 * Feature:
 */
public class FloatingTipDrawable extends Drawable {
    final FloatingTipDrawable self = this;


    /* Constructors */
    public FloatingTipDrawable(EditTextLayout parent) {
        this.parent = parent;
    }


    /* Public Methods */
    public void show() {
        setShow(true);
    }

    public void hide() {
        setShow(false);
    }

    public Paint updatePaint() {
        getTextPaint().setTypeface(getParent().getFloatingTipTypeface());
        getTextPaint().setTextSize(getParent().getFloatingTipSize());
        getTextPaint().setColor(getParent().getFloatingTipColor());

        return getTextPaint();
    }

    
    /* Properties */
    private final EditTextLayout parent;
    protected EditTextLayout getParent() {
        return this.parent;
    }

    private boolean show;
    protected FloatingTipDrawable setShow(boolean show) {
        this.show = show;
        ViewCompat.postInvalidateOnAnimation(getParent());
        return this;
    }
    public boolean isShow() {
        return this.show;
    }

    private TextPaint textPaint;
    protected TextPaint getTextPaint() {
        if (this.textPaint == null) {
            this.textPaint = new TextPaint();
            this.textPaint.setAntiAlias(true);
            this.textPaint.setDither(true);
        }
        return this.textPaint;
    }
    
    
    /* Overrides */
    @Override
    public void draw(Canvas canvas) {
        if (getParent().isDisplayFloatingTip()) {
            final int saveCount = canvas.save();

            updatePaint();

            float x = getParent().getPaddingLeft();
            float y = getParent().getPaddingTop();
            y += -getTextPaint().ascent();

            String textToDraw = TextUtils.ellipsize(getParent().getFloatingTip(), getTextPaint(), getParent().getHorizontalSpace(), TextUtils.TruncateAt.END).toString();

            canvas.drawText(textToDraw, 0, textToDraw.length(), x, y, getTextPaint());

            canvas.restoreToCount(saveCount);
        }
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
     
     
    /* Private Methods */
    
}