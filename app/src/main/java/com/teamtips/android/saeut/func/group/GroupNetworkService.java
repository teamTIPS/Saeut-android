package com.teamtips.android.saeut.func.group;

import com.google.gson.JsonObject;
import com.teamtips.android.saeut.data.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GroupNetworkService {

    @GET("usergroup/{id}")
    Call<List<Group>> getGroup(@Path("id") String id);
}
