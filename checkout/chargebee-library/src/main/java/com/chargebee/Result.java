package com.chargebee;

import com.chargebee.internal.ResultBase;

import org.json.JSONObject;

public class Result extends ResultBase {

    public final int httpCode;

    public Result(int httpCode, JSONObject jsonObj) {
        super(jsonObj);
        this.httpCode = httpCode;
    }
}
