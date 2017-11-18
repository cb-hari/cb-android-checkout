package com.chargebee;

import com.chargebee.ListResult.Entry;
import com.chargebee.internal.ResultBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * To represent list output of ChargeBee APIs (like getting list of subscriptions !!)
 */
public class ListResult extends ArrayList<Entry> {

    /**
     * represents each entry in this result-list
     */
    public static class Entry extends ResultBase {

        public Entry(JSONObject entryJson) {
            super(entryJson);
        }
    }

    public final int httpCode;
    private JSONObject respJson; // the entire json-response

    public ListResult(int httpCode, JSONObject respJson) {
        this.httpCode = httpCode;
        this.respJson = respJson;
        initEntries();
    }

    private void initEntries() {
        try {
            JSONArray arr = respJson.getJSONArray("list");
            for (int i = 0; i < arr.length(); i++) {
                add(new Entry(arr.getJSONObject(i)));
            }
        } catch(JSONException exp) {
            throw new RuntimeException(exp.getMessage());
        }
    }

    public String nextOffset() {
        String cursor = respJson.optString("next_offset");
        return (cursor == null || cursor.isEmpty()) ? null : cursor;
    }

    @Override
    public String toString() {
        try {
            return respJson.toString(2);
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
    }
}
