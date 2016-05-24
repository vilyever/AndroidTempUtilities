package com.vilyever.temputilities.Keyboard;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.vilyever.activityhelper.ActivityHelper;

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
     * @param focusedView 获取焦点的视图
     */
    public static void showKeyboard(View focusedView) {
        if (focusedView.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) focusedView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(focusedView, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * 隐藏键盘
     */
    public static void hideKeyboard() {
        if (ActivityHelper.findTopActivity() == null) {
            return;
        }
        View focusedView = ActivityHelper.findTopActivity().getCurrentFocus();
        hideKeyboard(focusedView);
    }

    /**
     * 隐藏键盘
     */
    public static void hideKeyboard(View focusedView) {
        if (focusedView == null) {
            return;
        }
        focusedView.clearFocus();
        InputMethodManager imm = (InputMethodManager) focusedView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
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