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
public class PersistentBoolean {
    final PersistentBoolean self = this;

    /* Constructors */

    
    /* Public Methods */
    public static void set(String key, boolean value) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentBoolean.class.getSimpleName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static boolean get(String key) {
        return get(key, false);
    }

    public static boolean get(String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentBoolean.class.getSimpleName(), Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}