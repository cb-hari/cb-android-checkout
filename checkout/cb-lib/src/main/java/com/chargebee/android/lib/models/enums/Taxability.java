package com.chargebee.android.lib.models.enums;

public enum Taxability {
    TAXABLE,
    EXEMPT,
    _UNKNOWN; /*Indicates unexpected value for this enum. You can get this when there is a
    java-client version incompatibility. We suggest you to upgrade to the latest version */
}