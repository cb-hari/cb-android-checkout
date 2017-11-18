package com.chargebee.checkout;

import android.app.Activity;
import android.webkit.JavascriptInterface;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class ChargebeeMobileListener {
    CheckoutCallbacks checkoutCallbacks;
    PortalCallbacks portalCallbacks;
    Activity activity;

    ChargebeeMobileListener(Activity activity, CheckoutCallbacks checkoutCallbacks) {
        this.activity = activity;
        this.checkoutCallbacks = checkoutCallbacks;
    }

    ChargebeeMobileListener(Activity activity, PortalCallbacks portalCallbacks) {
        this.activity = activity;
        this.portalCallbacks = portalCallbacks;
    }

    @JavascriptInterface
    public boolean postMessage(String event, String target) {
        switch (event) {
            case "cb.close":
                activity.finish();
                checkoutCallbacks.getOnCloseCheckoutCallback().onClose();
                return true;
        }
        return false;
    }
}
