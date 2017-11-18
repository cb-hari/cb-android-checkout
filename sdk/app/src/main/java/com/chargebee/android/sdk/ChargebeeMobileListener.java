package com.chargebee.android.sdk;

import android.webkit.JavascriptInterface;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class ChargebeeMobileListener {
    CheckoutCallbacks checkoutCallbacks;

    ChargebeeMobileListener(CheckoutCallbacks checkoutCallbacks) {
        this.checkoutCallbacks = checkoutCallbacks;
    }

    @JavascriptInterface
    public boolean postMessage(String event, String target) {
        switch (event) {
            case "cb.close":
                checkoutCallbacks.getOnCloseCheckoutCallback().onClose();
                return true;
        }
        return false;
    }
}
