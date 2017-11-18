package com.chargebee.android.sdk;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class ChargebeeWebViewActivity extends AppCompatActivity {
    WebView cbWebView;
    ProgressBar cbProgressBar;
    CheckoutPage checkoutPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        cbWebView = (WebView) findViewById(R.id.cbWebView);
        cbProgressBar = (ProgressBar) findViewById(R.id.cbProgressBar);
        checkoutPage = (CheckoutPage) getIntent().getSerializableExtra("checkoutPage");
        setClient();
        configureWebViewSettings();
        addJsInterface();
        cbWebView.loadUrl(checkoutPage.getHostedPage().getUrl());
    }

    private void setClient() {
        cbWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                cbProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                cbProgressBar.setVisibility(View.GONE);
            }

        });
        cbWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                cbProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    cbProgressBar.setVisibility(View.GONE);
                }
            }

        });
    }

    private void configureWebViewSettings() {
        WebSettings webSettings = cbWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
    }

    private void addJsInterface() {
        cbWebView.addJavascriptInterface(new ChargebeeMobileListener(checkoutPage.getCheckoutCallbacks()), "mobileListener");
    }
}
