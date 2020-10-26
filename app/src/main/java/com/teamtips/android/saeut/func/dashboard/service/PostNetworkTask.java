package com.teamtips.android.saeut.func.dashboard.service;

import android.content.ContentValues;
import android.os.AsyncTask;
import com.teamtips.android.saeut.func.dashboard.DashboardListAdapter;
import com.teamtips.android.saeut.network.RequestHttpURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;

/*
 * Android - Server 연결 하는 Controller Class
 * */
public class PostNetworkTask extends AsyncTask<Void, Void, String> {

    private String url;
    private ContentValues values;
    private DashboardListAdapter dashboardListAdapter;

    public PostNetworkTask(String url, ContentValues values, DashboardListAdapter dashboardListAdapter) {

        this.url = url;
        this.values = values;
        this.dashboardListAdapter = dashboardListAdapter;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            JSONArray jsonArray = new JSONArray(s);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject json = jsonArray.getJSONObject(i);

                int post_id = json.getInt("post_id");
                String id = json.getString("id");
                String title = json.getString("title");
                String post_date = json.getString("post_date");
                String contents = json.getString("contents");
                String start_date = json.getString("start_date");
                String due_date = json.getString("due_date");
                int type = Integer.parseInt(json.getString("type"));

                dashboardListAdapter.addItem(post_id, id, title, post_date, contents, start_date, due_date, type);
                dashboardListAdapter.notifyDataSetChanged();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
