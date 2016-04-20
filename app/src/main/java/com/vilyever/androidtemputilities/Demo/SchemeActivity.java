package com.vilyever.androidtemputilities.Demo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.List;

/**
 * SchemeActivity
 * AndroidTempUtilities <com.vilyever.androidtemputilities.Demo>
 * Created by vilyever on 2016/3/18.
 * Feature:
 */
public class SchemeActivity extends Activity {
    final SchemeActivity self = this;

    // TODO: 2016/3/18 see manifests config
    
    /* Constructors */

    
    /* Public Methods */
    public void startSchemeActivity() {
        Uri scheme = Uri.parse("vilyever://demo");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(scheme);

        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(intent,
                                                               PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;
        if (isIntentSafe) {
            startActivity(intent);
        }
        else {
            // install app
//            Toast_VDKit.toast("你未安装智慧课堂");
        }
    }
    
    /* Properties */
    
    
    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    
}