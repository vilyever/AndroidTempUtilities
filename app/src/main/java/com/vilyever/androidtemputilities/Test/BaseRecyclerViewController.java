package com.vilyever.androidtemputilities.Test;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.vilyever.androidtemputilities.R;
import com.vilyever.popupcontroller.ViewController;
import com.vilyever.temputilities.RecyclerHelper.Basic.RecyclerViewAdapter;
import com.vilyever.temputilities.RecyclerHelper.Selection.SelectionAdapter;

/**
 * BaseRecyclerViewController
 * AndroidTempUtilities <com.vilyever.androidtemputilities.Test>
 * Created by vilyever on 2016/4/20.
 * Feature:
 */
public class BaseRecyclerViewController extends ViewController {
    final BaseRecyclerViewController self = this;
    
    
    /* Constructors */
    public BaseRecyclerViewController(Context context) {
        super(context, R.layout.base_recycler_view_controller);

        innerInitRecyclerView();
    }


    /* Public Methods */


    /* Properties */
    private RecyclerView recyclerView;
    protected RecyclerView getRecyclerView() { if (this.recyclerView == null) {this.recyclerView = findViewById(R.id.recyclerView); } return this.recyclerView; }

    private RecyclerView.LayoutManager layoutManager;
    protected RecyclerView.LayoutManager getLayoutManager() {
        if (this.layoutManager == null) {
            this.layoutManager = innerGetLayoutManager();
        }
        return this.layoutManager;
    }

    private SelectionAdapter adapter;
    protected SelectionAdapter getAdapter() {
        if (this.adapter == null) {
            this.adapter = innerGetAdapter();

        }
        return this.adapter;
    }

    private int itemCount = RandomData.getInt(200);
    public BaseRecyclerViewController setItemCount(int itemCount) {
        this.itemCount = itemCount;
        return this;
    }
    public int getItemCount() {
        return this.itemCount;
    }

    /* Overrides */

    @Override
    protected void onViewAppeared() {
        super.onViewAppeared();

        getRecyclerView().getAdapter().notifyDataSetChanged();
    }
    /* Delegates */

    /* Protected Methods */
    @CallSuper
    protected void innerInitRecyclerView() {
        getRecyclerView().setLayoutManager(getLayoutManager());
        getRecyclerView().setAdapter(getAdapter());
    }

    protected RecyclerView.LayoutManager innerGetLayoutManager() {
        return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

    protected SelectionAdapter innerGetAdapter() {
        SelectionAdapter adapter = new SelectionAdapter();
        adapter.setSelectionMode(SelectionAdapter.SelectionMode.Single);
        adapter.setItemDatasource(new RecyclerViewAdapter.ItemDatasource() {
            @Override
            public int gainItemCount(RecyclerViewAdapter adapter) {
                return self.getItemCount();
            }

            @Override
            public int gainItemViewType(RecyclerViewAdapter adapter, int position) {
                return 0;
            }

            @Override
            public RecyclerView.ViewHolder gainViewHolder(RecyclerViewAdapter adapter, ViewGroup parent, int viewType) {
                return new BaseRecyclerViewHolder(parent, R.layout.base_recycler_view_holder);
            }

            @Override
            public void onViewHolderWillBind(RecyclerViewAdapter adapter, RecyclerView.ViewHolder holder, int position, int viewType) {

            }

            @Override
            public void onViewHolderBind(RecyclerViewAdapter adapter, RecyclerView.ViewHolder holder, int position, int viewType) {

            }
        });
        adapter.setSelectionDelegate(new SelectionAdapter.SelectionDelegate() {
            @Override
            public boolean shouldSelectItem(SelectionAdapter adapter, int position, boolean fromUser) {
                return false;
            }

            @Override
            public void onItemSelected(SelectionAdapter adapter, int position, boolean fromUser) {

            }

            @Override
            public boolean shouldPreventDeselectItem(SelectionAdapter adapter, int position, boolean fromUser) {
                return false;
            }

            @Override
            public void onItemDeselected(SelectionAdapter adapter, int position, boolean fromUser) {

            }
        });
        return adapter;
    }
    

    /* Private Methods */
}