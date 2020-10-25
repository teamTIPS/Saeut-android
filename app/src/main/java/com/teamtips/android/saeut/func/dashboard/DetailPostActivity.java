package com.teamtips.android.saeut.func.dashboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Apply;
import com.teamtips.android.saeut.data.Post;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkService;

import java.io.IOException;

public class DetailPostActivity extends AppCompatActivity {

    private TextView tv_tag1;
    private TextView tv_tag2;
    private TextView tv_tag3;

    private TextView tv_type;
    private TextView tv_status;

    private TextView tv_title;
    private TextView tv_id;
    private TextView tv_writer_address;

    private EditText et_startDate;
    private EditText et_dueDate;
    private TextView tv_contents;

    private EditText et_wage;
    private EditText et_payment;
    private EditText et_postSchedule;

    private TextView tv_applyCount;
    private Button btn_detail_submit;

    private PostNetworkService postNetworkService;

    private Toolbar toolbar;

    private static Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        postNetworkService = new PostNetworkService();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Detail Post findViewById
        AllFindViewDetail();

        // 서버에서 전달받은 Post 객체 저장
        post = (Post) getIntent().getSerializableExtra("post");
        try {
            AllStoreData(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // OnClickListener
        btn_detail_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 돌봄 신청 페이지 뜨게끔 수정하기.
                applyDialogShow();
            }
        });
    }

    // 돌보미 신청 페이지로 연결되는 다이얼로그 함수 (수정 필요)
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

                Apply apply = new Apply(post.getPost_id(), "test", introduce);

                postNetworkService.addApply(apply);
                Toast.makeText(getApplicationContext(), "돌봄 신청이 완료되었습니다 !",Toast.LENGTH_LONG).show();
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

        et_startDate = (EditText) findViewById(R.id.et_startDate);
        et_dueDate = (EditText) findViewById(R.id.et_dueDate);
        tv_applyCount = (TextView) findViewById(R.id.tv_applyCount);
        tv_writer_address = (TextView) findViewById(R.id.tv_writer_address);

        et_wage = (EditText) findViewById(R.id.et_wage);
        et_payment = (EditText) findViewById(R.id.et_payment);
        et_postSchedule = (EditText) findViewById(R.id.et_postSchedule);

        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_status = (TextView) findViewById(R.id.tv_status);

        tv_tag1 = (TextView) findViewById(R.id.tv_tagTitle);
        tv_tag2 = (TextView) findViewById(R.id.tv_tag2);
        tv_tag3 = (TextView) findViewById(R.id.tv_tag3);

        btn_detail_submit = (Button) findViewById(R.id.btn_detail_submit);
    }

    private void AllStoreData(Post post) throws IOException {

        // 추가로 태그 연결 메소드 필요
        tv_type.setText(post.getTypeForText(post.getType()));
        tv_status.setText(post.getStatusForText(post.getStatus()));

        tv_title.setText(post.getTitle());
        tv_contents.setText(post.getContents());
        tv_id.setText(post.getId());

        // 추후 Additional 테이블과 연결해야 함.
        tv_writer_address.setText("서울시 서대문구 북가좌동");
        et_startDate.setText(post.getStart_date());
        et_dueDate.setText(post.getDue_date());

        et_postSchedule.setText(post.getPost_schedule());
        et_payment.setText(String.valueOf(post.getPayment()));
        et_wage.setText(String.valueOf(post.getWage()));

        // callback 함수 구현 필요 (api 연결)
        tv_applyCount.setText("1");
    }
}

// applyCount api 연결 구현 주
// 부득이하게 결과값 받기 위해 얘만 Callback 함수 여기서 선언
//        postNetworkService.mCallApplyCount.enqueue(new Callback<Integer>() {
//            @Override
//            public void onResponse(Call<Integer> call, Response<Integer> response) {
//                if(response.isSuccessful()) {
//                    if(response.body() != 0) {
//                        tv_applyCount.setText(String.valueOf(response.body()));
//                    } else {
//                        tv_applyCount.setText("0");
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "돌봄 신청에 실패하였습니다 !",Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Integer> call, Throwable t) {
//
//            }
//        });