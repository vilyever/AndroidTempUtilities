package com.vilyever.temputilities.RecyclerHelper.Input;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * InputEditText
 * ESB <com.vilyever.base.RecyclerHelper.Input>
 * Created by vilyever on 2016/4/13.
 * Feature:
 */
public class InputEditText extends EditText {
    final InputEditText self = this;


    /* Constructors */
    public InputEditText(Context context) {
        super(context);
    }

    public InputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    /* Public Methods */
    
    
    /* Properties */
    
    
    /* Overrides */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        int saveCount = canvas.save();

        getPaint().setColor(Color.RED);
        canvas.drawRect(5, 5, 100, 20, getPaint());

        if (getHint() != null && getText().length() > 0) {
            // TODO: 2016/4/13 draw floating hint
        }

        canvas.restoreToCount(saveCount);
    }

    /* Delegates */
     
     
    /* Private Methods */
    
}