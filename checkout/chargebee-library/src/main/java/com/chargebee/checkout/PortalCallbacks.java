package com.chargebee.checkout;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class PortalCallbacks {

    private LogoutPortalCallback logoutPortalCallback;

    public PortalCallbacks setLogoutPortalCallback(LogoutPortalCallback logoutPortalCallback) {
        this.logoutPortalCallback = logoutPortalCallback;
        return this;
    }

    public LogoutPortalCallback getLogoutPortalCallback() {
        return logoutPortalCallback;
    }
}
