package com.chargebee.android.sdk;

import android.content.Context;
import android.util.Log;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class Chargebee {
    public static CbInstance init(Options options, Context context) {
        return new CbInstance(options, context);
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

    public static void main(String[] args) {
        CbInstance cbInstance = Chargebee.init(OptionsBuilder.b().site("mannar-test"), null);
        cbInstance.openCheckout(new HostedPage().setUrl("http://ffcf2979.ngrok.io/hosted_pages/plans/professional"),
                CheckoutCallbacksBuilder.b().setOnCloseCheckoutCallback(new OnCloseCheckoutCallback() {
                    @Override
                    public void onClose() {
                        Log.d("hosted_page", "cb close event");
                    }
                }).setOnSuccessCheckoutCallback(new OnSuccessCheckoutCallback() {
                    @Override
                    public void onSuccess(String hostedPageId) {
                        Log.d("hosted_page", "cb success event");
                    }
                }));
    }
}
