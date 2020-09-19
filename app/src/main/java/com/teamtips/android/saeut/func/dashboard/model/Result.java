package com.teamtips.android.saeut.func.dashboard.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("request")
    private JsonObject request;
    @SerializedName("response")
    private JsonObject response;

    public JsonObject getRequest() {
        return request;
    }

    public void setRequest(JsonObject request) {
        this.request = request;
    }

    public JsonObject getResponse() {
        return response;
    }

    public void setResponse(JsonObject response) {
        this.response = response;
    }
}
