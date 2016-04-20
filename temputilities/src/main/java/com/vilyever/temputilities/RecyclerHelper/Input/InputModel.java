package com.vilyever.temputilities.RecyclerHelper.Input;

import android.text.TextUtils;

import com.vilyever.temputilities.RecyclerHelper.Util.InputValidation;


/**
 * InputModel
 * ESB <com.vilyever.base.RecyclerHelper.Input>
 * Created by vilyever on 2016/4/13.
 * Feature:
 */
public class InputModel {
    final InputModel self = this;

    
    /* Constructors */
    
    
    /* Public Methods */
    public boolean shouldValidateRegex() {
        return !TextUtils.isEmpty(getRegex());
    }
    
    /* Properties */
    private String hint;
    public InputModel setHint(String hint) {
        this.hint = hint;
        return this;
    }
    public String getHint() {
        return this.hint;
    }

    private String text;
    public InputModel setText(String text) {
        this.text = text;
        if (shouldValidateRegex()) {
            setRegexMatched(InputValidation.validateRegex(this.text, getRegex()));
        }
        return this;
    }
    public String getText() {
        return this.text;
    }

    private int hintColor;
    public InputModel setHintColor(int hintColor) {
        this.hintColor = hintColor;
        return this;
    }
    public int getHintColor() {
        return this.hintColor;
    }

    private String regex;
    public InputModel setRegex(String regex) {
        this.regex = regex;
        return this;
    }
    public String getRegex() {
        return this.regex;
    }

    private String hintForRegexNoMatched;
    public InputModel setHintForRegexNoMatched(String hintForRegexNoMatched) {
        this.hintForRegexNoMatched = hintForRegexNoMatched;
        return this;
    }
    public String getHintForRegexNoMatched() {
        return this.hintForRegexNoMatched;
    }

    private int hintColorForRegexNoMatched;
    public InputModel setHintColorForRegexNoMatched(int hintColorForRegexNoMatched) {
        this.hintColorForRegexNoMatched = hintColorForRegexNoMatched;
        return this;
    }
    public int getHintColorForRegexNoMatched() {
        return this.hintColorForRegexNoMatched;
    }

    private boolean regexMatched;
    protected InputModel setRegexMatched(boolean regexMatched) {
        this.regexMatched = regexMatched;
        return this;
    }
    public boolean isRegexMatched() {
        return !shouldValidateRegex() || this.regexMatched;
    }


    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}