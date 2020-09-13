package com.teamtips.android.saeut.func.login.join.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.teamtips.android.saeut.R;
/*
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
}
*/