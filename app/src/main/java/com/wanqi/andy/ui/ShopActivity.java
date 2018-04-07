package com.wanqi.andy.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.wanqi.andy.R;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * @author xc
 */
public class ShopActivity extends AppCompatActivity {

    private WebView wvShop;
    private WebSettings webSettings;
    private TextView tvWebTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        initData();
    }

    private void initData() {
        //声明WebSettings子类
        webSettings = wvShop.getSettings();
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
        wvShop.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //加载一个网页：
        wvShop.loadUrl("http://appmall.tiyushe.com/app/goods/index.html");
        //复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        wvShop.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvShop.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                title = title.replace("爱羽客", "玩奇");
                tvWebTitle.setText(title);
            }
        });
    }

    private void initView() {
        wvShop = findViewById(R.id.wv_shop);
        tvWebTitle = findViewById(R.id.tv_shop_title);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && wvShop.canGoBack()) {
            wvShop.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (wvShop != null) {
            wvShop.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            wvShop.clearHistory();
            ((ViewGroup) wvShop.getParent()).removeView(wvShop);
            wvShop.destroy();
            wvShop = null;
        }
        super.onDestroy();
    }

    public void shopOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_shop_back:
                if (wvShop.canGoBack()) {
                    wvShop.goBack();
                } else {
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
