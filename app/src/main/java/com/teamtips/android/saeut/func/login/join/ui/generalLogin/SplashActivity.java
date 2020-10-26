package com.teamtips.android.saeut.func.login.join.ui.generalLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.teamtips.android.saeut.MainActivity;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.join.ui.service.SaveSharedPreference;

public class SplashActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private static final String Tag = "SplashActivity";
    boolean isloginSuccess = false; //true: 로그인 성공

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        startLoading();

        String Rt = SaveSharedPreference.getRT(this);
        if(Rt != null && !Rt.equals("")){
            //Rt로 로그인하기
            loginViewModel.login(Rt);
        }

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(LoginResult loginResult) {
                if (loginResult == null) {
                    Log.e(Tag,"loginResult == null");
                    return;
                }
                //로그인 실패
                if (loginResult.getError() != null) {
                    Log.e(Tag,"SplashActivity 로그인 실패");
                    //로그인 결과 초기화
                    loginResult.setOrigin();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                //로그인 성공
                if (loginResult.getSuccess() != null) {
                    isloginSuccess = true;
                    setResult(Activity.RESULT_OK);
                }
            }
        });
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isloginSuccess){
                    Log.e(Tag,"자동로그인 성공");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                else {
                    Log.e(Tag,"로그인 타임아웃, 로그인 화면으로 돌아갑니다.");
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        }, 2000);
    }
}
