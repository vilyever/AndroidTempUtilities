package com.vilyever.androidtemputilities.Test;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vilyever.temputilities.RecyclerHelper.ItemDecoration.DividerItemDecoration;
import com.vilyever.unitconversion.DimenConverter;

/**
 * TestDividerItemDecorationController
 * AndroidTempUtilities <com.vilyever.androidtemputilities.Test>
 * Created by vilyever on 2016/4/20.
 * Feature:
 */
public class TestDividerItemDecorationController extends BaseRecyclerViewController {
    final TestDividerItemDecorationController self = this;
    
    
    /* Constructors */
    public TestDividerItemDecorationController(Context context) {
        super(context);
    }
    
    
    /* Public Methods */
    
    
    /* Properties */
    
    
    /* Overrides */

    @Override
    protected void innerInitRecyclerView() {
        super.innerInitRecyclerView();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(DimenConverter.dpToPixel(20), DimenConverter.dpToPixel(44));
        dividerItemDecoration.setEdgeSpaceEqualInnerSpace(false);
        dividerItemDecoration.setEdgeSpace(400, 200, 300, 100);
        getRecyclerView().addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected RecyclerView.LayoutManager innerGetLayoutManager() {
//        return super.innerGetLayoutManager();
//        return new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        return new GridLayoutManager(getContext(), 5, LinearLayoutManager.HORIZONTAL, false);
    }



    /* Delegates */
    
    
    /* Private Methods */
    
}