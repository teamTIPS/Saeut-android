package com.teamtips.android.saeut.func.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Post;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkService;

import java.util.Calendar;

public class CreatePostActivity extends AppCompatActivity {

    private static final String TAG = "CreatePostActivity";

    private EditText et_title;          //게시물 제목
    private EditText et_contents;       // 게시글 내용
    private EditText et_startDate;      // 돌봄 요청 시작 날짜 -> 모바일 캘린더
    private EditText et_dueDate;        // 돌봄 요청 마지막 날짜  -> 모바일 캘린더
    private EditText et_tag;            // 게시물 태그 (일단 3개까지만 입력)
    private EditText et_postSchedule;   // 원하는 시간대
    private EditText et_hourlyWage;     // 시급

    private RadioGroup rg_age;          // 선호하는 연령대
    private RadioGroup rg_gender;       // 선호하는 성별
    private RadioGroup rg_type;

    private Button btn_add_submit;
    private Button btn_startDate;   // choose start date
    private Button btn_dueDate;     // choose due date

    // 함께돌봄일때
    private TextView tv_group;
    private LinearLayout group_layout;
    private Spinner spinner_demand;
    private Spinner spinner_supply;

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
                // public Post(String id, String title, String post_age, String post_schedule,
                //                String post_gender, String contents, String start_date, String due_date, int status, int type,
                //                int wage, int limit_supply, int limit_demend)
                RadioButton rb_age = findViewById(rg_age.getCheckedRadioButtonId());
                RadioButton rb_gender = findViewById(rg_gender.getCheckedRadioButtonId());
                Post post = new Post(
                        "test",
                        et_title.getText().toString(),
                        rb_age.getText().toString(),
                        et_postSchedule.getText().toString(),
                        rb_gender.getText().toString(),
                        et_contents.getText().toString(),
                        et_startDate.getText().toString(),
                        et_dueDate.getText().toString(),
                        0,
                        getCheckedType(rg_type.getCheckedRadioButtonId()),
                        Integer.parseInt(et_hourlyWage.getText().toString()),
                        0,
                        0
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

    private int getCheckedType(int checkedId) {
        if(checkedId == R.id.rb_typeAlone) {
            // 맞춤돌봄
            return 0;
        } else {
            // 함께돌봄
            tv_group.setVisibility(View.VISIBLE);
            group_layout.setVisibility(View.VISIBLE);
            return 1;
        }
    }

    private void sendPostNetworkService(Post post) {
        PostNetworkService postNetworkService = new PostNetworkService();
        postNetworkService.addPost(post);
    }

    private void AllFindViewCreate() {
        // 기타 컬럼들
        et_title = findViewById(R.id.et_title);
        et_contents = findViewById(R.id.et_contents);
        et_dueDate = findViewById(R.id.et_dueDate);
        et_startDate = findViewById(R.id.et_startDate);
        et_tag = findViewById(R.id.et_tag);
        et_postSchedule = findViewById(R.id.et_postSchedule);
        et_hourlyWage = findViewById(R.id.et_hourlyWage);

        rg_age = findViewById(R.id.rg_age);
        rg_gender = findViewById(R.id.rg_age);
        rg_type = findViewById(R.id.rg_type);

        // 함께돌봄일 때 동작하는 레이아웃
        tv_group = findViewById(R.id.number);
        group_layout = findViewById(R.id.group_layout);
        spinner_demand = findViewById(R.id.sp_limit_demand);
        spinner_supply = findViewById(R.id.sp_limit_supply);

        // 날짜 설정 다이얼로그 버튼
        btn_dueDate = findViewById(R.id.btn_dueDate);
        btn_startDate = findViewById(R.id.btn_startDate);

        // 최종 추가 버튼
        btn_add_submit = findViewById(R.id.btn_add_submit);
    }
}