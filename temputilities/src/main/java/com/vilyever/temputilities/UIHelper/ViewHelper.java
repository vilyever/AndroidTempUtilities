package com.vilyever.temputilities.UIHelper;

import android.view.View;
import android.view.ViewGroup;

/**
 * ViewHelper
 * AndroidTempUtilities <com.vilyever.androidtemputilities.utilities.UIHelper>
 * Created by vilyever on 2016/3/25.
 * Feature:
 */
public class ViewHelper {
    final ViewHelper self = this;

    
    /* Constructors */
    
    
    /* Public Methods */
    public static void disableClipOnParents(View v)
    {
        if (v.getParent() == null)
        {
            return;
        }

        if (v instanceof ViewGroup)
        {
            ( (ViewGroup) v).setClipChildren(false);
            ( (ViewGroup) v).setClipToPadding(false);
        }

        if (v.getParent() instanceof View)
        {
            disableClipOnParents( (View) v.getParent() );
        }
    }
    
    
    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}