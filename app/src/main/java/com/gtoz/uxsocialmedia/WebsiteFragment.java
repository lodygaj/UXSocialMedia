package com.gtoz.uxsocialmedia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.gtoz.uxsocialmedia.R.id.webView;

/**
 * Created by GtoZ on 2/11/2017.
 */

public class WebsiteFragment extends Fragment {
    private WebView web;
    private String url;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_website, container, false);

        web = (WebView) view.findViewById(webView);
        web.setWebViewClient(new MyBrowser());
        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        return view;
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void setUrl(String url) {
        this.url = url;
        web.loadUrl(this.url);
    }
}