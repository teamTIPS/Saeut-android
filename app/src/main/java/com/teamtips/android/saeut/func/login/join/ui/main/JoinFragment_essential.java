package com.teamtips.android.saeut.func.login.join.ui.main;

import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.join.ui.service.LoginNetwork;
import com.teamtips.android.saeut.network.RequestHttpURLConnection;
import com.teamtips.android.saeut.network.RequestHttpURLConnection_POST;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.http.Body;

public class JoinFragment_essential extends Fragment {

    private JoinViewModel mViewModel = new JoinViewModel();
    private final static String Tag = "JoinFragment_essential";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up_essential, container, false);


        //email == account_id
        EditText email_edit = root.findViewById(R.id.email_edit);
        EditText password_edit = root.findViewById(R.id.password_edit);
        EditText password_confirm = root.findViewById(R.id.password_confirm);

        EditText name_edit = root.findViewById(R.id.name_edit);
        EditText phone_edit = root.findViewById(R.id.phone_edit);
        EditText nickname_edit = root.findViewById(R.id.nickname_edit);
        EditText birth_edit = root.findViewById(R.id.birth_edit);
        RadioGroup gender_radio = root.findViewById(R.id.radioGender);
        Button email_sign_up_button = root.findViewById(R.id.email_sign_up_button);
        Button btn_check_id = root.findViewById(R.id.btn_check_id);

        email_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!isEmailValid(email_edit.getText().toString())){
                    Log.e(Tag,"이메일 형식 아님");

                    //아이디 옆의 초록원 빨갛게 셋팅
                }
            }
        });

        password_confirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!password_confirm.getText().toString().matches(password_edit.getText().toString())){
                    Log.e(Tag,"비밀번호 확인 틀렸음");
                }
            }
        });

        phone_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isNotNumber(phone_edit.getText().toString())){
                    Log.e(Tag,"번호 아님");
                }
            }
        });

        btn_check_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                JSONObject checkID_json = new JSONObject();
                Log.e(Tag,"btn_check_id.setOnClickListener");

//                try{
//                    checkID_json.accumulate("id", email_edit.getText().toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                String url = "http://49.50.173.180:8080/saeut/valid/id/" + email_edit.getText().toString();
//                NetworkTask_CheckID networkTask_checkID = new NetworkTask_CheckID(url, checkID_json.toString());
//                networkTask_checkID.execute();
            }
        });



        //닉네임 중복확인 api 사용, 버튼 만들어주세요
        email_sign_up_button.setOnClickListener(view -> {
            //if(/*초록원&&비밀번호확인 맞음&&폰번호인증완료*/){
                Joinin joinin = null;
                joinin = new Joinin(
                        email_edit.getText().toString(),
                        password_edit.getText().toString(),
                        name_edit.getText().toString(),
                        phone_edit.getText().toString(),
                        0,
                        birth_edit.getText().toString(),
                        true
                );
                Log.d(Tag, joinin.toString());
                mViewModel.Join(joinin);
          //  }

        });
        return root;
    }

    private boolean isNotNumber(String toString) {
        try{
            Integer.parseInt(toString);
            return true;
        }
        catch (NumberFormatException  e){
            return false;
        }
    }

    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return !email.trim().isEmpty();
        }
    }
    public static class NetworkTask_CheckID extends AsyncTask<String, Void, Boolean> {

        private String url;
        private String json;
        private int responseCode;

        public NetworkTask_CheckID(String url, String json) {
            this.url = url;
            this.json = json;
        }


        //result - true: 사용가능 / false: 중복
        @Override
        protected Boolean doInBackground(String... strings) {
            boolean result;
            RequestHttpURLConnection_POST requestHttpURLConnection = new RequestHttpURLConnection_POST();
            String temp = requestHttpURLConnection.request(url, json);
            result = temp.contains("true");
            Log.e(Tag,"requestHttpURLConnection: "+temp);

            responseCode = requestHttpURLConnection.getResponseCode(); // HTTP 통신 결과의 ResponseCode를 할당
            Log.e(Tag,"result:"+result);

            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Log.e(Tag,"onPostExecute");
            if(aBoolean){
                Log.e(Tag,"아이디 사용 가능입니다.");
            }
            else{
                Log.e(Tag,"아이디 중복입니다.");
            }

        }

//        @Override
//        protected void onPostExecute(boolean s) {
//            super.onPostExecute(s);
//
//            if(s){
//                try {
//                    JSONObject result_json = new JSONObject(s);
//                    boolean result = result_json.getBoolean("result");
//                    Log.e(Tag,"result:"+ result);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            else{
//                Log.e(Tag,"TextUtils.isEmpty(s)");
//            }
//            Log.e(Tag,s);
//        }
    }
}
