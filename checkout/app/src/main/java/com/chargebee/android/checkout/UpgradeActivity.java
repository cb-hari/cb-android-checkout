package com.chargebee.android.checkout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chargebee.Result;
import com.chargebee.checkout.Chargebee;
import com.chargebee.checkout.Chargebee.CheckoutCallbacksBuilder;
import com.chargebee.checkout.OnCloseCheckoutCallback;
import com.chargebee.checkout.OnSuccessCheckoutCallback;
import com.chargebee.models.HostedPage;

/**
 * Created by cb-hariprasath on 18/11/17.
 */

public class UpgradeActivity extends AppCompatActivity {

    TextView trial;
    private ProgressDialog progress;

    Button oneMonth;
    Button threeMonth;
    Button sixMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        trial = (TextView) findViewById(R.id.trial_text);
        oneMonth = (Button) findViewById(R.id.one_month);
        threeMonth = (Button) findViewById(R.id.three_month);
        sixMonth = (Button) findViewById(R.id.six_month);
        trial.setText(Html.fromHtml("<h1>You are currently in trial plan</h1>"));
        oneMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new CheckoutReq(UpgradeActivity.this, "1-month").execute();
                WynkData.paidDisp = "<h1>1 Month Subscription Successful.</h1>";
            }
        });
        threeMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new CheckoutReq(UpgradeActivity.this, "3-months").execute();
                WynkData.paidDisp = "<h1>3 Months Subscription Successful.</h1>";
            }
        });
        sixMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new CheckoutReq(UpgradeActivity.this, "6-months").execute();
                WynkData.paidDisp = "<h1>6 Months Subscription Successful.</h1>";
            }
        });
    }

    private class CheckoutReq extends AsyncTask<String, Void, Result> {
        private final Context context;
        private Exception ex = null;
        private String planId;

        public CheckoutReq(Context c, String planId) {
            this.context = c;
            this.planId = planId;
        }


        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(this.context);
            progress.setMessage("Opening Checkout...");
            progress.show();
        }

        @Override
        protected Result doInBackground(String... params) {
            try {
                Result result = HostedPage.checkoutExisting()
                        .subscriptionId(WynkData.subscriptionId)
                        .subscriptionPlanId(planId).request();
                return result;
            } catch (Exception e) {
                ex = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Result result) {
            progress.dismiss();
            if (ex != null) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                openWebview(result.hostedPage());
            }
        }

    }

    private void openWebview(HostedPage hostedPage) {
        Chargebee.openCheckout(getApplicationContext(), hostedPage,
                CheckoutCallbacksBuilder.b().setOnCloseCheckoutCallback(new OnCloseCheckoutCallback() {
                    @Override
                    public void onClose() {
                        Intent intent = new Intent(getApplicationContext(), PaidSubscriptionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        getApplicationContext().startActivity(intent);
                        finish();
                    }
                }).setOnSuccessCheckoutCallback(new OnSuccessCheckoutCallback() {
                    @Override
                    public void onSuccess(String hostedPageId) {
                        Log.d("hosted_page", "cb success event");
                    }
                }));
    }
}
