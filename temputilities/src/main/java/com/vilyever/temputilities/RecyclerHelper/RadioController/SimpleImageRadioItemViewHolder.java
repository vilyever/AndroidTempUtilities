package com.vilyever.temputilities.RecyclerHelper.RadioController;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vilyever.temputilities.RecyclerHelper.Selection.SelectionViewHolder;


/**
 * SimpleImageRadioItemViewHolder
 * ESchoolbag <com.ftet.base.RadioController>
 * Created by vilyever on 2016/3/11.
 * Feature:
 */
public class SimpleImageRadioItemViewHolder extends SelectionViewHolder {
    final SimpleImageRadioItemViewHolder self = this;

    /* Constructors */
    public SimpleImageRadioItemViewHolder(ViewGroup parent) {
        super(new FrameLayout(parent.getContext()));

        FrameLayout rootFrameLayout = (FrameLayout) itemView;
        rootFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootFrameLayout.addView(getImageView());
    }

    /* Public Methods */

    /* Properties */
    private ImageView imageView;
    public ImageView getImageView() {
        if (this.imageView == null) {
            this.imageView = new ImageView(this.itemView.getContext());
            this.imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        return this.imageView; 
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