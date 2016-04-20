package com.vilyever.androidtemputilities.Test;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vilyever.temputilities.RecyclerHelper.ItemDecoration.CenterItemDecoration;
import com.vilyever.unitconversion.DimenConverter;

/**
 * TestDividerItemDecorationController
 * AndroidTempUtilities <com.vilyever.androidtemputilities.Test>
 * Created by vilyever on 2016/4/20.
 * Feature:
 */
public class TestCenterItemDecorationController extends BaseRecyclerViewController {
    final TestCenterItemDecorationController self = this;


    /* Constructors */
    public TestCenterItemDecorationController(Context context) {
        super(context);

        setItemCount(3);
    }
    
    
    /* Public Methods */
    
    
    /* Properties */
    
    
    /* Overrides */

    @Override
    protected void innerInitRecyclerView() {
        super.innerInitRecyclerView();

        CenterItemDecoration centerItemDecoration = new CenterItemDecoration();
        centerItemDecoration.setSeparate(true);
        centerItemDecoration.setInnerSpace(DimenConverter.dpToPixel(30));
        centerItemDecoration.setOuterSpace(DimenConverter.dpToPixel(30));
        getRecyclerView().addItemDecoration(centerItemDecoration);
    }

    @Override
    protected RecyclerView.LayoutManager innerGetLayoutManager() {
//        return super.innerGetLayoutManager();
        return new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
    }



    /* Delegates */
    
    
    /* Private Methods */
    
}