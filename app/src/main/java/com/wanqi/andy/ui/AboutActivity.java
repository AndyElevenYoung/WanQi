package com.wanqi.andy.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wanqi.andy.R;

/**
 * @author xc
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void aboutOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_about_back:
                finish();
                break;
            default:
                break;
        }
    }
}
