package com.chargebee.android.checkout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chargebee.android.lib.ListResult;
import com.chargebee.android.lib.Result;
import com.chargebee.android.lib.filters.enums.SortOrder;
import com.chargebee.android.lib.models.PortalSession;
import com.chargebee.android.lib.models.Subscription;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cb-hariprasath on 18/11/17.
 */

public class SignupActivity extends AppCompatActivity {

    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_signup)
    Button _signupButton;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    public void signup() {
        String email = _emailText.getText().toString();
//        String password = _passwordText.getText().toString();
        if (email == null || email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_LONG).show();
            return;
        }
//        if (password == null || password.isEmpty()) {
//            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_LONG).show();
//            return;
//        }
        new CreateTrialSubscription(SignupActivity.this, email).execute();
    }

    private class CreateTrialSubscription extends AsyncTask<String, Void, Result> {
        private final Context context;
        private Exception ex = null;
        String email;

        public CreateTrialSubscription(Context c, String email) {
            this.context = c;
            this.email = email;
        }


        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(this.context);
            progress.setMessage("Creating trial subscription...");
            progress.show();
        }

        @Override
        protected Result doInBackground(String... params) {
            try {
                Result result = Subscription.create()
                        .id("dummy" + System.currentTimeMillis())
                        .customerEmail(email)
                        .planId("trial-30days")
                        .request();
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
                WynkData.subscriptionId = result.subscription().id();
                WynkData.customerId = result.customer().id();
                Intent intent = new Intent(getApplicationContext(), TrialSubscriptionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getApplicationContext().startActivity(intent);
                finish();
            }
        }

    }
}
