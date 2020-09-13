package com.teamtips.android.saeut.func.login.join.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.join.ui.main.JoinViewModel;

public class fragment_join extends Fragment {

    private JoinViewModel mViewModel;
    private final static String Tag = "JoinFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        //email == account_id
        EditText email_edit = root.findViewById(R.id.email_edit);
        EditText password_edit = root.findViewById(R.id.password_edit);
        EditText password_confirm = root.findViewById(R.id.password_confirm);

        EditText name_edit = root.findViewById(R.id.name_edit);
        EditText phone_edit = root.findViewById(R.id.phone_edit);
        EditText nickname_edit = root.findViewById(R.id.nickname_edit);
        Button email_sign_up_button = root.findViewById(R.id.email_sign_up_button);





        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
