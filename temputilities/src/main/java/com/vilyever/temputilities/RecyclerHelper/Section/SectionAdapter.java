package com.vilyever.temputilities.RecyclerHelper.Section;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import com.vilyever.temputilities.RecyclerHelper.Selection.SelectionAdapter;
import com.vilyever.temputilities.RecyclerHelper.Selection.SelectionViewHolder;


/**
 * SectionAdapter
 * ESchoolbag <com.ftet.base.RecyclerHelper>
 * Created by vilyever on 2016/3/15.
 * Feature:
 */
public class SectionAdapter extends SelectionAdapter {
    final SectionAdapter self = this;
    
    /* Constructors */
    public SectionAdapter() {
        super();
    }
    
    /* Public Methods */
    public int getSectionCount() {
        return getSectionDatasource().gainSectionCount(this);
    }

    public int getSectionItemCount(int section) {
        return getSectionDatasource().gainSectionItemCount(this, section);
    }

    public int getSectionViewType(int section) {
        return getSectionDatasource().gainSectionViewType(this, section);
    }

    public int getSectionItemViewType(int section, int item) {
        return getSectionDatasource().gainSectionItemViewType(this, section, item);
    }

    public SectionItemIndex getSectionItemIndex(int position) {
        SectionItemIndex sectionItemIndex = new SectionItemIndex();

        int count = 0;
        for (int section = 0; section < getSectionCount(); section++) {
            if (position == count) {
                sectionItemIndex.section = section;
                break;
            }

            int increaseCount = 1 + getSectionItemCount(section);

            if (position > count && position < count + increaseCount) {
                sectionItemIndex.section = section;
                sectionItemIndex.item = position - count - 1;
                break;
            }

            count += increaseCount;
        }

        return sectionItemIndex;
    }

    public boolean selectSectionItem(SectionItemIndex itemIndex) {
        return internalSelectSectionItem(itemIndex, false);
    }

    public boolean selectSection(int section) {
        return internalSelectSection(section, false);
    }

    public boolean deselectSectionItem(SectionItemIndex itemIndex) {
        return internalDeselectSectionItem(itemIndex, false);
    }

    public boolean deselectSection(int section) {
        return internalDeselectSection(section, false);
    }

    /* Properties */
    private SectionDatasource sectionDatasource;
    public SectionAdapter setSectionDatasource(SectionDatasource sectionDatasource) {
        this.sectionDatasource = sectionDatasource;
        return this;
    }
    public SectionDatasource getSectionDatasource() {
        if (this.sectionDatasource == null) {
            this.sectionDatasource = new SectionDatasource.SimpleHeaderDatasource();
        }
        return this.sectionDatasource;
    }
    public interface SectionDatasource {
        int gainSectionCount(SectionAdapter adapter);
        int gainSectionViewType(SectionAdapter adapter, int section);
        int gainSectionItemCount(SectionAdapter adapter, int section);
        int gainSectionItemViewType(SectionAdapter adapter, int section, int item);
        SelectionViewHolder gainItemViewHolder(SelectionAdapter adapter, ViewGroup parent, int viewType);
        void onBindSectionViewHolder(SelectionAdapter adapter, SelectionViewHolder holder, int section, int viewType);
        void onBindSectionItemViewHolder(SelectionAdapter adapter, SelectionViewHolder holder, int section, int item, int viewType);

        class SimpleHeaderDatasource implements SectionDatasource {
            @Override
            public int gainSectionCount(SectionAdapter adapter) {
                return 0;
            }

            @Override
            public int gainSectionViewType(SectionAdapter adapter, int section) {
                return 0;
            }

            @Override
            public int gainSectionItemCount(SectionAdapter adapter, int section) {
                return 0;
            }

            @Override
            public int gainSectionItemViewType(SectionAdapter adapter, int section, int item) {
                return 0;
            }

            @Override
            public SelectionViewHolder gainItemViewHolder(SelectionAdapter adapter, ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindSectionViewHolder(SelectionAdapter adapter, SelectionViewHolder holder, int section, int viewType) {

            }

            @Override
            public void onBindSectionItemViewHolder(SelectionAdapter adapter, SelectionViewHolder holder, int section, int item, int viewType) {

            }
        }
    }

    private SectionSelectionDelegate sectionSelectionDelegate;
    public SectionAdapter setSectionSelectionDelegate(SectionSelectionDelegate sectionSelectionDelegate) {
        this.sectionSelectionDelegate = sectionSelectionDelegate;
        return this;
    }
    public SectionSelectionDelegate getSectionSelectionDelegate() {
        if (this.sectionSelectionDelegate == null) {
            this.sectionSelectionDelegate = new SectionSelectionDelegate.SimpleSectionSelectionDelegate();
        }
        return this.sectionSelectionDelegate;
    }
    public interface SectionSelectionDelegate {

        /**
         * 将要选中item
         * 注意：此时item已被点击
         * @param adapter adapter
         * @param itemIndex item的所在section和所在section的position
         * @return 返回itemIndex表示选中此项，返回其他SectionItemIndex表示选中其他项，返回null表示不选中此项
         */
        SectionItemIndex willSelectItem(SectionAdapter adapter, SectionItemIndex itemIndex, boolean fromUser);

        /**
         * item已被选中
         * @param adapter adapter
         * @param itemIndex item的所在section和所在section的position
         */
        void onItemSelected(SectionAdapter adapter, SectionItemIndex itemIndex, boolean fromUser);

        /**
         * 已被选中的item被点击
         * @param adapter adapter
         * @param itemIndex item的所在section和所在section的position
         */
        void onSelectedItemClick(SectionAdapter adapter, SectionItemIndex itemIndex);

        /**
         * 将要反选item
         * 注意：此时已选中的item已被点击
         * @param adapter adapter
         * @param itemIndex item的所在section和所在section的position
         * @return 返回itemIndex表示反选此项，返回其他SectionItemIndex表示反选其他项，返回null表示不反选此项
         */
        SectionItemIndex willDeselectItem(SectionAdapter adapter, SectionItemIndex itemIndex, boolean fromUser);

        /**
         * item已被取消选中
         * @param adapter adapter
         * @param itemIndex item的所在section和所在section的position
         */
        void onItemDeselected(SectionAdapter adapter, SectionItemIndex itemIndex, boolean fromUser);

        class SimpleSectionSelectionDelegate implements SectionSelectionDelegate {
            @Override
            public SectionItemIndex willSelectItem(SectionAdapter adapter, SectionItemIndex itemIndex, boolean fromUser) {
                return itemIndex;
            }

            @Override
            public void onItemSelected(SectionAdapter adapter, SectionItemIndex itemIndex, boolean fromUser) {

            }

            @Override
            public void onSelectedItemClick(SectionAdapter adapter, SectionItemIndex itemIndex) {

            }

            @Override
            public SectionItemIndex willDeselectItem(SectionAdapter adapter, SectionItemIndex itemIndex, boolean fromUser) {
                return itemIndex;
            }

            @Override
            public void onItemDeselected(SectionAdapter adapter, SectionItemIndex itemIndex, boolean fromUser) {

            }
        }
    }

    /* Overrides */
    @Override
    public int getItemCount() {
        int count = 0;

        int sectionCount = getSectionCount();
        count = sectionCount;
        for (int section = 0; section < sectionCount; section++) {
            count += getSectionItemCount(section);
        }

        return count;
    }
    
    @Override
    public int getItemViewType(int position) {
        SectionItemIndex sectionItemIndex = getSectionItemIndex(position);

        if (sectionItemIndex.item == SectionItemIndex.NoIndex) {
            return getSectionDatasource().gainSectionViewType(this, sectionItemIndex.section);
        }
        else {
            return getSectionDatasource().gainSectionItemViewType(this, sectionItemIndex.section, sectionItemIndex.item);
        }
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getSectionDatasource().gainItemViewHolder(this, parent, viewType);
    }
    
    @Override
    protected void onBind(ViewHolder holder, int position) {
        SectionItemIndex sectionItemIndex = getSectionItemIndex(position);

        if (sectionItemIndex.item == SectionItemIndex.NoIndex) {
            getSectionDatasource().onBindSectionViewHolder(this, (SelectionViewHolder) holder, sectionItemIndex.section, getSectionViewType(sectionItemIndex.section));
        }
        else {
            super.onBind(holder, position);
            getSectionDatasource().onBindSectionItemViewHolder(this, (SelectionViewHolder) holder, sectionItemIndex.section, sectionItemIndex.item, getSectionItemViewType(sectionItemIndex.section, sectionItemIndex.item));
        }
    }

    @Override
    public SelectionAdapter setItemDatasource(ItemDatasource itemDatasource) {
        throw new IllegalStateException("With SectionAdapter, we require SectionDatasource.");
    }

    @Override
    public SelectionAdapter setSelectionDelegate(SelectionDelegate selectionDelegate) {
        throw new IllegalStateException("With SectionAdapter, we require SectionSelectionDelegate.");
    }

    private SelectionDelegate interceptSelectionDelegate;
    @Override
    public SelectionDelegate getSelectionDelegate() {
        if (this.interceptSelectionDelegate == null) {
            this.interceptSelectionDelegate = new SelectionDelegate.SimpleOnItemSelectedListener() {
                @Override
                public int willSelectItem(SelectionAdapter adapter, int position, boolean fromUser) {
                    SectionItemIndex sectionItemIndex = getSectionItemIndex(position);
                    SectionItemIndex willSelectItemIndex = getSectionSelectionDelegate().willSelectItem((SectionAdapter) adapter, sectionItemIndex, fromUser);
                    return internalFindPosition(willSelectItemIndex);
                }

                @Override
                public void onItemSelected(SelectionAdapter adapter, int position, boolean fromUser) {
                    SectionItemIndex sectionItemIndex = getSectionItemIndex(position);
                    getSectionSelectionDelegate().onItemSelected((SectionAdapter) adapter, sectionItemIndex, fromUser);
                }

                @Override
                public void onSelectedItemClick(SelectionAdapter adapter, int position) {
                    SectionItemIndex sectionItemIndex = getSectionItemIndex(position);
                    getSectionSelectionDelegate().onSelectedItemClick((SectionAdapter) adapter, sectionItemIndex);
                }

                @Override
                public int willDeselectItem(SelectionAdapter adapter, int position, boolean fromUser) {
                    SectionItemIndex sectionItemIndex = getSectionItemIndex(position);
                    SectionItemIndex willDeselectItemIndex = getSectionSelectionDelegate().willDeselectItem((SectionAdapter) adapter, sectionItemIndex, fromUser);
                    return internalFindPosition(willDeselectItemIndex);
                }

                @Override
                public void onItemDeselected(SelectionAdapter adapter, int position, boolean fromUser) {
                    SectionItemIndex sectionItemIndex = getSectionItemIndex(position);
                    getSectionSelectionDelegate().onItemDeselected((SectionAdapter) adapter, sectionItemIndex, fromUser);
                }
            };
        }
        return this.interceptSelectionDelegate;
    }

    /* Delegates */

    /* Private Methods */
    protected int internalFindPosition(SectionItemIndex itemIndex) {
        int position = 0;
        for (int i = 0; i < itemIndex.section; i++) {
            position += getSectionItemCount(itemIndex.section) + 1;
        }

        return position + itemIndex.item + 1;
    }

    protected boolean internalSelectSectionItem(SectionItemIndex itemIndex, boolean fromUser) {
        return internalSelectItem(internalFindPosition(itemIndex), fromUser);
    }

    protected boolean internalSelectSection(int section, boolean fromUser) {
        if (getSelectionMode() == SelectionMode.Multiple) {
            boolean result = true;
            int fromPosition = internalFindPosition(new SectionItemIndex(section, SectionItemIndex.NoIndex));
            for (int position = fromPosition; position < fromPosition + getSectionItemCount(section); position++) {
                result = result && internalSelectItem(position, fromUser);
            }
            return result;
        }
        return false;
    }

    protected boolean internalDeselectSectionItem(SectionItemIndex itemIndex, boolean fromUser) {
        return internalDeselectItem(internalFindPosition(itemIndex), fromUser);
    }

    protected boolean internalDeselectSection(int section, boolean fromUser) {
        if (getSelectionMode() == SelectionMode.None) {
            return true;
        }
        else {
            boolean result = true;
            int fromPosition = internalFindPosition(new SectionItemIndex(section, SectionItemIndex.NoIndex));
            for (int position = fromPosition; position < fromPosition + getSectionItemCount(section); position++) {
                result = result && internalDeselectItem(position, fromUser);
            }

            return result;
        }
    }

    /* Inner Classes */
    public class SectionItemIndex {
        public static final int NoIndex = -1;
        public int section = NoIndex;
        public int item = NoIndex;

        public SectionItemIndex() {
            this(NoIndex, NoIndex);
        }
        public SectionItemIndex(int section, int item) {
            this.section = section;
            this.item = item;
        }
    }

}