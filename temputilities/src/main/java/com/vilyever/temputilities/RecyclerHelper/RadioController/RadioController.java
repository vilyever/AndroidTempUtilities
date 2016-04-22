package com.vilyever.temputilities.RecyclerHelper.RadioController;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.vilyever.popupcontroller.ViewController;
import com.vilyever.temputilities.RecyclerHelper.Basic.RecyclerViewAdapter;
import com.vilyever.temputilities.RecyclerHelper.ItemDecoration.DividerItemDecoration;
import com.vilyever.temputilities.RecyclerHelper.Selection.SelectionAdapter;
import com.vilyever.temputilities.RecyclerHelper.Selection.SelectionViewHolder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * RadioController
 * ESchoolbag <com.ftet.base.RadioController>
 * Created by vilyever on 2016/3/11.
 * Feature:
 */
public abstract class RadioController extends ViewController {
    final RadioController self = this;

    @IntDef({HORIZONTAL, VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {}
    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;

    
    /* Constructors */
    public RadioController(Context context) {
        super(context);

        getRootFrameLayout().addView(getRadioRecyclerView());
        setView(getRootFrameLayout());
        init();
    }
    
    
    /* Public Methods */
    public RadioController setOrientation(@Orientation int orientation) {
        if ((int) orientation != getOrientation()) {
            getLayoutManager().setOrientation(orientation);
        }
        return this;
    }
    public int getOrientation() {
        return getLayoutManager().getOrientation();
    }

    protected RadioController setReverseLayout(boolean reverseLayout) {
        if (reverseLayout != isReverseLayout()) {
            getLayoutManager().setReverseLayout(reverseLayout);
        }
        return this;
    }
    protected boolean isReverseLayout() {
        return getLayoutManager().getReverseLayout();
    }

    public RadioController setLayoutCenter(boolean layoutCenter) {
        if (layoutCenter != isLayoutCenter()) {
            getLayoutManager().setLayoutCenter(layoutCenter);
            getRadioRecyclerView().getAdapter().notifyDataSetChanged();
        }
        return this;
    }
    public boolean isLayoutCenter() {
        return getLayoutManager().isLayoutCenter();
    }

    public RadioController setHorizontalSpace(int horizontalSpace) {
        if (horizontalSpace != getHorizontalSpace()) {
            getItemDecoration().setHorizontalSpace(horizontalSpace);
            getRadioRecyclerView().getAdapter().notifyDataSetChanged();
        }
        return this;
    }
    public int getHorizontalSpace() {
        return getItemDecoration().getHorizontalSpace();
    }

    public RadioController setVerticalSpace(int verticalSpace) {
        if (verticalSpace != getVerticalSpace()) {
            getItemDecoration().setVerticalSpace(verticalSpace);
            getRadioRecyclerView().getAdapter().notifyDataSetChanged();
        }
        return this;
    }
    public int getVerticalSpace() {
        return getItemDecoration().getVerticalSpace();
    }

    public RadioController setEdgeSpacing(boolean edgeSpacing) {
        if (edgeSpacing != isEdgeSpacing()) {
            getItemDecoration().setEdgeSpaceEqualInnerSpace(edgeSpacing);
            getRadioRecyclerView().getAdapter().notifyDataSetChanged();
        }
        return this;
    }
    public boolean isEdgeSpacing() {
        return getItemDecoration().isEdgeSpaceEqualInnerSpace();
    }

    public RadioController selectRadioItem(int position) {
        getItemAdapter().selectItem(position);
        return this;
    }
    public int getSelectedPosition() {
        return getItemAdapter().getSingleSelectedPosition();
    }

    /* Properties */
    /**
     * root视图
     */
    private FrameLayout rootFrameLayout;
    protected FrameLayout getRootFrameLayout() {
        if (rootFrameLayout == null) {
            rootFrameLayout = new FrameLayout(getContext());
            rootFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        return rootFrameLayout;
    }

    private RecyclerView radioRecyclerView;
    protected RecyclerView getRadioRecyclerView() {
        if (radioRecyclerView == null) {
            radioRecyclerView = new RecyclerView(getContext());
            radioRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            radioRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
        return this.radioRecyclerView;
    }

    private RadioItemLayoutManager layoutManager;
    protected RadioItemLayoutManager getLayoutManager() {
        if (this.layoutManager == null) {
            this.layoutManager = new RadioItemLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        }
        return this.layoutManager;
    }
    
    private DividerItemDecoration itemDecoration;
    protected DividerItemDecoration getItemDecoration() {
        if (this.itemDecoration == null) {
            this.itemDecoration = new DividerItemDecoration();
        }
        return this.itemDecoration;
    }
    
    private SelectionAdapter itemAdapter;
    protected SelectionAdapter getItemAdapter() {
        if (this.itemAdapter == null) {
            this.itemAdapter = new SelectionAdapter();
            this.itemAdapter.setSelectionMode(SelectionAdapter.SelectionMode.Single);
            this.itemAdapter.setSelectionDelegate(new SelectionAdapter.SelectionDelegate.SimpleOnItemSelectedListener() {
                @Override
                public void onItemSelected(SelectionAdapter adapter, int position, boolean fromUser) {
                    self.onItemSelected(position);
                }
            });
            this.itemAdapter.setItemDatasource(new RecyclerViewAdapter.ItemDatasource() {
                @Override
                public int gainItemCount(RecyclerViewAdapter adapter) {
                    return self.gainItemCount();
                }

                @Override
                public int gainItemViewType(RecyclerViewAdapter adapter, int position) {
                    return 0;
                }

                @Override
                public SelectionViewHolder gainViewHolder(RecyclerViewAdapter adapter, ViewGroup parent, int viewType) {
                    return self.gainViewHolder(parent);
                }

                @Override
                public void onViewHolderWillBind(RecyclerViewAdapter adapter, RecyclerView.ViewHolder holder, int position, int viewType) {
                    self.onViewHolderWillBind((SelectionViewHolder) holder, position);
                }

                @Override
                public void onViewHolderBind(RecyclerViewAdapter adapter, RecyclerView.ViewHolder holder, int position, int viewType) {
                    self.onViewHolderBind((SelectionViewHolder) holder, position);
                }
            });
        }
        return this.itemAdapter; 
    }


    /* Overrides */

     
    /* Delegates */

    /* Protected Methods */
    protected abstract int gainItemCount();
    protected abstract SelectionViewHolder gainViewHolder(ViewGroup parent);
    protected abstract void onViewHolderWillBind(SelectionViewHolder viewHolder, int position);
    protected abstract void onViewHolderBind(SelectionViewHolder viewHolder, int position);
    protected abstract void onItemSelected(int position);

    /* Private Methods */
    private void init() {
        getRadioRecyclerView().setLayoutManager(getLayoutManager());
        getRadioRecyclerView().addItemDecoration(getItemDecoration());
        getRadioRecyclerView().setAdapter(getItemAdapter());
    }

}