package com.chargebee.android.checkout;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.chargebee.Result;
import com.chargebee.checkout.Chargebee;
import com.chargebee.checkout.Chargebee.PortalCallbacksBuilder;
import com.chargebee.models.PortalSession;


public class PaidSubscriptionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_subscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView t = (TextView) findViewById(R.id.success_text);
        t.setText(Html.fromHtml(WynkData.paidDisp));
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        if (id == R.id.nav_my_sub) {
            new PortalReq(PaidSubscriptionActivity.this).execute();
        }
        return true;
    }

    private class PortalReq extends AsyncTask<String, Void, Result> {
        private final Context context;
        private Exception ex = null;

        public PortalReq(Context c) {
            this.context = c;
        }


        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(this.context);
            progress.setMessage("Creating portal session...");
            progress.show();
        }

        @Override
        protected Result doInBackground(String... params) {
            try {
                Result result = PortalSession.create()
                        .customerId(WynkData.customerId)
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
                openWebview(result.portalSession());
            }
        }

    }

    private void openWebview(final PortalSession portalSession) {
        Chargebee.openPortal(getApplicationContext(), portalSession,
                PortalCallbacksBuilder.b());
    }
}
