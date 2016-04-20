package com.vilyever.temputilities.RecyclerHelper.Basic;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * RecyclerViewAdapter
 * ESB <com.vilyever.base.RecyclerHelper>
 * Created by vilyever on 2016/4/13.
 * Feature:
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {
    final RecyclerViewAdapter self = this;

    
    /* Constructors */
    
    
    /* Public Methods */
    
    
    /* Properties */
    private ItemDatasource itemDatasource;
    public RecyclerViewAdapter setItemDatasource(ItemDatasource itemDatasource) {
        this.itemDatasource = itemDatasource;
        return this;
    }
    public ItemDatasource getItemDatasource() {
        if (this.itemDatasource == null) {
            this.itemDatasource = new ItemDatasource.SimpleItemDatasource();
        }
        return this.itemDatasource;
    }
    public interface ItemDatasource {
        int gainItemCount(RecyclerViewAdapter adapter);
        int gainItemViewType(RecyclerViewAdapter adapter, int position);
        RecyclerView.ViewHolder gainViewHolder(RecyclerViewAdapter adapter, ViewGroup parent, int viewType);
        void onViewHolderWillBind(RecyclerViewAdapter adapter, RecyclerView.ViewHolder holder, int position, int viewType);
        void onViewHolderBind(RecyclerViewAdapter adapter, RecyclerView.ViewHolder holder, int position, int viewType);

        class SimpleItemDatasource implements ItemDatasource {
            @Override
            public int gainItemCount(RecyclerViewAdapter adapter) {
                return 0;
            }

            @Override
            public int gainItemViewType(RecyclerViewAdapter adapter, int position) {
                return 0;
            }

            @Override
            public RecyclerView.ViewHolder gainViewHolder(RecyclerViewAdapter adapter, ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onViewHolderWillBind(RecyclerViewAdapter adapter, RecyclerView.ViewHolder holder, int position, int viewType) {

            }

            @Override
            public void onViewHolderBind(RecyclerViewAdapter adapter, RecyclerView.ViewHolder holder, int position, int viewType) {

            }
        }
    }

    /**
     * 绑定的RecyclerView
     */
    private RecyclerView recyclerView;
    private  <T extends RecyclerViewAdapter> T setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return (T) this;
    }
    protected RecyclerView getRecyclerView() {
        return this.recyclerView;
    }
    
    /* Overrides */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        setRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        setRecyclerView(null);
    }

    @Override
    public int getItemCount() {
        return getItemDatasource().gainItemCount(this);
    }

    @Override
    public int getItemViewType(int position) {
        return getItemDatasource().gainItemViewType(this, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getItemDatasource().gainViewHolder(this, parent, viewType);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder) {
            RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
            viewHolder.onViewHolderWillBind();
            getItemDatasource().onViewHolderWillBind(this, holder, position, getItemViewType(position));
            onBind(holder, position);
            getItemDatasource().onViewHolderBind(this, holder, position, getItemViewType(position));
            viewHolder.onViewHolderBind();
        }
    }
     
    /* Delegates */

    /* Protected Methods */
    protected void onBind(RecyclerView.ViewHolder holder, int position) {

    }
     
    /* Private Methods */
    
}