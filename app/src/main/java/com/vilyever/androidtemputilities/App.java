package com.vilyever.androidtemputilities;

import android.app.Application;
import android.content.Intent;

import com.vilyever.activityhelper.ActivityHelper;
import com.vilyever.activityhelper.ActivityIntentDelegate;
import com.vilyever.activityhelper.ActivityResultDelegate;
import com.vilyever.activityhelper.ActivityRouter;
import com.vilyever.androidtemputilities.Demo.SchemeActivity;


/**
 * App
 * ESchoolbag <com.ftet.eschoolbag>
 * Created by vilyever on 2016/2/29.
 * Feature:
 */
//@ReportsCrashes(
//        httpMethod = HttpSender.Method.PUT,
//        reportType = HttpSender.Type.JSON,
//        connectionTimeout = 15000,
//        formUri = "http://vilyever.com:5984/acra-vilyever/_design/acra-storage/_update/report",
//        formUriBasicAuthLogin = "vilyever",
//        formUriBasicAuthPassword = "vilyever",
//        customReportContent = {
//                ReportField.APP_VERSION_CODE,
//                ReportField.APP_VERSION_NAME,
//                ReportField.ANDROID_VERSION,
//                ReportField.PACKAGE_NAME,
//                ReportField.REPORT_ID,
//                ReportField.BUILD,
//                ReportField.STACK_TRACE
//        }
//)
public class App extends Application {
    final App self = this;
    
    /* Public Methods */
    public static void exit() {
        ActivityHelper.finishAllActivities();
        System.exit(0);
    }
    
    /* Overrides */
    @Override
    public void onCreate() {
        super.onCreate();

//        ACRA.init(this);
//        HttpSender httpSender = new HttpSender(HttpSender.Method.PUT,
//                HttpSender.Type.JSON, "http://192.168.1.233:5984/acra-vilyever/_design/acra-storage/_update/report", null);
//        httpSender.setBasicAuth("vilyever", "vilyever");
//        ACRA.getErrorReporter().addReportSender(httpSender);

        ActivityHelper.initialize(this);

        ActivityRouter.bindActivity(MainActivity.class, "Home");
        ActivityRouter.bindActivity(SchemeActivity.class, "Test");
//        ActivityRouter.bindActivity(TestCustomViewActivity.class, "Test");

        ActivityRouter.startActivity("Test", new ActivityIntentDelegate() {
            @Override
            public void setupIntentForStartActivity(Intent intent) {
                intent.putExtra("data", "hello");
            }
        });

        ActivityRouter.startActivityForResult("Test", 1, new ActivityIntentDelegate() {
            @Override
            public void setupIntentForStartActivity(Intent intent) {
                intent.putExtra("data", "hello");
            }
        }, new ActivityResultDelegate() {
            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == 1) {
                    // TODO: 2016/5/4 do sth for result
                }
            }
        });
    }
}