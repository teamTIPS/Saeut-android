package com.teamtips.android.saeut.func.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.dashboard.model.Post;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreatePostActivity extends AppCompatActivity {

    private static Button btn_add_submit;
    private static Button btn_add_cancel;
    private static Button btn_startDate;   // choose start date
    private static Button btn_dueDate;     // choose due date

    private static RadioButton rb_old;
    private static RadioButton rb_children;
    private static RadioButton rb_disable;

    private static EditText et_title; //게시물 제목
    private static EditText et_contents; // 게시글 내용
    private static EditText et_startDate; // 돌봄 요청 시작 날짜 -> 모바일 캘린더
    private static EditText et_dueDate; // 돌봄 요청 마지막 날짜  -> 모바일 캘린더

    private RadioGroup rg_tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_create);

        // findViewById
        AllFindViewCreate();

        // 현재 날짜 구하기
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);


        // CalendarView 구현
        btn_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreatePostActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String chooseDate = year + "-" + month + "-" + dayOfMonth;
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
                        String chooseDate = year + "-" + month + "-" + dayOfMonth;
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
                // 데이터 전송
                Post post = new Post(
                        et_title.getText().toString(),
                        et_contents.getText().toString()
                );

//                boolean result = sendPostNetworkService(post);
//                if(result) {
//                    Toast.makeText(getApplicationContext(), "돌봄 게시글이 정상적으로 등록되었습니다.", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), post.toString(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "게시글 등록 실패 !!", Toast.LENGTH_SHORT).show();
//                }
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

    private void AllFindViewCreate() {
        btn_add_submit = findViewById(R.id.btn_add_submit);
        btn_add_cancel = findViewById(R.id.btn_add_cancel);

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
}