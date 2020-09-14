package com.teamtips.android.saeut.func.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.teamtips.android.saeut.MainActivity;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.join.JoinActivity;
import com.teamtips.android.saeut.func.login.ui.generalLogin.LoginActivity;

/*
public class KakaoLoginActivity extends AppCompatActivity {

    !!!사용이 끝난 더미 액티비티입니다!!!
    !!!코드모음 액티비티!!!


    private SessionCallback sessionCallback = new SessionCallback();
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakaologintest);

        Button btn_general_login = findViewById(R.id.btn_general_login);
        Button btn_general_logout = findViewById(R.id.btn_general_logout);
        Button btn_custom_login = findViewById(R.id.btn_custom_login);
        Button btn_custom_login_out = findViewById(R.id.btn_custom_login_out);
        Button btn_join = findViewById(R.id.btn_join);

        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);
        btn_general_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        btn_general_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        btn_custom_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.open(AuthType.KAKAO_LOGIN_ALL, KakaoLoginActivity.this);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btn_custom_login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.getInstance()
                        .requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() {
                                Toast.makeText(KakaoLoginActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), JoinActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
*/