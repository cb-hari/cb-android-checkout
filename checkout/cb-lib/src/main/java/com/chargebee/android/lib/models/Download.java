package com.chargebee.android.lib.models;

import com.chargebee.android.lib.internal.Resource;

import org.json.JSONObject;

import java.sql.Timestamp;

public class Download extends Resource<Download> {

    //Constructors
    //============

    public Download(String jsonStr) {
        super(jsonStr);
    }

    public Download(JSONObject jsonObj) {
        super(jsonObj);
    }

    // Fields
    //=======

    public String downloadUrl() {
        return reqString("download_url");
    }

    public Timestamp validTill() {
        return reqTimestamp("valid_till");
    }

    // Operations
    //===========


}
