package com.chargebee.models;

import com.chargebee.internal.HttpUtil.Method;
import com.chargebee.internal.Params;
import com.chargebee.internal.Request;
import com.chargebee.internal.Resource;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by cb-hariprasath on 17/11/17.
 */


public class PortalSession extends Resource<PortalSession> {
    public PortalSession(String jsonStr) {
        super(jsonStr);
    }

    public PortalSession(JSONObject jsonObj) {
        super(jsonObj);
    }

    public String id() {
        return this.reqString("id");
    }

    public String token() {
        return this.reqString("token");
    }

    public String accessUrl() {
        return this.reqString("access_url");
    }

    public String redirectUrl() {
        return this.optString("redirect_url");
    }

    public PortalSession.Status status() {
        return (PortalSession.Status) this.reqEnum("status", PortalSession.Status.class);
    }

    public Timestamp createdAt() {
        return this.reqTimestamp("created_at");
    }

    public Timestamp expiresAt() {
        return this.optTimestamp("expires_at");
    }

    public String customerId() {
        return this.reqString("customer_id");
    }

    public Timestamp loginAt() {
        return this.optTimestamp("login_at");
    }

    public Timestamp logoutAt() {
        return this.optTimestamp("logout_at");
    }

    public String loginIpaddress() {
        return this.optString("login_ipaddress");
    }

    public String logoutIpaddress() {
        return this.optString("logout_ipaddress");
    }

    public List<LinkedCustomer> linkedCustomers() {
        return this.optList("linked_customers", PortalSession.LinkedCustomer.class);
    }

    public static PortalSession.CreateRequest create() throws IOException {
        String uri = uri(new String[]{"portal_sessions"});
        return new PortalSession.CreateRequest(Method.POST, uri);
    }

    public static Request retrieve(String id) throws IOException {
        String uri = uri(new String[]{"portal_sessions", nullCheck(id)});
        return new Request(Method.GET, uri);
    }

    public static Request logout(String id) throws IOException {
        String uri = uri(new String[]{"portal_sessions", nullCheck(id), "logout"});
        return new Request(Method.POST, uri);
    }

    public static PortalSession.ActivateRequest activate(String id) throws IOException {
        String uri = uri(new String[]{"portal_sessions", nullCheck(id), "activate"});
        return new PortalSession.ActivateRequest(Method.POST, uri);
    }

    public static class ActivateRequest extends Request<PortalSession.ActivateRequest> {
        private ActivateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }

        public PortalSession.ActivateRequest token(String token) {
            this.params.add("token", token);
            return this;
        }

        public Params params() {
            return this.params;
        }
    }

    public static class CreateRequest extends Request<PortalSession.CreateRequest> {
        private CreateRequest(Method httpMeth, String uri) {
            super(httpMeth, uri);
        }

        public PortalSession.CreateRequest redirectUrl(String redirectUrl) {
            this.params.addOpt("redirect_url", redirectUrl);
            return this;
        }

        public PortalSession.CreateRequest forwardUrl(String forwardUrl) {
            this.params.addOpt("forward_url", forwardUrl);
            return this;
        }

        public PortalSession.CreateRequest customerId(String customerId) {
            this.params.add("customer[id]", customerId);
            return this;
        }

        public Params params() {
            return this.params;
        }
    }

    public static class LinkedCustomer extends Resource<PortalSession.LinkedCustomer> {
        public LinkedCustomer(JSONObject jsonObj) {
            super(jsonObj);
        }

        public String customerId() {
            return this.reqString("customer_id");
        }

        public String email() {
            return this.optString("email");
        }

        public Boolean hasBillingAddress() {
            return this.reqBoolean("has_billing_address");
        }

        public Boolean hasPaymentMethod() {
            return this.reqBoolean("has_payment_method");
        }

        public Boolean hasActiveSubscription() {
            return this.reqBoolean("has_active_subscription");
        }
    }

    public static enum Status {
        CREATED,
        LOGGED_IN,
        LOGGED_OUT,
        NOT_YET_ACTIVATED,
        ACTIVATED,
        _UNKNOWN;

        private Status() {
        }
    }
}
