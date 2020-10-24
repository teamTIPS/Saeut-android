package com.teamtips.android.saeut.func.login.join.ui.main;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.graphics.Color;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.join.ui.service.LoginNetwork;
import com.teamtips.android.saeut.func.login.join.ui.service.LoginNetworkService;
import com.teamtips.android.saeut.network.RequestHttpURLConnection;
import com.teamtips.android.saeut.network.RequestHttpURLConnection_POST;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class JoinFragment_essential extends Fragment {

    private JoinViewModel mViewModel = new JoinViewModel();
    private final static String Tag = "JoinFragment_essential";
    private LoginNetworkService loginNetworkService = new LoginNetworkService();

    /* Retrofit */
    private Gson gson = new GsonBuilder().setLenient().create();
    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
    private OkHttpClient client = builder.build();
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://49.50.173.180:8080/saeut/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private int gender; // gender radio 클릭 결과 저장용

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
        CheckBox marketing_cb = (CheckBox)root.findViewById(R.id.check_marketing);
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
                if (!isEmailValid(email_edit.getText().toString())) {
                    Log.e(Tag, "이메일 형식 아님");

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
                if (!password_confirm.getText().toString().matches(password_edit.getText().toString())) {
                    Log.e(Tag, "비밀번호 확인 틀렸음");
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
                if (isNotNumber(phone_edit.getText().toString())) {
                    Log.e(Tag, "번호 아님");
                }
            }
        });

        //닉네임 중복확인 api 사용, 버튼 만들어주세요
        btn_check_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit.create(LoginNetwork.class).validEmail(email_edit.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String result = response.body();
                            if (result.equals("true")) {
                                AlertDialog.Builder emailCheckDialog = new AlertDialog.Builder(getContext());

                                emailCheckDialog.setMessage("사용 가능한 Email 입니다.")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Log.i("Dialog", "확인");
                                            }
                                        })
                                        .setCancelable(true) // 백버튼으로 팝업창이 닫히지 않도록 한다.
                                        .show();

                                // 중복 확인 끝났으니까
                                btn_check_id.setClickable(false);
                                btn_check_id.setText("확인완료");
                            }
                            Log.d(Tag, "성공  : " + response.body());
                        } else {
                            Log.d(Tag, "실패  : " + response.code());
                            AlertDialog.Builder emailCheckDialog = new AlertDialog.Builder(getContext());

                            emailCheckDialog.setMessage("중복된 Email 입니다. 다른 Email을 입력해주세요.")
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

        gender_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_woman) {
                    // 여성 클릭시
                    gender = 0;
                } else if (checkedId == R.id.rb_man) {
                    // 남성 클릭
                    gender = 1;
                }
            }
        });

        email_sign_up_button.setOnClickListener(view -> {
            //if(/*초록원&&비밀번호확인 맞음&&폰번호인증완료*/){
            // 아이디 중복확인 했니?
            if (btn_check_id.isClickable()) {
                // 안했으면 하렴
                AlertDialog.Builder emailCheckDialog = new AlertDialog.Builder(getContext());

                emailCheckDialog.setMessage("Email 중복을 확인해주세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Dialog", "확인");
                            }
                        })
                        .setCancelable(true) // 백버튼으로 팝업창이 닫히지 않도록 한다.
                        .show();
            } else {
                Joinin joinin = new Joinin(
                        email_edit.getText().toString(),
                        password_edit.getText().toString(),
                        name_edit.getText().toString(),
                        phone_edit.getText().toString(),
                        gender,
                        birth_edit.getText().toString(),
                        marketing_cb.isChecked()
                );
                Log.d(Tag, joinin.toString());
//                mViewModel.Join(joinin);
            }

        });
        return root;
    }

    private boolean isNotNumber(String toString) {
        try {
            Integer.parseInt(toString);
            return true;
        } catch (NumberFormatException e) {
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
}