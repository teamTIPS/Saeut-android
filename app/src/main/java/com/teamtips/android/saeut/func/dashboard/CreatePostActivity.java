package com.teamtips.android.saeut.func.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.dashboard.model.Post;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkService;

import java.sql.Date;
import java.util.Calendar;

public class CreatePostActivity extends AppCompatActivity {

    private static final String TAG = "CreatePostActivity";

    private static Button btn_add_submit;
    private static Button btn_startDate;   // choose start date
    private static Button btn_dueDate;     // choose due date

    private static RadioButton rb_old;
    private static RadioButton rb_children;
    private static RadioButton rb_disable;

    private static EditText et_title; //게시물 제목
    private static EditText et_contents; // 게시글 내용
    private static EditText et_startDate; // 돌봄 요청 시작 날짜 -> 모바일 캘린더
    private static EditText et_dueDate; // 돌봄 요청 마지막 날짜  -> 모바일 캘린더

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_create);

        // findViewById
        AllFindViewCreate();

        // 현재 날짜 구하기
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);

        // CalendarView 구현
        btn_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreatePostActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String chooseDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                        et_startDate.setText(chooseDate);
                    }
                },year, month, date);

                datePickerDialog.setMessage("메시지");
                datePickerDialog.show();
            }
        });

        btn_dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreatePostActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String chooseDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                        et_dueDate.setText(chooseDate);
                    }
                },year, month, date);

                datePickerDialog.setMessage("메시지");
                datePickerDialog.show();
            }
        });

        // 작성 완료 시 서버로 데이터 전송 & REST API 연결
        btn_add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"btn_add_submit onClick");
                // 데이터 전송
                Post post = new Post(
                        et_title.getText().toString(),
                        et_contents.getText().toString(),
                        "test",
                        getCheckedType(),
                        et_startDate.getText().toString(),
                        et_dueDate.getText().toString()
                );

                Log.d(TAG, post.toString());
                try {
                    sendPostNetworkService(post);
                    Toast.makeText(getApplicationContext(), "돌봄 게시글이 등록되었습니다 !!", Toast.LENGTH_SHORT).show();
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "게시글 등록 실패 !! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
    };

    private void sendPostNetworkService(Post post) {
        PostNetworkService postNetworkService = new PostNetworkService();
        postNetworkService.addPost(post);
    }

    private void AllFindViewCreate() {
        btn_add_submit = findViewById(R.id.btn_add_submit);

        et_title = findViewById(R.id.et_title);
        et_contents = findViewById(R.id.et_contents);
        et_dueDate = findViewById(R.id.et_dueDate);
        et_startDate = findViewById(R.id.et_startDate);

        rb_children = findViewById(R.id.rb_children);
        rb_disable = findViewById(R.id.rb_disable);
        rb_old = findViewById(R.id.rb_old);

        btn_dueDate = findViewById(R.id.btn_dueDate);
        btn_startDate = findViewById(R.id.btn_startDate);
    }

    private int getCheckedType() {
        // 근데 이거 만약에 사용자가 값을 선택하지 않았을땐 어카지? 흐음,,,
        // 일단 2로 들어가기야 하겠지만 별도의 처리가 필요할듯.
        if(rb_disable.isChecked()) {
            return 0;
        } else if(rb_children.isChecked()) {
            return 1;
        } else {
            return 2;
        }
    }


}