package com.vilyever.temputilities.UI.EditTextLayout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * EditTextLayout
 * AndroidTempUtilities <com.vilyever.androidtemputilities.utilities.UIHelper>
 * Created by vilyever on 2016/4/13.
 * Feature:
 */
public class EditTextLayout extends LinearLayout {
    final EditTextLayout self = this;

    /* Constructors */
    public EditTextLayout(Context context) {
        this(context, null);
    }

    public EditTextLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.floatingTipEnabled = true;
        this.floatingTipColor = Color.RED;
        this.floatingTipTypeface = Typeface.DEFAULT;
        this.floatingTipSize = 50;
    }
    
    /* Public Methods */
    public boolean isDisplayFloatingTip() {
        return isFloatingTipEnabled() && getFloatingTip() != null && getEditText().getText().length() > 0;
    }

    public int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    public int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    /* Properties */
    private EditText editText;
    protected EditTextLayout setEditText(EditText editText) {
        if (this.editText != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        this.editText = editText;
        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                self.internalUpdateDisplay();
            }
        });

        if (getFloatingTip() == null && this.editText.getHint() != null) {
            setFloatingTip(this.editText.getHint().toString());
        }

        internalUpdateLayout();
        return this;
    }
    public EditText getEditText() {
        return this.editText;
    }

    private FloatingTipDrawable floatingTipDrawable;
    protected FloatingTipDrawable getFloatingTipDrawable() {
        if (this.floatingTipDrawable == null) {
            this.floatingTipDrawable = new FloatingTipDrawable(this);
        }
        return this.floatingTipDrawable;
    }

    private boolean floatingTipEnabled;
    public EditTextLayout setFloatingTipEnabled(boolean floatingTipEnabled) {
        this.floatingTipEnabled = floatingTipEnabled;
        internalUpdateLayout();
        return this;
    }
    public boolean isFloatingTipEnabled() {
        return this.floatingTipEnabled;
    }

    private String floatingTip;
    public EditTextLayout setFloatingTip(String floatingTip) {
        this.floatingTip = floatingTip;
        internalUpdateDisplay();
        return this; 
    }
    public String getFloatingTip() {
        return this.floatingTip;
    }
    
    private int floatingTipColor;
    public EditTextLayout setFloatingTipColor(int floatingTipColor) {
        this.floatingTipColor = floatingTipColor;
        internalUpdateDisplay();
        return this; 
    }
    public int getFloatingTipColor() {
        return this.floatingTipColor;
    }

    private Typeface floatingTipTypeface;
    protected EditTextLayout setFloatingTipTypeface(Typeface floatingTipTypeface) {
        this.floatingTipTypeface = floatingTipTypeface;
        return this;
    }
    public Typeface getFloatingTipTypeface() {
        return this.floatingTipTypeface;
    }

    private float floatingTipSize;
    public EditTextLayout setFloatingTipSize(float floatingTipSize) {
        return setFloatingTipSize(TypedValue.COMPLEX_UNIT_SP, floatingTipSize);
    }
    public EditTextLayout setFloatingTipSize(int unit, float floatingTipSize) {
        Context c = getContext();
        Resources r = (c == null) ? Resources.getSystem() : c.getResources();

        this.floatingTipSize = TypedValue.applyDimension(
                unit, floatingTipSize, r.getDisplayMetrics());

        internalUpdateLayout();
        return this;
    }
    public float getFloatingTipSize() {
        return this.floatingTipSize;
    }
    
    /* Overrides */
    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof EditText) {
            super.addView(child, 0, params);
            setEditText((EditText) child);
        } else {
            // Carry on adding the View...
            super.addView(child, index, params);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        getFloatingTipDrawable().draw(canvas);
    }

    /* Delegates */
     

    /* Protected Methods */
    protected void internalUpdateLayout() {
        if (getEditText() == null) {
            return;
        }

        LayoutParams layoutParams;

        if (getEditText().getLayoutParams() instanceof LayoutParams) {
            layoutParams = (LayoutParams) getEditText().getLayoutParams();
        }
        else {
            layoutParams = new LayoutParams(getEditText().getLayoutParams());
        }
        if (isFloatingTipEnabled()) {
            layoutParams.topMargin = (int) (getFloatingTipDrawable().updatePaint().descent() - getFloatingTipDrawable().updatePaint().ascent());
        }
        else {
            layoutParams.topMargin = 0;
        }
    }

    protected void internalUpdateDisplay() {
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* Private Methods */
}