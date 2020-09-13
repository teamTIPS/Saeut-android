package com.teamtips.android.saeut.func.login.ui.generalLogin;

import android.content.Context;
import android.util.Log;

import com.auth0.jwt.JWT;
import com.google.gson.JsonObject;
import com.teamtips.android.saeut.GlobalApplication;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class UpdateRefreshToken implements Callback, retrofit2.Callback<JsonObject> {
    private static final String Tag = "UpdateRefreshToken";
    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        String rt = response.body().get("RefreshToken").toString();
        SaveSharedPreference.setRT(GlobalApplication.getGlobalApplicationContext(), rt);
    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        Log.e(Tag,"UpdateRefreshToken onFailure");
    }
}
