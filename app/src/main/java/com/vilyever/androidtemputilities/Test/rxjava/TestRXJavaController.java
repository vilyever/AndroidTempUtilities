package com.vilyever.androidtemputilities.Test.rxjava;

import android.content.Context;

import com.vilyever.androidtemputilities.R;
import com.vilyever.logger.Logger;
import com.vilyever.popupcontroller.ViewController;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * TestRXJavaController
 * Created by vilyever on 2016/5/5.
 * Feature:
 */
public class TestRXJavaController extends ViewController {
    final TestRXJavaController self = this;


    /* Constructors */
    public TestRXJavaController(Context context) {
        super(context, R.layout.base_test_layout);

        Observable<String> params = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
            }
        });

        Observable.just("1", "3", "2")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Logger.log("call " + s);
                    }
                });
    }
    
    /* Public Methods */
    
    
    /* Properties */
    
    
    /* Overrides */
    
    
    /* Delegates */
    
    
    /* Private Methods */
    
}