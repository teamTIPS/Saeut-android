package com.teamtips.android.saeut.func.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.teamtips.android.saeut.R;

public class RatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Button review_finish_btn = findViewById(R.id.review_finish_btn);

        AlertDialog.Builder emailCheckDialog = new AlertDialog.Builder(this);

        emailCheckDialog.setMessage("리뷰 작성을 완료하셨나요?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Dialog", "확인");
                        finish();
                    }
                })
                .setNegativeButton("다시 작성하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("Dialog", "취소함");
                    }
                })
                .setCancelable(true); // 백버튼으로 팝업창이 닫히지 않도록 한다.
        review_finish_btn.setOnClickListener(view -> {
            emailCheckDialog.show();
        });
    }
}