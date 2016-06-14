package com.vilyever.temputilities.UI.AspectRatioFrameLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.vilyever.temputilities.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * AspectRatioFrameLayout
 * ESchoolbag <com.ftet.base.UIHelper>
 * Created by vilyever on 2016/3/8.
 * Feature:
 * 自动根据比例固定一边改变另一边大小的layout
 */
public class AspectRatioFrameLayout extends FrameLayout {
    final AspectRatioFrameLayout self = this;

    /**
     * xml属性aspectRatio的默认值，{@link #aspectRatio}
     */
    // 不使用比例调整
    private static final float DefaultAspectRatioAttrsFlagValueNone = 0;
    // 宽高比为16：9
    private static final float DefaultAspectRatioAttrsFlagValue16to9 = -1;
    // 宽高比为9：16
    private static final float DefaultAspectRatioAttrsFlagValue9to16 = -2;
    // 宽高比为4：3
    private static final float DefaultAspectRatioAttrsFlagValue4to3 = -3;
    // 宽高比为3：4
    private static final float DefaultAspectRatioAttrsFlagValue3to4 = -4;

    /**
     * xml属性aspectRatio的默认值对应的具体比例，{@link #aspectRatio}
     */
    public static final float DefaultAspectRatioNone = 0.0f;
    public static final float DefaultAspectRatio16to9 = 16.0f / 9.0f;
    public static final float DefaultAspectRatio9to16 =  9.0f / 16.0f;
    public static final float DefaultAspectRatio4to3 = 4.0f / 3.0f;
    public static final float DefaultAspectRatio3to4 = 3.0f / 4.0f;

    /**
     * xml属性aspectRatioWidth的默认值，{@link #aspectRatioWidth}
     */
    private static final float DefaultAspectRatioWidthAttrsFlagValueNone = 0;
    public static final float DefaultAspectRatioWidthNone = 0.0f;

    /**
     * xml属性aspectRatioHeight的默认值，{@link #aspectRatioHeight}
     */
    private static final float DefaultAspectRatioHeightAttrsFlagValueNone = 0;
    public static final float DefaultAspectRatioHeightNone = 0.0f;

    /**
     * xml属性keepAspectDimension的预设值，{@link #keepAspectDimension}
     */
    @IntDef({KeepNone, KeepWidth, KeepHeight})
    @Retention(RetentionPolicy.SOURCE)
    public @interface KeepAspectDimension {}
    // 保持非wrap_content的宽或高不变
    public static final int KeepNone = 0;
    // 保持宽不变
    public static final int KeepWidth = 1;
    // 保持高不变
    public static final int KeepHeight = 2;

    /**
     * 最大最小宽度的失效值，即设置为此值最大最小宽度不影响自动调整
     */
    public static final int MinWidthNone = -1;
    public static final int MaxWidthNone = -1;
    public static final int MinHeightNone = -1;
    public static final int MaxHeightNone = -1;

    /* Constructors */
    public AspectRatioFrameLayout(Context context) {
        super(context);
        internalInit();
    }
    
    public AspectRatioFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public AspectRatioFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioFrameLayout);

        this.aspectRatio = MatchAspectRatioFromAttrFlag(a.getFloat(R.styleable.AspectRatioFrameLayout_aspectRatio, DefaultAspectRatioAttrsFlagValueNone));

        if (this.aspectRatio == DefaultAspectRatioNone) {
            this.aspectRatioWidth = a.getFloat(R.styleable.AspectRatioFrameLayout_aspectRatioWidth, DefaultAspectRatioWidthAttrsFlagValueNone);
            if (this.aspectRatioWidth == DefaultAspectRatioWidthAttrsFlagValueNone) {
                this.aspectRatioWidth = DefaultAspectRatioWidthNone;
            }
            this.aspectRatioHeight = a.getFloat(R.styleable.AspectRatioFrameLayout_aspectRatioHeight, DefaultAspectRatioHeightAttrsFlagValueNone);
            if (this.aspectRatioHeight == DefaultAspectRatioHeightAttrsFlagValueNone) {
                this.aspectRatioHeight = DefaultAspectRatioHeightNone;
            }

            if (this.aspectRatioWidth != DefaultAspectRatioWidthNone && this.aspectRatioHeight != DefaultAspectRatioHeightNone) {
                this.aspectRatio = this.aspectRatioWidth / this.aspectRatioHeight;
            }
        }

        this.keepAspectDimension = a.getInt(R.styleable.AspectRatioFrameLayout_keepAspectDimension, KeepNone);

        this.aspectRatioMinWidth = a.getLayoutDimension(R.styleable.AspectRatioFrameLayout_aspectRatioMinWidth, MinWidthNone);
        this.aspectRatioMaxWidth = a.getLayoutDimension(R.styleable.AspectRatioFrameLayout_aspectRatioMaxWidth, MaxWidthNone);
        this.aspectRatioMinHeight = a.getLayoutDimension(R.styleable.AspectRatioFrameLayout_aspectRatioMinHeight, MinHeightNone);
        this.aspectRatioMaxHeight = a.getLayoutDimension(R.styleable.AspectRatioFrameLayout_aspectRatioMaxHeight, MaxHeightNone);

        a.recycle();

        internalInit();
    }
    
    
    /* Public Methods */
    
    
    /* Properties */
    /**
     * 宽高比
     * 最终的宽高调整与此关联
     */
    private float aspectRatio = DefaultAspectRatioNone;
    public AspectRatioFrameLayout setAspectRatio(float aspectRatio) {
        if (this.aspectRatio != aspectRatio) {
            this.aspectRatio = aspectRatio;
            requestLayout();
        }
        return this;
    }
    public float getAspectRatio() {
        if (this.aspectRatio <= 0.0f) {
            this.aspectRatio = DefaultAspectRatioNone;
        }
        return this.aspectRatio;
    }

    /**
     * 宽高比中宽比例
     * 最终的宽高调整与此无关，此值在设定后会自动变更{@link #aspectRatio}
     */
    private float aspectRatioWidth = DefaultAspectRatioWidthNone;
    public AspectRatioFrameLayout setAspectRatioWidth(float aspectRatioWidth) {
        this.aspectRatioWidth = aspectRatioWidth;
        if (getAspectRatioHeight() != DefaultAspectRatioHeightNone) {
            setAspectRatio(aspectRatioWidth / getAspectRatioHeight());
        }
        return this;
    }
    public float getAspectRatioWidth() {
        return this.aspectRatioWidth;
    }

    /**
     * 宽高比中高比例
     * 最终的宽高调整与此无关，此值在设定后会自动变更{@link #aspectRatio}
     */
    private float aspectRatioHeight = DefaultAspectRatioHeightNone;
    public AspectRatioFrameLayout setAspectRatioHeight(float aspectRatioHeight) {
        this.aspectRatioHeight = aspectRatioHeight;
        if (getAspectRatioWidth() != DefaultAspectRatioHeightNone) {
            setAspectRatio(getAspectRatioWidth() / aspectRatioHeight);
        }
        return this;
    }
    public float getAspectRatioHeight() {
        return this.aspectRatioHeight;
    }

    /**
     * 宽高比自动布局中保持不变的维度（宽或高）
     * 最终的宽高调整与此关联
     * 如果宽或高指定为{@link android.view.ViewGroup.LayoutParams#MATCH_PARENT}或明确数值可能导致此设置失效或其它不可预料后果
     */
    private int keepAspectDimension;
    public AspectRatioFrameLayout setKeepAspectDimension(@KeepAspectDimension int keepAspectDimension) {
        if (this.keepAspectDimension != keepAspectDimension) {
            this.keepAspectDimension = keepAspectDimension;
            requestLayout();
        }
        return this;
    }
    public int getKeepAspectDimension() {
        return this.keepAspectDimension;
    }

    /**
     * 自动调整后的最小宽度
     * 最终的宽高调整与此关联
     */
    private int aspectRatioMinWidth = MinWidthNone;
    protected AspectRatioFrameLayout setAspectRatioMinWidth(int aspectRatioMinWidth) {
        if (this.aspectRatioMinWidth != aspectRatioMinWidth) {
            this.aspectRatioMinWidth = aspectRatioMinWidth;
            requestLayout();
        }
        return this;
    }
    protected int getAspectRatioMinWidth() {
        return this.aspectRatioMinWidth;
    }

    /**
     * 自动调整后的最大宽度
     * 最终的宽高调整与此关联
     */
    private int aspectRatioMaxWidth = MaxWidthNone;
    protected AspectRatioFrameLayout setAspectRatioMaxWidth(int aspectRatioMaxWidth) {
        if (this.aspectRatioMaxWidth != aspectRatioMaxWidth) {
            this.aspectRatioMaxWidth = aspectRatioMaxWidth;
            requestLayout();
        }
        return this;
    }
    protected int getAspectRatioMaxWidth() {
        return this.aspectRatioMaxWidth;
    }

    /**
     * 自动调整后的最小高度
     * 最终的宽高调整与此关联
     */
    private int aspectRatioMinHeight = MinHeightNone;
    protected AspectRatioFrameLayout setAspectRatioMinHeight(int aspectRatioMinHeight) {
        if (this.aspectRatioMinHeight != aspectRatioMinHeight) {
            this.aspectRatioMinHeight = aspectRatioMinHeight;
            requestLayout();
        }
        return this;
    }
    protected int getAspectRatioMinHeight() {
        return this.aspectRatioMinHeight;
    }

    /**
     * 自动调整后的最大高度
     * 最终的宽高调整与此关联
     */
    private int aspectRatioMaxHeight = MaxHeightNone;
    protected AspectRatioFrameLayout setAspectRatioMaxHeight(int aspectRatioMaxHeight) {
        if (this.aspectRatioMaxHeight != aspectRatioMaxHeight) {
            this.aspectRatioMaxHeight = aspectRatioMaxHeight;
            requestLayout();
        }
        return this;
    }
    protected int getAspectRatioMaxHeight() {
        return this.aspectRatioMaxHeight;
    }
    
    /* Overrides */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getAspectRatio() != DefaultAspectRatioNone && getKeepAspectDimension() != KeepNone && getLayoutParams() != null) {
            final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
            final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
            final int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
            final int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

            int widthPadding = getPaddingLeft() + getPaddingRight();
            int heightPadding = getPaddingTop() + getPaddingBottom();

            int desireWidth = widthSpecSize;
            int desireHeight = heightSpecSize;

            if (getKeepAspectDimension() == KeepWidth) {
                if (widthSpecMode == MeasureSpec.EXACTLY) {
                    desireWidth = widthSpecSize;
                }
                else {
                    if (getLayoutParams().width >= 0) {
                        desireWidth = getLayoutParams().width;
                    }

                    if (widthSpecMode == MeasureSpec.AT_MOST) {
                        desireWidth = Math.min(desireWidth, widthSpecSize);
                    }
                }

                if (heightSpecMode != MeasureSpec.EXACTLY) {
                    desireHeight = (int) ((desireWidth - widthPadding) / getAspectRatio() + heightPadding);
                    if (getAspectRatioMinHeight() != MinHeightNone) {
                        desireHeight = Math.max(desireHeight, getAspectRatioMinHeight());
                    }
                    if (getAspectRatioMaxHeight() != MaxHeightNone && getAspectRatioMaxHeight() > 0) {
                        desireHeight = Math.min(desireHeight, getAspectRatioMaxHeight());
                    }
                }
                else {
                    desireHeight = heightSpecSize;
                }
            }
            else {
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
                    desireWidth = (int) ((desireHeight - heightPadding) * getAspectRatio() + widthPadding);
                    if (getAspectRatioMinWidth() != MinWidthNone) {
                        desireWidth = Math.max(desireWidth, getAspectRatioMinWidth());
                    }
                    if (getAspectRatioMaxWidth() != MaxWidthNone && getAspectRatioMaxWidth() > 0) {
                        desireWidth = Math.min(desireWidth, getAspectRatioMaxWidth());
                    }
                }
                else {
                    desireWidth = widthSpecSize;
                }
            }

            Log.d("logger", "desireWidth " + desireWidth + "  , desireHeight " + desireHeight);
            setMeasuredDimension(desireWidth, desireHeight);

            widthMeasureSpec = MeasureSpec.makeMeasureSpec(desireWidth, widthSpecMode);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(desireHeight, heightSpecMode);
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = getChildAt(i);
                if (child.getVisibility() != GONE) {
                    measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                }
            }
        }
        else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        if (params != null) {
            if ((getKeepAspectDimension() == KeepWidth && params.width == ViewGroup.LayoutParams.WRAP_CONTENT)
                    || (getKeepAspectDimension() == KeepHeight && params.height == ViewGroup.LayoutParams.WRAP_CONTENT)) {
                throw new IllegalStateException("AspectRatioFrameLayout do not support WRAP_CONTENT for the keep dimension.");
            }
        }
        super.setLayoutParams(params);
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        ViewGroup.LayoutParams layoutParams = super.getLayoutParams();
        if (layoutParams != null) {
            if (getKeepAspectDimension() == KeepWidth) {
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }
            else if (getKeepAspectDimension() == KeepHeight) {
                layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            }
        }
        return layoutParams;
    }

    /* Delegates */
    
    
    /* Private Methods */
    private void internalInit() {
    }

    /**
     * 获取xml属性aspectRatio的默认值对应的比例
     * 对于无法整除的比例添加默认值可以较好的得到比较精确的数值
     * 当然，使用{@link #aspectRatioWidth}和{@link #aspectRatioHeight}也可以做到
     * @param flagValue 预设的attr中的flag的value
     * @return
     */
    private static float MatchAspectRatioFromAttrFlag(float flagValue) {
        if (flagValue == DefaultAspectRatioAttrsFlagValueNone) {
            return DefaultAspectRatioNone;
        }
        else if (flagValue == DefaultAspectRatioAttrsFlagValue16to9) {
            return DefaultAspectRatio16to9;
        }
        else if (flagValue == DefaultAspectRatioAttrsFlagValue9to16) {
            return DefaultAspectRatio9to16;
        }
        else if (flagValue == DefaultAspectRatioAttrsFlagValue4to3) {
            return DefaultAspectRatio4to3;
        }
        else if (flagValue == DefaultAspectRatioAttrsFlagValue3to4) {
            return DefaultAspectRatio3to4;
        }

        return flagValue;
    }

    /* Enums */
}