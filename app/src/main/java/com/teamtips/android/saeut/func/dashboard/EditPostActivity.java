package com.teamtips.android.saeut.func.dashboard;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.dashboard.model.Post;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;

public class EditPostActivity extends AppCompatActivity {

    private static RadioButton rb_old;
    private static RadioButton rb_children;
    private static RadioButton rb_disable;

    private static EditText et_title;
    private static EditText et_contents;
    private static EditText et_startDate;
    private static EditText et_dueDate;

    private static Button btn_edit_submit;
    private static Button btn_edit_cancel;
    private static Button btn_edit_startDate;
    private static Button btn_edit_dueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_edit);

        // Detail Post findViewById
        AllFindViewDetail();

        // 서버에서 전달받은 Post 객체 저장
        Post post = (Post) getIntent().getSerializableExtra("post");
        AllStoreData(post);

        // OnClickListener
        btn_edit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 돌봄 신청 페이지 뜨게끔 수정하기.
                Post post = new Post(
                        et_title.getText().toString(),
                        et_contents.getText().toString(),
                        "test",
                        getCheckedType(),
                        et_startDate.getText().toString(),
                        et_dueDate.getText().toString()
                );

                Toast.makeText(getApplicationContext(), post.toString(), Toast.LENGTH_SHORT).show();
//                boolean result = sendPostNetworkService(post);
//                if(result) {
//                    Toast.makeText(getApplicationContext(), "돌봄 게시글이 정상적으로 등록되었습니다.", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), post.toString(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "게시글 등록 실패 !!", Toast.LENGTH_SHORT).show();
//                }
                finish();
            }
        });

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);

        btn_edit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // CalendarView 구현
        btn_edit_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditPostActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        btn_edit_dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditPostActivity.this, new DatePickerDialog.OnDateSetListener() {
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
    }

    private void AllFindViewDetail() {
        et_title = findViewById(R.id.et_title);
        et_contents = findViewById(R.id.et_contents);

        rb_children = (RadioButton) findViewById(R.id.rb_children);
        rb_disable = (RadioButton) findViewById(R.id.rb_disable);
        rb_old = (RadioButton) findViewById(R.id.rb_old);

        et_startDate = findViewById(R.id.et_startDate);
        et_dueDate = findViewById(R.id.et_dueDate);

        btn_edit_submit = (Button) findViewById(R.id.btn_edit_submit);
        btn_edit_cancel = (Button)findViewById(R.id.btn_edit_cancel);
        btn_edit_startDate = (Button) findViewById(R.id.btn_editStartDate);
        btn_edit_dueDate = (Button)findViewById(R.id.btn_editDueDate);
    }

    private void AllStoreData(Post post) {
        et_title.setText(post.getTitle());
        et_contents.setText(post.getContents());

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        et_startDate.setText(dateFormat.format(post.getStart_date()));
        et_dueDate.setText(dateFormat.format(post.getDue_date()));

        // tag radio button checking
        int type = post.getType();
        if(type == 0) {
            rb_disable.setChecked(true);
        } else if(type == 1) {
            rb_children.setChecked(true);
        } else {
            rb_old.setChecked(true);
        }
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
