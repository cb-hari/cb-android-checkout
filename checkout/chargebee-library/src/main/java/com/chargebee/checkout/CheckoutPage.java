package com.chargebee.checkout;

import com.chargebee.models.HostedPage;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class CheckoutPage {
    private HostedPage hostedPage;
    private CheckoutCallbacks checkoutCallbacks;

    CheckoutPage(HostedPage hostedPage, CheckoutCallbacks checkoutCallbacks) {
        this.hostedPage = hostedPage;
        this.checkoutCallbacks = checkoutCallbacks;
    }

    public HostedPage getHostedPage() {
        return hostedPage;
    }

    public CheckoutCallbacks getCheckoutCallbacks() {
        return checkoutCallbacks;
    }

}
