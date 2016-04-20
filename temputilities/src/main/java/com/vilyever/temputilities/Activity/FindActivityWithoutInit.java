package com.vilyever.temputilities.Activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.support.annotation.Nullable;

import com.vilyever.contextholder.ContextHolder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

/**
 * FindActivityWithoutInit
 * AndroidTempUtilities <com.vilyever.androidtemputilities.utilities.Activity>
 * Created by vilyever on 2016/3/24.
 * Feature:
 */
public class FindActivityWithoutInit {
    final FindActivityWithoutInit self = this;

    
    /* Constructors */
    // new UIHandler().postDelayed(new Runnable() {}) to avoid get null or pre activity while activity onCreate()
    @Nullable
    public static Activity findTopActivity() {
        String topActivityComponentName = null;

        try {
            Object activityThread = Class.forName("android.app.ActivityThread")
                                         .getMethod("currentActivityThread").invoke(null);

            Field activityMapField = Class.forName("android.app.ActivityThread").getDeclaredField("mActivities");
            activityMapField.setAccessible(true);
            Map activityMap = (Map) activityMapField.get(activityThread);

            Iterator it = activityMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();

                Field activityField = Class.forName("android.app.ActivityThread$ActivityClientRecord").getDeclaredField("activity");
                activityField.setAccessible(true);
                Activity activity = (Activity) activityField.get(pair.getValue());

                if (topActivityComponentName == null) {
                    // api 21-22 do not have topActivity property in TaskInfo
                    // for google docs, we still can use getRunningTasks to get limited info
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        // Do something for after api 23 and above versions
                        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
                        for (ActivityManager.AppTask task : activityManager.getAppTasks()) {
                            if (task.getTaskInfo().topActivity.getPackageName().equals(ContextHolder.getContext().getPackageName())) {
                                topActivityComponentName = task.getTaskInfo().topActivity.getClassName();
                                break;
                            }
                        }
                    }
                    else {
                        // do something for phones running an SDK before api 23
                        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
                        for (ActivityManager.RunningTaskInfo info : activityManager.getRunningTasks(Integer.MAX_VALUE)) {
                            if (info.topActivity.getPackageName().equals(ContextHolder.getContext().getPackageName())) {
                                topActivityComponentName = info.topActivity.getClassName();
                                break;
                            }
                        }
                    }
                }

                if (activity.getClass().getName().equals(topActivityComponentName)) {
                    return activity;
                }
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    /* Public Methods */
    
    
    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}