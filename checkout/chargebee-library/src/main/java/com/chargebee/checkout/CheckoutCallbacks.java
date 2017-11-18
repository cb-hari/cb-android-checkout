package com.chargebee.checkout;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class CheckoutCallbacks {
    private OnCloseCheckoutCallback onCloseCheckoutCallback;
    private OnLoadCheckoutCallback onLoadCheckoutCallback;
    private OnSuccessCheckoutCallback onSuccessCheckoutCallback;

    public OnCloseCheckoutCallback getOnCloseCheckoutCallback() {
        return onCloseCheckoutCallback;
    }

    public OnLoadCheckoutCallback getOnLoadCheckoutCallback() {
        return onLoadCheckoutCallback;
    }

    public OnSuccessCheckoutCallback getOnSuccessCheckoutCallback() {
        return onSuccessCheckoutCallback;
    }

    public CheckoutCallbacks setOnCloseCheckoutCallback(OnCloseCheckoutCallback onCloseCheckoutCallback) {
        this.onCloseCheckoutCallback = onCloseCheckoutCallback;
        return this;
    }

    public CheckoutCallbacks setOnLoadCheckoutCallback(OnLoadCheckoutCallback onLoadCheckoutCallback) {
        this.onLoadCheckoutCallback = onLoadCheckoutCallback;
        return this;
    }

    public CheckoutCallbacks setOnSuccessCheckoutCallback(OnSuccessCheckoutCallback onSuccessCheckoutCallback) {
        this.onSuccessCheckoutCallback = onSuccessCheckoutCallback;
        return this;
    }

}
