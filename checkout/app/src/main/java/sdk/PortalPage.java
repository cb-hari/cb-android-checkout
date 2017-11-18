package sdk;

import com.chargebee.android.lib.models.PortalSession;

/**
 * Created by cb-hariprasath on 17/11/17.
 */

public class PortalPage {
    private PortalSession portalSession;
    private PortalCallbacks portalCallbacks;

    PortalPage(PortalSession portalSession, PortalCallbacks portalCallbacks){
        this.portalSession = portalSession;
        this.portalCallbacks = portalCallbacks;
    }

    public PortalSession getPortalSession() {
        return portalSession;
    }

    public PortalCallbacks getPortalCallbacks() {
        return portalCallbacks;
    }
}
