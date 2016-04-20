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

    public boolean selectSectionItem(int section, int item) {
        return internalSelectSectionItem(section, item, false);
    }

    public boolean selectSection(int section) {
        return internalSelectSection(section, false);
    }

    public boolean deselectSectionItem(int section, int item) {
        return internalDeselectSectionItem(section, item, false);
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
         * 是否允许item被选中
         * 注意：此时item已被点击
         * @param adapter adapter
         * @param section item的所在section
         * @param item item的所在section的position
         * @return 是否允许选中
         */
        boolean shouldSelectItem(SectionAdapter adapter, int section, int item, boolean fromUser);

        /**
         * item已被选中
         * @param adapter adapter
         * @param section item的所在section
         * @param item item的所在section的position
         */
        void onItemSelected(SectionAdapter adapter, int section, int item, boolean fromUser);

        /**
         * 是否阻止item被取消选中
         * 注意：此时已选中的item已被点击
         * @param adapter adapter
         * @param section item的所在section
         * @param item item的所在section的position
         * @return 是否允许取消选中
         */
        boolean shouldPreventDeselectItem(SectionAdapter adapter, int section, int item, boolean fromUser);

        /**
         * item已被取消选中
         * @param adapter adapter
         * @param section item的所在section
         * @param item item的所在section的position
         */
        void onItemDeselected(SectionAdapter adapter, int section, int item, boolean fromUser);

        class SimpleSectionSelectionDelegate implements SectionSelectionDelegate {
            @Override
            public boolean shouldSelectItem(SectionAdapter adapter, int section, int item, boolean fromUser) {
                return false;
            }

            @Override
            public void onItemSelected(SectionAdapter adapter, int section, int item, boolean fromUser) {

            }

            @Override
            public boolean shouldPreventDeselectItem(SectionAdapter adapter, int section, int item, boolean fromUser) {
                return false;
            }

            @Override
            public void onItemDeselected(SectionAdapter adapter, int section, int item, boolean fromUser) {

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
            this.interceptSelectionDelegate = new SelectionDelegate() {
                @Override
                public boolean shouldSelectItem(SelectionAdapter adapter, int position, boolean fromUser) {
                    SectionItemIndex sectionItemIndex = getSectionItemIndex(position);
                    if (sectionItemIndex.item == SectionItemIndex.NoIndex) {
                        return false;
                    }
                    return getSectionSelectionDelegate().shouldSelectItem(self, sectionItemIndex.section, sectionItemIndex.item, fromUser);
                }

                @Override
                public void onItemSelected(SelectionAdapter adapter, int position, boolean fromUser) {
                    SectionItemIndex sectionItemIndex = getSectionItemIndex(position);
                    getSectionSelectionDelegate().onItemSelected(self, sectionItemIndex.section, sectionItemIndex.item, fromUser);
                }

                @Override
                public boolean shouldPreventDeselectItem(SelectionAdapter adapter, int position, boolean fromUser) {
                    SectionItemIndex sectionItemIndex = getSectionItemIndex(position);
                    if (sectionItemIndex.item == SectionItemIndex.NoIndex) {
                        return false;
                    }
                    return getSectionSelectionDelegate().shouldPreventDeselectItem(self, sectionItemIndex.section, sectionItemIndex.item, fromUser);
                }

                @Override
                public void onItemDeselected(SelectionAdapter adapter, int position, boolean fromUser) {
                    SectionItemIndex sectionItemIndex = getSectionItemIndex(position);
                    getSectionSelectionDelegate().onItemDeselected(self, sectionItemIndex.section, sectionItemIndex.item, fromUser);
                }
            };
        }
        return this.interceptSelectionDelegate;
    }

    /* Delegates */

    /* Private Methods */
    protected int internalFindSectionItemPosition(int section, int item) {
        int position = 0;
        for (int i = 0; i < section; i++) {
            position += getSectionItemCount(section) + 1;
        }

        return position + item + 1;
    }

    protected boolean internalSelectSectionItem(int section, int item, boolean fromUser) {
        return internalSelectItem(internalFindSectionItemPosition(section, item), fromUser);
    }

    protected boolean internalSelectSection(int section, boolean fromUser) {
        if (getSelectionMode() == SelectionMode.Multiple) {
            boolean result = true;
            int fromPosition = internalFindSectionItemPosition(section, 0);
            for (int position = fromPosition; position < fromPosition + getSectionItemCount(section); position++) {
                result = result && internalSelectItem(position, fromUser);
            }
            return result;
        }
        return false;
    }

    protected boolean internalDeselectSectionItem(int section, int item, boolean fromUser) {
        return internalDeselectItem(internalFindSectionItemPosition(section, item), fromUser);
    }

    protected boolean internalDeselectSection(int section, boolean fromUser) {
        if (getSelectionMode() == SelectionMode.None) {
            return true;
        }
        else {
            boolean result = true;
            int fromPosition = internalFindSectionItemPosition(section, 0);
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
    }

}