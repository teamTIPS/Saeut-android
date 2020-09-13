package com.teamtips.android.saeut.func.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.dashboard.model.Post;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkTask;

public class CreatePostActivity extends AppCompatActivity {

    private Button btn_add_submit;
    private Button btn_add_cancel;

    private EditText et_account_id; // (FK) account 클래스의 id
    private EditText et_title; //게시물 제목
    private EditText et_post_date; //게시글 추가한 날짜
    private EditText et_contents; // 게시글 내용
    private EditText et_start_date; // 돌봄 요청 시작 날짜 -> 모바일 캘린더
    private EditText et_due_date; // 돌봄 요청 마지막 날짜  -> 모바일 캘린더
    private EditText et_birth; // 돌봄이 필요한 대상의 생년월일 -> 모바일 캘린더
    private EditText et_address1; // 돌봄이 필요한 대상의 주소1
    private EditText et_address2; // 돌봄이 필요한 대상의 주소2
    private EditText et_status; // 현재 돌봄 진행상태

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_create);

        btn_add_submit = findViewById(R.id.btn_add_submit);
        btn_add_cancel = findViewById(R.id.btn_add_cancel);

        et_title = findViewById(R.id.et_title);
        et_contents = findViewById(R.id.et_contents);

        btn_add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터 전송
                Post post = new Post(
                        et_title.getText().toString(),
                        et_contents.getText().toString()
                );
                boolean result = sendPostNetworkService(post);

                if(result) {
                    Toast.makeText(getApplicationContext(), "돌봄 게시글이 정상적으로 등록되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "게시글 등록 실패 !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_add_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    };

    private boolean sendPostNetworkService(Post post) {
        String url = "http://49.50.173.180:8080/saeut/post/add";
        // 여길 더 구현해야 할듯.
        return false;
    }
}