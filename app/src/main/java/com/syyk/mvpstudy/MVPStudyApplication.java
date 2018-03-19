package com.syyk.mvpstudy;

import android.app.Application;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class MVPStudyApplication  extends Application {

    private static MVPStudyApplication mIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        mIntent = this;
    }


    public static MVPStudyApplication getmIntent(){
        return mIntent;
    }

}
