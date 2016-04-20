package com.vilyever.temputilities.Keyboard;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * KeyboardManager
 * ESB <com.vilyever.base>
 * Created by vilyever on 2016/2/23.
 * Feature:
 * 键盘管理
 */
public class KeyboardManager {
    final KeyboardManager self = this;

    
    /* Constructors */
    
    
    /* Public Methods */

    /**
     * 弹出键盘
     * @param inputView 获取焦点的视图
     */
    public static void showKeyboard(View inputView) {
//        inputView.requestFocus();
//        InputMethodManager imm = (InputMethodManager) inputView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(inputView, InputMethodManager.SHOW_FORCED);

        if (inputView.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    inputView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(inputView, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * 隐藏键盘
     */
    public static void hideKeyboard() {
//        if (ActivityHelper.findCurrentActivity() == null) {
//            return;
//        }
//        View inputView = ActivityHelper.findCurrentActivity().getCurrentFocus();
//        if (inputView == null) {
//            return;
//        }
//        inputView.clearFocus();
//        InputMethodManager imm = (InputMethodManager) inputView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(inputView.getWindowToken(), 0);
    }

    public static boolean isInputMethodTarget(View inputView) {
        InputMethodManager imm = (InputMethodManager) inputView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm != null && imm.isActive(inputView);
    }
    
    
    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}