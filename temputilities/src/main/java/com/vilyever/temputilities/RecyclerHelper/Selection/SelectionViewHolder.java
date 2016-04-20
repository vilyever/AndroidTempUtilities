package com.vilyever.temputilities.RecyclerHelper.Selection;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vilyever.temputilities.RecyclerHelper.Basic.RecyclerViewHolder;


/**
 * SelectionViewHolder
 * ESchoolbag <com.ftet.base.Adapter>
 * Created by vilyever on 2016/3/3.
 * Feature:
 */
public abstract class SelectionViewHolder extends RecyclerViewHolder {
    final SelectionViewHolder self = this;

    /* Constructors */
    public SelectionViewHolder(ViewGroup parent, int layoutID) {
        this(LayoutInflater.from(parent.getContext())
                           .inflate(layoutID, parent, false));
    }

    public SelectionViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.getSelectionItemDelegate().onItemViewClick(self);
            }
        });
    }
    
    /* Public Methods */
    /**
     * 刷新此ViewHolder的选中状态
     *
     * @param datasource 数据源
     */
    @CallSuper
    public void reloadSelection(@NonNull SelectionItemDatasource datasource) {
        if (getAdapterPosition() == RecyclerView.NO_POSITION) {
            return;
        }

        this.itemView.setSelected(datasource.gainSelected(this));
    }

    /* Properties */
    private SelectionItemDelegate selectionItemDelegate;
    protected SelectionViewHolder setSelectionItemDelegate(SelectionItemDelegate selectionItemDelegate) {
        this.selectionItemDelegate = selectionItemDelegate;
        return this;
    }
    protected SelectionItemDelegate getSelectionItemDelegate() {
        if (this.selectionItemDelegate == null) {
            this.selectionItemDelegate = new SelectionItemDelegate.SimpleOnItemViewClickListener();
        }
        return this.selectionItemDelegate;
    }
    public interface SelectionItemDelegate {
        void onItemViewClick(SelectionViewHolder viewHolder);

        class SimpleOnItemViewClickListener implements SelectionItemDelegate {
            @Override
            public void onItemViewClick(SelectionViewHolder viewHolder) {
            }
        }
    }


    /* Overrides */
     
     
    /* Delegates */

    /* Protected Methods */


    /* Private Methods */


    /* Interfaces */
    public interface SelectionItemDatasource {
        boolean gainSelected(SelectionViewHolder viewHolder);

        class SimpleSelectionDatasource implements SelectionItemDatasource {
            @Override
            public boolean gainSelected(SelectionViewHolder viewHolder) {
                return false;
            }
        }
    }

}