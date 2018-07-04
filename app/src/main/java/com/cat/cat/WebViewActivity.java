package com.cat.cat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {

    // 等待提示控件
    private ProgressDialog mDialog;

    // 网页加载控件
    private WebView mWebView;

    //当前正在加载的网页链接
    private String mURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);

        // 从跳转来的Intent中取出相应的数据
        String url = getIntent().getStringExtra("url");

        mWebView = (WebView) findViewById(R.id.web_view);
        // 开启对JS的支持
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 增加对用户的加载提示和放置外部浏览器跳转
        mWebView.setWebViewClient(new WebViewClient() {

            // 开始加载一个网页的回调
            // 第一个参数：mWebView
            // 第二个参数：当前加载的网页的地址
            //
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mURL = url;
                // 如果不在展示中，就展示出来
                if (!mDialog.isShowing())
                    mDialog.show();
            }

            // 结束加载网页的回调
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 如果在展示中，就关闭
                if (mDialog.isShowing())
                    mDialog.dismiss();
            }

        });

        //增加长按的监听器
        mWebView.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                //TODO 将当前网页的链接持久化，代码略，可以选择使用SQLite或者SharedPreferences
                Toast.makeText(WebViewActivity.this, "收藏成功，请到收藏夹中查看已收藏的内容！", Toast.LENGTH_SHORT).show();
                Log.d("Jason", mURL);
                return false;
            }
        });

        initDialog();

        // 加载网页
        mWebView.loadUrl(url);

    }

    private void initDialog() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("玩命加载中...");
    }

    // 按键事件的回调
    // 第一个参数：按键的类型
    // 第二个参数：按键的事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断是否是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            // 网页是否可以返回
            // 回退上个网页
            mWebView.goBack();
            // 拦截返回按键事件继续传递
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

