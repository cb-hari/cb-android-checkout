/*
 * Copyright (c) 2011 chargebee.com
 * All Rights Reserved.
 */

package com.chargebee.android.lib.exceptions;

import com.chargebee.android.lib.APIException;

import org.json.JSONObject;


public class InvalidRequestException extends APIException {

    public InvalidRequestException(int httpStatusCode,JSONObject jsonObj) throws Exception {
        super(httpStatusCode,jsonObj);
    }
    
}
