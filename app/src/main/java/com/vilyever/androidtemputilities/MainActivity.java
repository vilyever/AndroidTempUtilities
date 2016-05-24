package com.vilyever.androidtemputilities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.vilyever.androidtemputilities.Test.TestCenterItemDecorationController;
import com.vilyever.androidtemputilities.Test.TestDividerItemDecorationController;
import com.vilyever.androidtemputilities.Test.rxjava.TestRXJavaController;
import com.vilyever.androidtemputilities.Test.var.TestVARController;
import com.vilyever.logger.Logger;
import com.vilyever.logger.LoggerDisplay;

public class MainActivity extends AppCompatActivity {
    final MainActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LoggerDisplay.initialize(getApplication());

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
        getTestVARController().attachToParent(getTestContentLayout());

        getWindow().getDecorView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                Logger.log("onViewAttachedToWindow");
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                Logger.log("onViewDetachedFromWindow");
            }
        });



        //得到弹出菜单的view，login_setting_popup是弹出菜单的布局文件
        View view = getLayoutInflater().inflate(R.layout.login_setting_popup, null);
        //初始化弹出菜单
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
        //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
        popWindow.setFocusable(true);
        //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
//        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }

    private PopupWindow popWindow;

    @Override
    protected void onResume() {
        super.onResume();

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                Logger.log("post isAttachedToWindow " + ViewCompat.isAttachedToWindow(getWindow().getDecorView()));

                //在底部显示
                popWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
            }
        });
        Logger.log("onResume isAttachedToWindow " + ViewCompat.isAttachedToWindow(getWindow().getDecorView()));
    }

    @Override
    protected void onPause() {
        super.onPause();

        Logger.log("onPause isAttachedToWindow " + ViewCompat.isAttachedToWindow(getWindow().getDecorView()));
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
}
