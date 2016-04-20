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
public class PersistentString {
    final PersistentString self = this;

    /* Constructors */

    
    /* Public Methods */
    public static void set(String key, String value) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentString.class.getSimpleName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static String get(String key) {
        return get(key, "");
    }

    public static String get(String key, String defaultValue) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentString.class.getSimpleName(), Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }


    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}