package com.teamtips.android.saeut.func.login.join.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.teamtips.android.saeut.R;

public class JoinFragment extends Fragment {

    private JoinViewModel mViewModel;
    private final static String Tag = "JoinFragment";

    public static JoinFragment newInstance() {
        return new JoinFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_join, container, false);

        EditText idet = root.findViewById(R.id.idet);
        EditText passwordet = root.findViewById(R.id.passwordet);
        EditText nameet = root.findViewById(R.id.nameet);
        EditText emailet = root.findViewById(R.id.emailet);
        EditText phoneet = root.findViewById(R.id.phoneet);
        Button nextBt = root.findViewById(R.id.nextBt);
        Button duplicatedIdBt = root.findViewById(R.id.duplicatedIdBt);

        TextWatcher idchangedListner = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!isIdValid(idet.getText().toString())){
                    //중복확인 검사 결과
                    //ui쪽에서 배경색을 빨간색으로 해줬으면 좋겠음
                }
            }
        };
        idet.addTextChangedListener(idchangedListner);

        TextWatcher pwchangedListner = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!isPasswordValid(passwordet.getText().toString())){
                    //비밀번호 길이 검사 결과
                    //ui쪽에서 배경색을 빨간색으로 해줬으면 좋겠음
                }
            }
        };
        passwordet.addTextChangedListener(pwchangedListner);

        TextWatcher emailchangedListner = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!isEmailValid(emailet.getText().toString())){
                    //이메일 형식 검사 결과
                    //ui쪽에서 배경색을 빨간색으로 해줬으면 좋겠음
                }
            }
        };
        emailet.addTextChangedListener(emailchangedListner);

        duplicatedIdBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //서버와 통신하여 중복된 ID인지 확인
            }
        });

        //입력완료 알림버튼
        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Joinin joinin = new Joinin(
                        idet.getText().toString(),
                        passwordet.getText().toString(),
                        nameet.getText().toString(),
                        emailet.getText().toString(),
                        phoneet.getText().toString());
                mViewModel.Join(joinin);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(JoinViewModel.class);
    }

    private boolean isIdValid(String id) {
        //id는 6자 이상으로
        return id != null && id.trim().length() > 5;
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}