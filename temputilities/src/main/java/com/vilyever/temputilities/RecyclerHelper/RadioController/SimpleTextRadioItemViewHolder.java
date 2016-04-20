package com.vilyever.temputilities.RecyclerHelper.RadioController;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vilyever.temputilities.RecyclerHelper.Selection.SelectionViewHolder;


/**
 * SimpleTextRadioItemViewHolder
 * ESchoolbag <com.ftet.base.RadioController>
 * Created by vilyever on 2016/3/11.
 * Feature:
 */
public class SimpleTextRadioItemViewHolder extends SelectionViewHolder {
    final SimpleTextRadioItemViewHolder self = this;
    
    /* Constructors */
    public SimpleTextRadioItemViewHolder(ViewGroup parent) {
        super(new FrameLayout(parent.getContext()));

        FrameLayout rootFrameLayout = (FrameLayout) itemView;
        rootFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootFrameLayout.addView(getTitleLabel());
    }

    /* Public Methods */


    /* Properties */
    private TextView titleLabel;
    public TextView getTitleLabel() {
        if (this.titleLabel == null) {
            this.titleLabel = new TextView(this.itemView.getContext());
            this.titleLabel.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            this.titleLabel.setGravity(Gravity.CENTER);
        }
        return this.titleLabel;
    }

    /* Overrides */
    @Override
    public void onViewHolderWillBind() {

    }

    @Override
    public void onViewHolderBind() {

    }

    /* Interfaces */
}