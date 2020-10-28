package com.teamtips.android.saeut.func.dashboard;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Post;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkService;

import java.text.DateFormat;
import java.util.Calendar;

public class EditPostActivity extends AppCompatActivity {

    private static EditText et_title;
    private static EditText et_contents;
    private static EditText et_startDate;
    private static EditText et_dueDate;

    private static Button btn_edit_submit;
    private static Button btn_edit_startDate;
    private static Button btn_edit_dueDate;

    private Toolbar toolbar;

    private PostNetworkService postNetworkService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_edit);

        // Detail Post findViewById
        AllFindViewDetail();

        // 서버에서 전달받은 Post 객체 저장
        Post post = (Post) getIntent().getSerializableExtra("post");
        AllStoreData(post);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // OnClickListener
        btn_edit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 돌봄 신청 페이지 뜨게끔 수정하기.
//                Post post = new Post(
//                        "test",
//                        et_title.getText().toString(),
//                        rb_age.getText().toString(),
//                        et_postSchedule.getText().toString(),
//                        rb_gender.getText().toString(),
//                        et_contents.getText().toString(),
//                        et_startDate.getText().toString(),
//                        et_dueDate.getText().toString(),
//                        0,
//                        getCheckedType(rg_type.getCheckedRadioButtonId()),
//                        Integer.parseInt(et_hourlyWage.getText().toString()),
//                        limit_supply,
//                        limit_demand
//                );

//                postNetworkService = new PostNetworkService();
//                postNetworkService.modPost(modPost);
                Toast.makeText(getApplicationContext(), "돌봄 게시글이 정상적으로 등록되었습니다.", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);

        // CalendarView 구현
        btn_edit_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditPostActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        btn_edit_dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditPostActivity.this, new DatePickerDialog.OnDateSetListener() {
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
    }

    // Toolbar Back Button 활성화
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void AllFindViewDetail() {
        et_title = findViewById(R.id.et_title);
        et_contents = findViewById(R.id.et_contents);

        et_startDate = findViewById(R.id.et_startDate);
        et_dueDate = findViewById(R.id.et_dueDate);

        btn_edit_startDate = (Button) findViewById(R.id.btn_startDate);
        btn_edit_dueDate = (Button)findViewById(R.id.btn_dueDate);

        btn_edit_submit = (Button) findViewById(R.id.btn_edit_submit);

    }

    private void AllStoreData(Post post) {
        et_title.setText(post.getTitle());
        et_contents.setText(post.getContents());
        et_startDate.setText(post.getStart_date());
        et_dueDate.setText(post.getDue_date());
    }
}
