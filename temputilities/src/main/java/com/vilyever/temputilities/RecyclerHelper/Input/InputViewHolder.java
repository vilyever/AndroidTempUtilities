package com.vilyever.temputilities.RecyclerHelper.Input;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vilyever.temputilities.RecyclerHelper.Selection.SelectionViewHolder;


/**
 * InputViewHolder
 * ESB <com.vilyever.base.RecyclerHelper.Input>
 * Created by vilyever on 2016/4/13.
 * Feature:
 */
public class InputViewHolder extends SelectionViewHolder implements TextWatcher {
    final InputViewHolder self = this;
    
    /* Constructors */
    public InputViewHolder(ViewGroup parent, int layoutID) {
        this(LayoutInflater.from(parent.getContext())
                           .inflate(layoutID, parent, false));
    }

    public InputViewHolder(View itemView) {
        super(itemView);

        getEditText().addTextChangedListener(this);
    }

    /* Public Methods */
    /**
     * 刷新此ViewHolder的输入状态
     *
     * @param datasource 数据源
     */
    @CallSuper
    public void reloadInput(@NonNull InputItemDatasource datasource) {
        if (getAdapterPosition() == RecyclerView.NO_POSITION) {
            return;
        }

        InputModel inputModel = datasource.gainInputModel(this);
        if (inputModel.isRegexMatched()) {
            getEditText().setHintTextColor(inputModel.getHintColor());
            getEditText().setHint(inputModel.getHint());
        }
        else {
            getEditText().setHintTextColor(inputModel.getHintColorForRegexNoMatched());
            getEditText().setHint(inputModel.getHintForRegexNoMatched());
        }

        getEditText().setText(inputModel.getText());
    }

    /* Properties */
    private EditText editText;
    protected EditText getEditText() {
        if (this.editText == null) {
            if (this.itemView instanceof ViewGroup) {
                this.editText = findEditText((ViewGroup) this.itemView);
            }

            if (this.editText == null) {
                throw new IllegalStateException("We cannot find any EditText in this ViewHolder");
            }
        }
        return this.editText;
    }

    private InputItemDelegate inputItemDelegate;
    public InputViewHolder setInputItemDelegate(InputItemDelegate inputItemDelegate) {
        this.inputItemDelegate = inputItemDelegate;
        return this;
    }
    public InputItemDelegate getInputItemDelegate() {
        if (this.inputItemDelegate == null) {
            this.inputItemDelegate = new InputItemDelegate.SimpleInputItemDelegate();
        }
        return this.inputItemDelegate;
    }
    public interface InputItemDelegate {
        void onTextChange(InputViewHolder viewHolder, String text);

        class SimpleInputItemDelegate implements InputItemDelegate {
            @Override
            public void onTextChange(InputViewHolder viewHolder, String text) {

            }
        }
    }

    /* Overrides */
    @Override
    public void onViewHolderWillBind() {

    }

    @Override
    public void onViewHolderBind() {

    }

    /* Delegates */
    /** {@link TextWatcher} */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        getInputItemDelegate().onTextChange(this, s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /* Private Methods */
    private EditText findEditText(@NonNull  ViewGroup parent) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof TextInputLayout) {
                return (EditText) child;
            }

            if (child instanceof ViewGroup) {
                View subChild = findEditText((ViewGroup) child);
                if (subChild != null) {
                    return (EditText) subChild;
                }
            }
        }

        return null;
    }

    /* Interfaces */
    public interface InputItemDatasource {
        InputModel gainInputModel(InputViewHolder viewHolder);

        class SimpleInputItemDatasource implements InputItemDatasource {
            @Override
            public InputModel gainInputModel(InputViewHolder viewHolder) {
                return null;
            }
        }
    }
}