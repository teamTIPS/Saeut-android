package com.teamtips.android.saeut.func.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Board;
import com.teamtips.android.saeut.func.dashboard.service.PostNetwork;
import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;
import com.teamtips.android.saeut.func.login.join.ui.service.LoginNetwork;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class modify_account_info extends AppCompatActivity {

    private final String Tag = "modify_account_info";


    private Gson gson = new GsonBuilder().setLenient().create();
    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
    private OkHttpClient client = builder.build();
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://49.50.173.180:8080/saeut/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up_additional);
        context = this;
        Button btn_check_nickname = findViewById(R.id.btn_check_nickname);
        EditText address1_edit = findViewById(R.id.address1_edit);
        EditText nickname_edit = findViewById(R.id.nickname_edit);
        Button additional_btn = findViewById(R.id.additional_btn);

        btn_check_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit.create(ProfileNetworkService.class).validNickname(nickname_edit.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String result = response.body();
                            Log.e(Tag,result);
                            if (result.equals("true")) {
                                AlertDialog.Builder emailCheckDialog = new AlertDialog.Builder(context);

                                emailCheckDialog.setMessage("사용 가능한 닉네임입니다.")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Log.i("Dialog", "확인");
                                            }
                                        })
                                        .setCancelable(true) // 백버튼으로 팝업창이 닫히지 않도록 한다.
                                        .show();

                                // 중복 확인 끝났으니까
                                btn_check_nickname.setClickable(false);
                                btn_check_nickname.setText("확인완료");
                            }
                            Log.d(Tag, "성공  : " + response.body());
                        } else {
                            Log.d(Tag, "실패  : " + response.code());
                            AlertDialog.Builder emailCheckDialog = new AlertDialog.Builder(context);

                            emailCheckDialog.setMessage("중복된 닉네임입니다. 다른 닉네임을 입력해주세요.")
                                    .setPositiveButton("다시 입력하기", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Log.i("Dialog", "확인");
                                        }
                                    })
                                    .setCancelable(true) // 백버튼으로 팝업창이 닫히지 않도록 한다.
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(Tag, "실패  : " + t.getMessage());
                    }
                });
            }
        });

        additional_btn.setOnClickListener(view -> {
            LoggedInUser loggedInUser = LoggedInUser.getLoggedInUser();
            loggedInUser.setNickname(nickname_edit.getText().toString());
            Log.e(Tag,"@@@@@@@@@id: "+nickname_edit.getText().toString());
            loggedInUser.setAddress1(address1_edit.getText().toString());


            UserAdditional userAdditional = new UserAdditional(loggedInUser.getId(), loggedInUser.getAddress1(), loggedInUser.getNickname());
            String url = "http://49.50.173.180:8080/saeut/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            ProfileNetworkService profileNetworkService = retrofit.create(ProfileNetworkService.class);
            profileNetworkService.putuserAdditional(userAdditional).enqueue(responseback);
            finish();
        });
    }
    Callback<String> responseback = new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {

            Log.e(Tag,"@@@@@@@@@@@@@@@@@@@@@@@@서버통신 성공: "+LoggedInUser.getLoggedInUser().getId());
            if(response.isSuccessful()){
                Log.d(Tag, "성공  : " + response.toString());
            }
            else {
                Log.d(Tag, "응답 실패  : " + response.code());
            }

        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {

        }
    };
}