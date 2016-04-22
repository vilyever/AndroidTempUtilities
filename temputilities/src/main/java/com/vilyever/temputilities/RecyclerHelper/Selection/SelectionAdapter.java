package com.vilyever.temputilities.RecyclerHelper.Selection;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;

import com.vilyever.temputilities.RecyclerHelper.Basic.RecyclerViewAdapter;


/**
 * SelectionAdapter
 * ESchoolbag <com.ftet.base.Adapter>
 * Created by vilyever on 2016/3/3.
 * Feature:
 * 集成选中功能
 */
public class SelectionAdapter extends RecyclerViewAdapter implements SelectionViewHolder.SelectionItemDelegate, SelectionViewHolder.SelectionItemDatasource {
    final SelectionAdapter self = this;


    /* Constructors */


    /* Public Methods */
    /**
     * 是否可以进行{@link #notifyDataSetChanged()}等notify操作
     * 若强行notify可能导致崩溃
    * @return 是否
     */
    public boolean canNotify() {
        return getRecyclerView() != null && !getRecyclerView().isAnimating() && !getRecyclerView().isComputingLayout() && !getRecyclerView().getLayoutManager().isSmoothScrolling();
    }

    public boolean validatePosition(int position) {
        return internalValidatePosition(position);
    }

    public boolean selectItem(int position) {
        return internalSelectItem(position, false);
    }

    public boolean selectAll(int viewType) {
        return internalSelectAll(viewType, false);
    }

    public boolean selectAll() {
        return internalSelectAll(false);
    }

    public boolean deselectItem(int position) {
        return internalDeselectItem(position, false);
    }

    public boolean deselectAll(int viewType) {
        return internalDeselectAll(viewType, false);
    }

    public boolean deselectAll() {
        return internalDeselectAll(false);
    }

    /**
     * 获取当前单选状态下的选中item的position
     * 若当前处于单选状态，且此时没有item被选中，则返回{@link RecyclerView#NO_POSITION}
     * 若当前处于非单选状态，则返回{@link RecyclerView#NO_POSITION}
     * @return
     */
    public int getSingleSelectedPosition() {
        if (getSelectionMode() == SelectionMode.Single) {
            for (int position = 0; position < getItemCount(); position++) {
                if (getItemSelectionStateArray().get(position)) {
                    return position;
                }
            }
        }
        return RecyclerView.NO_POSITION;
    }

    /* Properties */
    /**
     * 选中状态改变的回调
     */
    private SelectionDelegate selectionDelegate;
    public SelectionAdapter setSelectionDelegate(SelectionDelegate selectionDelegate) {
        this.selectionDelegate = selectionDelegate;
        return this;
    }
    public SelectionDelegate getSelectionDelegate() {
        if (this.selectionDelegate == null) {
            this.selectionDelegate = new SelectionDelegate.SimpleOnItemSelectedListener();
        }
        return this.selectionDelegate;
    }
    public interface SelectionDelegate {
        /**
         * 将要选中item
         * 注意：此时item已被点击
         * @param adapter adapter
         * @param position item的position
         * @return 返回position表示选中此项，返回其他position表示选中其他项，返回{@link RecyclerView#NO_POSITION}表示不选中此项
         */
        int willSelectItem(SelectionAdapter adapter, int position, boolean fromUser);

        /**
         * item已被选中
         * @param adapter adapter
         * @param position item的position
         */
        void onItemSelected(SelectionAdapter adapter, int position, boolean fromUser);

        /**
         * 将要反选item
         * 注意：此时已选中的item已被点击
         * @param adapter adapter
         * @param position item的position
         * @return 返回position表示反选此项，返回其他position表示反选其他项，返回{@link RecyclerView#NO_POSITION}表示不反选此项
         */
        int willDeselectItem(SelectionAdapter adapter, int position, boolean fromUser);

        /**
         * item已被取消选中
         * @param adapter adapter
         * @param position item的position
         */
        void onItemDeselected(SelectionAdapter adapter, int position, boolean fromUser);

        class SimpleOnItemSelectedListener implements SelectionDelegate {
            @Override
            public int willSelectItem(SelectionAdapter adapter, int position, boolean fromUser) {
                return position;
            }

            @Override
            public void onItemSelected(SelectionAdapter adapter, int position, boolean fromUser) {

            }

            @Override
            public int willDeselectItem(SelectionAdapter adapter, int position, boolean fromUser) {
                return position;
            }

            @Override
            public void onItemDeselected(SelectionAdapter adapter, int position, boolean fromUser) {

            }
        }
    }

    private SelectionClickDelegate selectionClickDelegate;
    public SelectionAdapter setSelectionClickDelegate(SelectionClickDelegate selectionClickDelegate) {
        this.selectionClickDelegate = selectionClickDelegate;
        return this;
    }
    public SelectionClickDelegate getSelectionClickDelegate() {
        if (this.selectionClickDelegate == null) {
            this.selectionClickDelegate = new SelectionClickDelegate.SimpleSelectionClickDelegate();
        }
        return this.selectionClickDelegate;
    }
    public interface SelectionClickDelegate {
        /**
         * item被点击
         * @param adapter adapter
         * @param position item的position
         */
        void onItemClick(SelectionAdapter adapter, int position);

        class SimpleSelectionClickDelegate implements SelectionClickDelegate {
            @Override
            public void onItemClick(SelectionAdapter adapter, int position) {

            }
        }
    }

    /**
     * 选中模式
     * {@link SelectionMode}
     */
    public enum SelectionMode {
        None, Single, Multiple;
    }
    private SelectionMode selectionMode;
    public SelectionAdapter setSelectionMode(SelectionMode selectionMode) {
        if (selectionMode != this.selectionMode) {
            this.selectionMode = selectionMode;
            getItemSelectionStateArray().clear();
            notifyDataSetChanged();
        }
        return this;
    }
    public SelectionMode getSelectionMode() {
        if (this.selectionMode == null) {
            this.selectionMode = SelectionMode.None;
        }
        return this.selectionMode;
    }

    /**
     * 所有item的选中状态
     */
    private SparseBooleanArray itemSelectionStateArray;
    protected SparseBooleanArray getItemSelectionStateArray() {
        if (this.itemSelectionStateArray == null) {
            this.itemSelectionStateArray = new SparseBooleanArray();
        }
        return this.itemSelectionStateArray;
    }

    private int handlingSelectionPosition = RecyclerView.NO_POSITION;
    protected SelectionAdapter setHandlingSelectionPosition(int handlingSelectionPosition) {
        this.handlingSelectionPosition = handlingSelectionPosition;
        return this;
    }
    public int getHandlingSelectionPosition() {
        return this.handlingSelectionPosition;
    }

    /* Overrides */
    @Override
    @CallSuper
    protected void onBind(RecyclerView.ViewHolder holder, int position) {
        super.onBind(holder, position);

        if (holder instanceof SelectionViewHolder) {
            SelectionViewHolder viewHolder = (SelectionViewHolder) holder;
            viewHolder.setSelectionItemDelegate(this);
            viewHolder.reloadSelection(this);
        }
    }



    /* Delegates */
    @Override
    public void onItemViewClick(SelectionViewHolder viewHolder) {
        int position = viewHolder.getAdapterPosition();

        setHandlingSelectionPosition(position);
        switch (getSelectionMode()) {
            case None:
                break;
            case Single:
                internalSelectItem(position, true);
                break;
            case Multiple:
                if (getItemSelectionStateArray().get(position)) {
                    internalDeselectItem(position, true);
                }
                else {
                    internalSelectItem(position, true);
                }
                break;
        }

        getSelectionClickDelegate().onItemClick(this, position);

        setHandlingSelectionPosition(RecyclerView.NO_POSITION);
    }

    @Override
    public boolean gainSelected(SelectionViewHolder viewHolder) {
        return getItemSelectionStateArray().get(viewHolder.getAdapterPosition());
    }

    /* Protected Methods */
    /**
     * 验证position是否合理
     * @param position
     * @return position是否在[0, itemCount)区间内
     */
    protected boolean internalValidatePosition(int position) {
        return position >= 0 && position < getItemCount();
    }

    /**
     * 选中指定item
     * @param position
     * @param fromUser 是否由user触屏操作引起
     * @return 指定item的状态是否为选中，如果该item在调用此方法前的状态为选中，此时仍然返回true
     */
    protected boolean internalSelectItem(int position, boolean fromUser) {
        if (!internalValidatePosition(position)) {
            return false;
        }

        switch (getSelectionMode()) {
            case None:
                return false;
            case Single:
                int preSingleSelectedPosition = getSingleSelectedPosition();

                if (position == preSingleSelectedPosition) {
                    return true;
                }

                if (internalValidatePosition(preSingleSelectedPosition)
                        && !internalDeselectItem(preSingleSelectedPosition, fromUser)) {
                    break;
                }
            case Multiple:
                if (getItemSelectionStateArray().get(position)) {
                    return true;
                }

                int willSelectPosition = getSelectionDelegate().willSelectItem(this, position, fromUser);
                if (internalValidatePosition(willSelectPosition)) {
                    getItemSelectionStateArray().put(willSelectPosition, true);

//                    notifyItemChanged(willSelectPosition);
                    internalReloadItemSelectionState(willSelectPosition);

                    getSelectionDelegate().onItemSelected(this, willSelectPosition, fromUser);
                }
        }

        return getItemSelectionStateArray().get(position);
    }

    /**
     * 选中所有指定viewType的item
     * @param viewType
     * @param fromUser 是否由user触屏操作引起
     * @return 是否所有viewType的item都处于选中状态，即使有一个item被拒绝选中也返回false
     */
    protected boolean internalSelectAll(int viewType, boolean fromUser) {
        if (getSelectionMode() == SelectionMode.Multiple) {
            boolean result = true;
            int count = 0;
            for (int position = 0; position < getItemCount(); position++) {
                if (getItemViewType(position) == viewType) {
                    result = result && internalSelectItem(position, fromUser);
                    count++;
                }
            }
            return result && count != 0;
        }
        return false;
    }

    /**
     * 选中所有item
     * @param fromUser 是否由user触屏操作引起
     * @return 是否所有的item都处于选中状态，即使有一个item被拒绝选中也返回false
     */
    protected boolean internalSelectAll(boolean fromUser) {
        if (getSelectionMode() == SelectionMode.Multiple) {
            boolean result = true;
            for (int position = 0; position < getItemCount(); position++) {
                result = result && internalSelectItem(position, fromUser);
            }
            return result;
        }
        return false;
    }

    /**
     * 反选指定item
     * @param position
     * @param fromUser 是否由user触屏操作引起
     * @return 指定item的状态是否为未选中，如果该item在调用此方法前的状态为未选中，此时仍然返回true
     */
    protected boolean internalDeselectItem(int position, boolean fromUser) {
        if (!internalValidatePosition(position)) {
            return false;
        }

        if (getItemSelectionStateArray().get(position)) {
            int willDeselectPosition = getSelectionDelegate().willDeselectItem(this, position, fromUser);

            if (internalValidatePosition(willDeselectPosition)) {
                getItemSelectionStateArray().put(willDeselectPosition, false);

//            notifyItemChanged(willDeselectPosition);
                internalReloadItemSelectionState(willDeselectPosition);

                getSelectionDelegate().onItemDeselected(this, willDeselectPosition, fromUser);
            }
        }

        return !getItemSelectionStateArray().get(position);
    }

    /**
     * 反选所有指定viewType的item
     * @param viewType
     * @param fromUser 是否由user触屏操作引起
     * @return 是否所有viewType的item都处于未选中状态，即使有一个item被拒绝反选也返回false
     *          注意：即使{@link #selectionMode}不为{@link SelectionMode#Multiple}仍然有可能返回true
     *                  即，返回的状态只表明结果符合预期，而非过程符合预期
     */
    protected boolean internalDeselectAll(int viewType, boolean fromUser) {
        if (getSelectionMode() == SelectionMode.None) {
            return true;
        }
        else {
            boolean result = true;
            for (int position = 0; position < getItemCount(); position++) {
                if (getItemViewType(position) == viewType) {
                    result = result && internalDeselectItem(position, fromUser);
                }
            }
            return result;
        }
    }

    /**
     * 反选所有的item
     * @param fromUser 是否由user触屏操作引起
     * @return 是否所有v的item都处于未选中状态，即使有一个item被拒绝反选也返回false
     *          注意：即使{@link #selectionMode}不为{@link SelectionMode#Multiple}仍然有可能返回true
     *                  即，返回的状态只表明结果符合预期，而非过程符合预期
     */
    protected boolean internalDeselectAll(boolean fromUser) {
        if (getSelectionMode() == SelectionMode.None) {
            return true;
        }
        else {
            boolean result = true;
            for (int position = 0; position < getItemCount(); position++) {
                result = result && internalDeselectItem(position, fromUser);
            }

            return result;
        }
    }

    protected void internalReloadItemSelectionState(int position) {
        if (!canNotify()) {
            return;
        }
        View child = getRecyclerView().getLayoutManager().findViewByPosition(position);
        if (child != null) {
            RecyclerView.ViewHolder viewHolder = getRecyclerView().getChildViewHolder(child);
            if (viewHolder instanceof SelectionViewHolder) {
                ((SelectionViewHolder) viewHolder).reloadSelection(this);
            }
        }
    }

    /* Enums */

}