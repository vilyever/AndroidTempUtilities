package com.vilyever.androidtemputilities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.vilyever.androidtemputilities.Test.TestCenterItemDecorationController;
import com.vilyever.androidtemputilities.Test.TestDividerItemDecorationController;
import com.vilyever.androidtemputilities.Test.aspectratioframelayout.AspectRatioFrameLayoutController;
import com.vilyever.androidtemputilities.Test.rxjava.TestRXJavaController;
import com.vilyever.androidtemputilities.Test.var.TestVARController;
import com.vilyever.logger.LoggerDisplay;
import com.vilyever.popupcontroller.ViewControllerManager;

public class MainActivity extends AppCompatActivity {
    final MainActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LoggerDisplay.initialize(getApplication());

        ViewControllerManager.initialize(getApplication());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        getTestDividerItemDecorationController().attachToParent(getTestContentLayout());
//        getTestCenterItemDecorationController().attachToParent(getTestContentLayout());
//        getTestRXJavaController().attachToParent(getTestContentLayout());
//        getTestVARController().attachToParent(getTestContentLayout());
        getAspectRatioFrameLayoutController().attachToParent(getTestContentLayout());
    }

    private FrameLayout testContentLayout;
    protected FrameLayout getTestContentLayout() { if (this.testContentLayout == null) {this.testContentLayout = (FrameLayout) findViewById(R.id.testContentLayout); } return this.testContentLayout; }


    private TestDividerItemDecorationController testDividerItemDecorationController;
    protected TestDividerItemDecorationController getTestDividerItemDecorationController() {
        if (this.testDividerItemDecorationController == null) {
            this.testDividerItemDecorationController = new TestDividerItemDecorationController(this);
        }
        return this.testDividerItemDecorationController;
    }

    private TestCenterItemDecorationController testCenterItemDecorationController;
    protected TestCenterItemDecorationController getTestCenterItemDecorationController() {
        if (this.testCenterItemDecorationController == null) {
            this.testCenterItemDecorationController = new TestCenterItemDecorationController(this);
        }
        return this.testCenterItemDecorationController;
    }

    private TestRXJavaController testRXJavaController;
    protected TestRXJavaController getTestRXJavaController() {
        if (this.testRXJavaController == null) {
            this.testRXJavaController = new TestRXJavaController(this);
        }
        return this.testRXJavaController;
    }

    private TestVARController testVARController;
    protected TestVARController getTestVARController() {
        if (this.testVARController == null) {
            this.testVARController = new TestVARController(this);
        }
        return this.testVARController;
    }

    private AspectRatioFrameLayoutController aspectRatioFrameLayoutController;
    protected AspectRatioFrameLayoutController getAspectRatioFrameLayoutController() {
        if (this.aspectRatioFrameLayoutController == null) {
            this.aspectRatioFrameLayoutController = new AspectRatioFrameLayoutController(this);
        }
        return this.aspectRatioFrameLayoutController;
    }
}
