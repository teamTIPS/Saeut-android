package com.teamtips.android.saeut.func.group;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamtips.android.saeut.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link none_comment_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class none_comment_fragment extends Fragment {

    public none_comment_fragment() {
        // Required empty public constructor
    }

    public static none_comment_fragment newInstance() {
        return new none_comment_fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_none_comment_fragment, container, false);
    }
}