package com.teamtips.android.saeut.func.login.join.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.teamtips.android.saeut.R;

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
        Button email_sign_up_button = root.findViewById(R.id.email_sign_up_button);

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

        //닉네임 중복확인 api 사용, 버튼 만들어주세요
        email_sign_up_button.setOnClickListener(view -> {
            //if(/*초록원&&비밀번호확인 맞음&&폰번호인증완료*/){
                Joinin joinin = new Joinin(
                        email_edit.getText().toString(),
                        password_edit.getText().toString(),
                        name_edit.getText().toString(),
                        phone_edit.getText().toString(),
                        nickname_edit.getText().toString());
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
}
