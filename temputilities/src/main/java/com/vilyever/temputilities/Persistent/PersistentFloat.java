package com.vilyever.temputilities.Persistent;

import android.content.Context;
import android.content.SharedPreferences;

import com.vilyever.contextholder.ContextHolder;

/**
 * PersistentInt
 * ESB <com.vilyever.base.Persistent>
 * Created by vilyever on 2016/4/12.
 * Feature:
 */
public class PersistentFloat {
    final PersistentFloat self = this;

    /* Constructors */

    
    /* Public Methods */
    public static void set(String key, float value) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentFloat.class.getSimpleName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public static float get(String key) {
        return get(key, 0.0f);
    }

    public static float get(String key, float defaultValue) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentFloat.class.getSimpleName(), Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defaultValue);
    }


    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}