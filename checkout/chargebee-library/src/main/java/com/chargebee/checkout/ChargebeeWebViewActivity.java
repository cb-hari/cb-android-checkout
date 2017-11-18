package com.chargebee.checkout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chargebee.R;
import com.chargebee.Result;
import com.chargebee.models.PortalSession;


/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class ChargebeeWebViewActivity extends AppCompatActivity {
    WebView cbWebView;
    ProgressBar cbProgressBar;
    CheckoutPage checkoutPage;
    PortalPage portalPage;
    String url;
    Boolean isCheckout = false;
    Boolean isPortal = false;
    private ProgressDialog progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        cbWebView = (WebView) findViewById(R.id.cbWebView);
        cbProgressBar = (ProgressBar) findViewById(R.id.cbProgressBar);
        setChargebeePage();
        setClient();
        configureWebViewSettings();
        cbWebView.loadUrl(domainProxy(url));
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
                cbWebView.clearHistory();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.d("qazwsx", request.getUrl().toString());
                return super.shouldOverrideUrlLoading(view, request);
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
        setDownloadListener();
    }

    private void setDownloadListener() {
        cbWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    private void configureWebViewSettings() {
        WebSettings webSettings = cbWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(false);
    }

    private void setChargebeePage() {
        switch (getIntent().getStringExtra("cb_type")) {
            case "checkout": {
                checkoutPage = Chargebee.getCbInstance().getCheckoutPage();
                url = checkoutPage.getHostedPage().url();
                cbWebView.addJavascriptInterface(new ChargebeeMobileListener(ChargebeeWebViewActivity.this,
                        checkoutPage.getCheckoutCallbacks()), "mobileListener");
                isCheckout = true;
                isPortal = false;
            }
            break;
            case "portal": {
                portalPage = Chargebee.getCbInstance().getPortalPage();
                url = portalPage.getPortalSession().accessUrl();
                cbWebView.addJavascriptInterface(new ChargebeeMobileListener(ChargebeeWebViewActivity.this,
                        portalPage.getPortalCallbacks()), "mobileListener");
                isPortal = true;
                isCheckout = false;
            }
            break;
            default:
                throw new RuntimeException("Invalid cb_type");
        }
    }

    private String domainProxy(String url) {
        return url.replaceFirst("mannar-test.localcb.in:8080", "f51adba4.ngrok.io");
    }

    @Override
    public void onBackPressed() {
        if (cbWebView.canGoBack()) {
            cbWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class LogoutPortalReq extends AsyncTask<String, Void, Result> {
        private final Context context;
        private PortalSession portalSession;
        private Exception ex = null;

        public LogoutPortalReq(Context c, PortalSession portalSession) {
            this.context = c;
            this.portalSession = portalSession;
        }


        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(this.context);
            progress.setMessage("Logging out of portal...");
            progress.show();
        }

        @Override
        protected Result doInBackground(String... params) {
            try {
                return PortalSession.logout(portalSession.id()).request();
            } catch (Exception e) {
                ex = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Result result) {
            progress.dismiss();
            if (ex != null) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
