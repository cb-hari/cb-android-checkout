package sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.chargebee.android.lib.models.HostedPage;

import java.io.Serializable;

import sdk.Chargebee.Options;

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
