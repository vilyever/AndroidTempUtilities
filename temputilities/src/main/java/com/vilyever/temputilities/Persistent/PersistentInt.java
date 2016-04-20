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
public class PersistentInt {
    final PersistentInt self = this;

    /* Constructors */

    
    /* Public Methods */
    public static void set(String key, int value) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentInt.class.getSimpleName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public static int get(String key) {
        return get(key, 0);
    }

    public static int get(String key, int defaultValue) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentInt.class.getSimpleName(), Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }


    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}