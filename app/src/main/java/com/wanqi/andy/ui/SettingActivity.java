package com.wanqi.andy.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wanqi.andy.R;

/**
 * @author xc
 */
public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void settingOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting_back:
                finish();
                break;
            case R.id.btn_setting_logout:
                break;
            default:
                break;
        }
    }
}
