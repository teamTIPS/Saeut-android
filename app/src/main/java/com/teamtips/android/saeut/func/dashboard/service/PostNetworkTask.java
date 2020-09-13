package com.teamtips.android.saeut.func.dashboard.service;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.teamtips.android.saeut.func.dashboard.DashboardChildAdapter;
import com.teamtips.android.saeut.network.RequestHttpURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Android - Server 연결 하는 Controller Class
 * */

public class PostNetworkTask extends AsyncTask<Void, Void, String> {

    private String url;
    private ContentValues values;
    private DashboardChildAdapter dashboardChildAdapter;

    public PostNetworkTask(String url, ContentValues values, DashboardChildAdapter dashboardChildAdapter) {

        this.url = url;
        this.values = values;
        this.dashboardChildAdapter = dashboardChildAdapter;
    }

    @Override
    protected String doInBackground(Void... params) {

        String result; // 요청 결과를 저장할 변수.
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            JSONArray jsonArray = new JSONArray(s);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject json = jsonArray.getJSONObject(i);
                Log.e("PostNetworkTask", json.toString());
                int post_id = json.getInt("post_id");
                String id = json.getString("account_id");
                String title = json.getString("title");
                Date start_date = sdf.parse("2020-01-01");
                Date due_date = sdf.parse("2020-01-01");
                dashboardChildAdapter.addItem(post_id, id, title, start_date, due_date);
                dashboardChildAdapter.notifyDataSetChanged();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
