package com.teamtips.android.saeut.func.dashboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.dashboard.model.Post;

import java.text.DateFormat;

public class DetailPostActivity extends AppCompatActivity {

    private static TextView tv_title;
    private static TextView tv_id;

    private static RadioButton rb_old;
    private static RadioButton rb_children;
    private static RadioButton rb_disable;

    private static TextView tv_startDate;
    private static TextView tv_dueDate;
    private static TextView tv_applyCount;
    private static TextView tv_address;
    private static TextView tv_contents;
    private static Button btn_detail_submit;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Detail Post findViewById
        AllFindViewDetail();

        // 서버에서 전달받은 Post 객체 저장
        Post post = (Post) getIntent().getSerializableExtra("post");
        AllStoreData(post);

        // OnClickListener
        btn_detail_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 돌봄 신청 페이지 뜨게끔 수정하기.
                applyDialogShow();
            }
        });
    }

    // 돌보미 신청 페이지로 연결되는 다이얼로그 함수
    public void applyDialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_apply, null);
        builder.setView(view);

        final EditText et_introduce = (EditText) view.findViewById(R.id.et_introduce);
        final Button btn_apply_submit = (Button) view.findViewById(R.id.btn_apply_submit);
        final Button btn_apply_cancel = (Button) view.findViewById(R.id.btn_apply_cancel);

        final AlertDialog dialog = builder.create();
        btn_apply_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String introduce = et_introduce.getText().toString();
                Toast.makeText(getApplicationContext(), introduce,Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        btn_apply_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "신청 취소 !!",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void AllFindViewDetail() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_contents = (TextView) findViewById(R.id.tv_contents);
        tv_id = (TextView) findViewById(R.id.tv_id);

        rb_children = (RadioButton) findViewById(R.id.rb_children);
        rb_disable = (RadioButton) findViewById(R.id.rb_disable);
        rb_old = (RadioButton) findViewById(R.id.rb_old);

        tv_startDate = (TextView) findViewById(R.id.tv_startDate);
        tv_dueDate = (TextView) findViewById(R.id.tv_dueDate);
        tv_applyCount = (TextView) findViewById(R.id.tv_applyCount);
        tv_address = (TextView) findViewById(R.id.tv_address);

        btn_detail_submit = (Button) findViewById(R.id.btn_detail_submit);
    }

    private void AllStoreData(Post post) {
        tv_title.setText(post.getTitle());
        tv_contents.setText(post.getContents());
        tv_id.setText(post.getId());

        tv_startDate.setText(post.getStart_date());
        tv_dueDate.setText(post.getDue_date());

        // 추후 Apply 테이블과 연결해야 함.
        tv_applyCount.setText("0");
        // 추후 Additional 테이블과 연결해야 함.
        tv_address.setText("서울시 서대문구 북가좌동");

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
}
