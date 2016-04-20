package com.vilyever.temputilities.RecyclerHelper.Basic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * RecyclerViewHolder
 * ESB <com.vilyever.base.RecyclerHelper>
 * Created by vilyever on 2016/4/13.
 * Feature:
 */
public abstract class RecyclerViewHolder extends RecyclerView.ViewHolder {
    final RecyclerViewHolder self = this;

    
    /* Constructors */
    public RecyclerViewHolder(ViewGroup parent, int layoutID) {
        this(LayoutInflater.from(parent.getContext())
                           .inflate(layoutID, parent, false));
    }

    public RecyclerViewHolder(View itemView) {
        super(itemView);
    }
    
    /* Public Methods */
    public abstract void onViewHolderWillBind();
    public abstract void onViewHolderBind();

//    /**
//     * {@link SelectionViewHolder} 便捷构造，adapter无需关心ViewHolder的layout文件是什么
//     *
//     * @param parent itemView的父view
//     * @return {@link SelectionViewHolder}实例
//     */
//    public static SelectionViewHolder newInstance(ViewGroup parent) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                                      .inflate(R.layout.xxx_view_holder, parent, false);
//        return new SelectionViewHolder(itemView);
//    }
    
    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}