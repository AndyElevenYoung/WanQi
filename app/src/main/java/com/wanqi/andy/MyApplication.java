package com.wanqi.andy;

import android.app.Application;

import com.mob.MobSDK;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 *
 * @author xc
 * @date 2018/3/26
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
        ZXingLibrary.initDisplayOpinion(this);
    }
}
