package com.teamtips.android.saeut.func.dashboard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.teamtips.android.saeut.R;

public class DetailPostActivity extends AppCompatActivity {

    private TextView tv_title;
    private int type;
    private TextView tv_contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
    }
}
