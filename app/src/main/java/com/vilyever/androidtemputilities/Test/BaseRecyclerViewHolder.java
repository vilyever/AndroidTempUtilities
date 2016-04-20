package com.vilyever.androidtemputilities.Test;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vilyever.androidtemputilities.R;
import com.vilyever.temputilities.RecyclerHelper.Selection.SelectionViewHolder;

/**
 * BaseRecyclerViewHolder
 * AndroidTempUtilities <com.vilyever.androidtemputilities.Test>
 * Created by vilyever on 2016/4/20.
 * Feature:
 */
public class BaseRecyclerViewHolder extends SelectionViewHolder {
    final BaseRecyclerViewHolder self = this;
    
    /* Constructors */
    public BaseRecyclerViewHolder(ViewGroup parent, int layoutID) {
        super(parent, layoutID);
    }

    /* Public Methods */

    /* Properties */
    private TextView titleLabel;
    public TextView getTitleLabel() { if (this.titleLabel == null) {this.titleLabel = (TextView) this.itemView.findViewById(R.id.titleLabel); } return this.titleLabel; }

    /* Overrides */

    @Override
    public void onViewHolderBind() {
        super.onViewHolderBind();

        this.itemView.setBackgroundColor(RandomData.getColor());

        if (isSelected()) {
            getTitleLabel().setTextColor(RandomData.getColor());
            getTitleLabel().setText("seleted item " + getAdapterPosition());
        }
        else {
            getTitleLabel().setTextColor(Color.BLACK);
            getTitleLabel().setText("item " + getAdapterPosition());
        }
    }

    /* Interfaces */
}