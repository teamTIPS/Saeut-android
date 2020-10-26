package com.teamtips.android.saeut.func.login.join.ui.main;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.teamtips.android.saeut.network.RequestHttpURLConnection_POST;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public class JoinViewModel extends ViewModel {

    private final static String Tag = "JoinViewModel";

    public JoinViewModel() {
        joinMutableLiveData.setValue(0);
    }
    public MutableLiveData<Integer> joinMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Integer> getJoinMutableLiveData() {
        return joinMutableLiveData;
    }

    public void setJoinMutableLiveData(){
        joinMutableLiveData.setValue(2);
    }

    public void Join(Joinin joinin){
        String url = "http://49.50.173.180:8080/saeut/user/essential";
        JSONObject join_json = new JSONObject();

        try {
            join_json.accumulate("id", joinin.getId());
            join_json.accumulate("password", joinin.getPassword());
            join_json.accumulate("name", joinin.getName());
            join_json.accumulate("phone", joinin.getPhonenum());
            join_json.accumulate("birth", joinin.getBirth());
            join_json.accumulate("gender", joinin.getGender());
            join_json.accumulate("perm1", joinin.isPerm());
            Log.e(Tag,joinin.getId()+ ", " +joinin.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        NetworkTask_JOIN networkTask_JOIN = new NetworkTask_JOIN(url, join_json.toString(), joinMutableLiveData);
        networkTask_JOIN.execute();
    }

    private boolean isIdValid(Joinin joinin) {
        if (joinin.getId() == null) {
            return false;
        }
        if (joinin.getId().contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(joinin.getId()).matches();
        } else {
            return !joinin.getId().trim().isEmpty();
        }
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 7;
    }

    public static class NetworkTask_JOIN extends AsyncTask<Void, Void, String> {

        private String url;
        private String json;
        private MutableLiveData<Integer> mutableLiveData;
        private int responseCode;

        public NetworkTask_JOIN(String url, String json, MutableLiveData<Integer> joinMutableLiveData) {
            this.url = url;
            this.json = json;
            this.mutableLiveData = joinMutableLiveData;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection_POST requestHttpURLConnection = new RequestHttpURLConnection_POST();
            result = requestHttpURLConnection.request(url, json); // 해당 URL로 부터 결과물을 얻어온다.
            responseCode = requestHttpURLConnection.getResponseCode(); // HTTP 통신 결과의 ResponseCode를 할당
            Log.e(Tag, String.valueOf(responseCode));
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // 회원가입 API의 결과의 ResponseCode로 결과 판단
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Log.e(Tag,"회원가입 성공");
                mutableLiveData.setValue(2);
            }
            else {
                Log.e(Tag,"회원가입 실패");
            }
        }
    }
}