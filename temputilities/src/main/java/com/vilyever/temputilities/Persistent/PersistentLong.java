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
public class PersistentLong {
    final PersistentLong self = this;

    /* Constructors */

    
    /* Public Methods */
    public static void set(String key, long value) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentLong.class.getSimpleName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public static long get(String key) {
        return get(key, 0l);
    }

    public static long get(String key, long defaultValue) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentLong.class.getSimpleName(), Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }


    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}