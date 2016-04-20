package com.vilyever.temputilities.RecyclerHelper.Input;

import android.support.v7.widget.RecyclerView;

import com.vilyever.temputilities.RecyclerHelper.Selection.SelectionAdapter;


/**
 * InputAdapter
 * ESB <com.vilyever.base.RecyclerHelper.Input>
 * Created by vilyever on 2016/4/13.
 * Feature:
 */
public class InputAdapter extends SelectionAdapter {
    final InputAdapter self = this;


    /* Constructors */
    
    
    /* Public Methods */
    
    
    /* Properties */
    
    
    /* Overrides */
    @Override
    public void onBind(RecyclerView.ViewHolder holder, int position) {
        super.onBind(holder, position);

        if (holder instanceof InputViewHolder) {
            InputViewHolder viewHolder = (InputViewHolder) holder;
        }
    }

    /* Delegates */
     
     
    /* Private Methods */
    
}