package com.teamtips.android.saeut.func.login.join;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Paint;
import android.os.Bundle;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.join.ui.main.JoinFragment;

public class JoinActivity extends FragmentActivity {

    JoinFragment joinFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinFragment = new JoinFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container_join, joinFragment);
        transaction.commit();
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