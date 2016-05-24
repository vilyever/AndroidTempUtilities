package com.vilyever.androidtemputilities.Test.var;

import android.content.Context;
import android.widget.TextView;

import com.vilyever.androidtemputilities.R;
import com.vilyever.popupcontroller.ViewController;
import com.vilyever.temputilities.VAR.VAR;

/**
 * TestRXJavaController
 * Created by vilyever on 2016/5/5.
 * Feature:
 */
public class TestVARController extends ViewController {
    final TestVARController self = this;

    public VAR<Float> titleLabelAlpha = new VAR<>(1.0f);

    /* Constructors */
    public TestVARController(Context context) {
        super(context, R.layout.base_test_layout);

    }

    @Override
    protected void onViewAppeared() {
        super.onViewAppeared();
    }

    /* Public Methods */
    
    
    /* Properties */
    private TextView titleLabel;
    protected TextView getTitleLabel() { if (this.titleLabel == null) {this.titleLabel = findViewById(R.id.titleLabel); } return this.titleLabel; }
    
    /* Overrides */
    
    
    /* Delegates */
    
    
    /* Private Methods */
    
}