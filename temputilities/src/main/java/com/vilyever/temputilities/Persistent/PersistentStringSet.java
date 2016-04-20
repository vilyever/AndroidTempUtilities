package com.vilyever.temputilities.Persistent;

import android.content.Context;
import android.content.SharedPreferences;

import com.vilyever.contextholder.ContextHolder;

import java.util.Set;

/**
 * PersistentInt
 * ESB <com.vilyever.base.Persistent>
 * Created by vilyever on 2016/4/12.
 * Feature:
 */
public class PersistentStringSet {
    final PersistentStringSet self = this;

    /* Constructors */

    
    /* Public Methods */
    public static void set(String key, Set<String> value) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentStringSet.class.getSimpleName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putStringSet(key, value).apply();
    }

    public static Set<String> get(String key) {
        return get(key, null);
    }

    public static Set<String> get(String key, Set<String> defaultValue) {
        SharedPreferences sharedPreferences = ContextHolder.getContext().getSharedPreferences(PersistentStringSet.class.getSimpleName(), Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet(key, defaultValue);
    }


    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}