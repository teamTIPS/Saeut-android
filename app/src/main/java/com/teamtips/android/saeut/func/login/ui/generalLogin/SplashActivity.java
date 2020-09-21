package com.teamtips.android.saeut.func.login.ui.generalLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.teamtips.android.saeut.MainActivity;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.ui.generalLogin.LoginResult;
import com.teamtips.android.saeut.func.login.ui.generalLogin.LoginViewModel;
import com.teamtips.android.saeut.func.login.ui.generalLogin.LoginViewModelFactory;
import com.teamtips.android.saeut.func.login.ui.generalLogin.SaveSharedPreference;

public class SplashActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private static final String Tag = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String Rt = SaveSharedPreference.getRT(this);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        if(Rt != null && !Rt.equals("")){
            //Rt로 로그인하기
            loginViewModel.login(Rt);
        }
        startLoading();

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
                }
                //로그인 성공
                if (loginResult.getSuccess() != null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    setResult(Activity.RESULT_OK);
                    finish();
                }
            }
        });
    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(Tag,"시간 지연으로 로그인 실패");
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, 3000);
    }
}
