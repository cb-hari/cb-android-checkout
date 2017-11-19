package com.chargebee.checkout;

import android.content.Context;

import com.chargebee.models.HostedPage;
import com.chargebee.models.PortalSession;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class Chargebee {

    private static CbInstance cbInstance;

    public static CbInstance getCbInstance() {
        if (cbInstance == null) {
            cbInstance = new CbInstance();
        }
        return cbInstance;
    }

    public static CbInstance init(Options options) {
        return getCbInstance().setOptions(options);
    }

    public static void openCheckout(Context context, HostedPage hostedPage, CheckoutCallbacks checkoutCallbacks) {
        getCbInstance().openCheckout(context, hostedPage, checkoutCallbacks);
    }

    public static void openPortal(Context context, PortalSession portalSession, PortalCallbacks portalCallbacks) {
        getCbInstance().openPortal(context, portalSession, portalCallbacks);
    }

    public static class Options {
        private String site;
        private String domain;

        public Options site(String site) {
            this.site = site;
            return this;
        }

        public Options domain(String domain) {
            this.domain = domain;
            return this;
        }

    }

    public static class OptionsBuilder {
        public static Options b() {
            return new Options();
        }
    }

    public static class CheckoutCallbacksBuilder {
        public static CheckoutCallbacks b() {
            return new CheckoutCallbacks();
        }
    }

    public static class PortalCallbacksBuilder {
        public static PortalCallbacks b() {
            return new PortalCallbacks();
        }
    }
}
