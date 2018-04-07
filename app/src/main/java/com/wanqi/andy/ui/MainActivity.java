package com.wanqi.andy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.wanqi.andy.R;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * @author xc
 */
public class MainActivity extends AppCompatActivity {

    private int REQUEST_CODE = 001;
    private WebView wvMain;
    private WebSettings webSettings;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        //导航图标默认自带
        toolbar.setTitle(R.string.app_name);
        //实现了监听的开关 ，最后2个参数可以写0
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        toggle.syncState();//同步drawerLayout
        drawerLayout.addDrawerListener(toggle);


        //声明WebSettings子类
        webSettings = wvMain.getSettings();
        //如果访问的页面中要与Javascript交互，则必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //缩放操作
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        //优先使用缓存:
        wvMain.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //加载一个网页：
        wvMain.loadUrl("http://quanzi.tiyushe.com/");
        //复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        wvMain.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvMain.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
//                title = title.replace("爱羽客", "玩奇");
            }
        });
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        wvMain = findViewById(R.id.wv_main);
    }

    public void onScanner(View view) {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void menuOnClick(MenuItem view) {
        switch (view.getItemId()) {
            case R.id.nav_meal:
                startActivity(new Intent(MainActivity.this, MealActivity.class));
                break;
            case R.id.nav_wallet:
                startActivity(new Intent(MainActivity.this, WalletActivity.class));
                break;
            case R.id.nav_shop:
                startActivity(new Intent(MainActivity.this, ShopActivity.class));
                break;
            case R.id.nav_setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.nav_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            Toast.makeText(MainActivity.this, "收到结果", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && wvMain.canGoBack()) {
            wvMain.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (wvMain != null) {
            wvMain.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            wvMain.clearHistory();
            ((ViewGroup) wvMain.getParent()).removeView(wvMain);
            wvMain.destroy();
            wvMain = null;
        }
        super.onDestroy();
    }

}
