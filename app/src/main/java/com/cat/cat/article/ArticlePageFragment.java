package com.cat.cat.article;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.cat.cat.R;


/**
 * Created by wuzijian on 2017/9/15.
 */

public class ArticlePageFragment extends Fragment {
    private static final String ARG_URI = "article_page_url";

    private String mUrl;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    public static ArticlePageFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_URI, url);
        ArticlePageFragment fragment = new ArticlePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUrl = (String) getArguments().getSerializable(ARG_URI);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_page, container, false);
        mProgressBar = view.findViewById(R.id.fragment_article_page_progress_bar);
        mProgressBar.setMax(100);
        mWebView = view.findViewById(R.id.fragment_article_page_web_view);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }


        });
        mWebView.loadUrl(mUrl);
        return view;
    }
}
