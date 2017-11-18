package com.chargebee.android.sdk;

import android.content.Context;

import com.chargebee.android.sdk.Chargebee.Options;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class CbInstance {
    public Options options;
    public Context context;

    public CbInstance(Options options, Context context) {
        this.options = options;
        this.context = context;
    }

    public void openCheckout(HostedPage hostedPage, CheckoutCallbacks checkoutCallbacks) {
        new CheckoutPage(hostedPage, checkoutCallbacks, context, options).open();
    }
}
