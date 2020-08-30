package com.teamtips.android.saeut.func.login.join;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.join.ui.main.JoinFragment;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, JoinFragment.newInstance())
                    .commitNow();
        }
    }

//    TextWatcher afterTextChangedListener = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            // ignore
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            // ignore
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
//                    passwordEditText.getText().toString());
//        }
//    }; LoginViewModel 참고해서 기본적인 아이디 검사 및 비밀번호 검사 필요
}