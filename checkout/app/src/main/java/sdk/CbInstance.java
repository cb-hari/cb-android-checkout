package sdk;

import android.content.Context;
import android.content.Intent;

import com.chargebee.android.lib.models.HostedPage;
import com.chargebee.android.lib.models.PortalSession;

import java.io.Serializable;

import sdk.Chargebee.Options;


/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class CbInstance {
    public Options options;
    public CheckoutPage checkoutPage;
    public PortalPage portalPage;

    public CbInstance setOptions(Options options) {
        this.options = options;
        return this;
    }

    public void openCheckout(Context context, HostedPage hostedPage, CheckoutCallbacks checkoutCallbacks) {
        checkoutPage = new CheckoutPage(hostedPage, checkoutCallbacks);
        openChargebeePage("checkout", context);
    }

    public void openPortal(Context context, PortalSession portalSession, PortalCallbacks portalCallbacks) {
        portalPage = new PortalPage(portalSession, portalCallbacks);
        openChargebeePage("portal", context);
    }

    public CheckoutPage getCheckoutPage() {
        return checkoutPage;
    }

    public PortalPage getPortalPage() {
        return portalPage;
    }

    private void openChargebeePage(String type, Context context) {
        Intent intent = new Intent(context.getApplicationContext(), ChargebeeWebViewActivity.class);
        intent.putExtra("cb_type", type);
        context.getApplicationContext().startActivity(intent);
    }
}
