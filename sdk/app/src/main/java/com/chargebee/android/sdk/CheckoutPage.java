package com.chargebee.android.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.chargebee.android.sdk.Chargebee.Options;

import java.io.Serializable;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class CheckoutPage implements Serializable {
    private HostedPage hostedPage;
    private CheckoutCallbacks checkoutCallbacks;
    private Context context;
    private Options options;

    CheckoutPage(HostedPage hostedPage, CheckoutCallbacks checkoutCallbacks, Context context, Options options) {
        this.hostedPage = hostedPage;
        this.checkoutCallbacks = checkoutCallbacks;
        this.context = context;
        this.options = options;
    }

    public void open() {
        Intent intent = new Intent(context, ChargebeeWebViewActivity.class);
        intent.putExtra("checkoutPage", this);
        context.startActivity(intent);
    }

    public HostedPage getHostedPage() {
        return hostedPage;
    }

    public CheckoutCallbacks getCheckoutCallbacks() {
        return checkoutCallbacks;
    }
}
