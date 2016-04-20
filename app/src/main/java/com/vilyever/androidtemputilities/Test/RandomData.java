package com.vilyever.androidtemputilities.Test;

import android.graphics.Color;

import java.util.Random;

/**
 * RandomData
 * AndroidTempUtilities <com.vilyever.androidtemputilities.Test>
 * Created by vilyever on 2016/4/20.
 * Feature:
 */
public class RandomData {
    final RandomData self = this;

    private static Random random = new Random();
    
    /* Constructors */
    
    
    /* Public Methods */
    public static int getColor() {
        return Color.rgb(random.nextInt(1234) % 255, random.nextInt(12345) % 255, random.nextInt(123456) % 255);
    }

    public static int getInt() {
        return random.nextInt(65535);
    }

    public static int getInt(int n) {
        return random.nextInt(n);
    }

    /* Properties */
    
    
    /* Overrides */
    
    
    /* Delegates */
    
    
    /* Private Methods */
    
}