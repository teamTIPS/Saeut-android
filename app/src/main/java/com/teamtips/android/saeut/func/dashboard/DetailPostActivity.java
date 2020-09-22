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
import com.teamtips.android.saeut.func.dashboard.model.Apply;
import com.teamtips.android.saeut.func.dashboard.model.Post;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkService;

import java.io.IOException;
import java.text.DateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPostActivity extends AppCompatActivity {

    private static TextView tv_title;
    private static TextView tv_id;

    private static TextView tv_tagTitle;
    private static TextView tv_startDate;
    private static TextView tv_dueDate;
    private static TextView tv_applyCount;
    private static TextView tv_address;
    private static TextView tv_contents;
    private static Button btn_detail_submit;

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
        tv_tagTitle = (TextView)findViewById(R.id.tv_tagTitle);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_contents = (TextView) findViewById(R.id.tv_contents);
        tv_id = (TextView) findViewById(R.id.tv_id);

        tv_startDate = (TextView) findViewById(R.id.tv_startDate);
        tv_dueDate = (TextView) findViewById(R.id.tv_dueDate);
        tv_applyCount = (TextView) findViewById(R.id.tv_applyCount);
        tv_address = (TextView) findViewById(R.id.tv_address);

        btn_detail_submit = (Button) findViewById(R.id.btn_detail_submit);
    }

    private void AllStoreData(Post post) throws IOException {
        tv_title.setText(post.getTitle());
        tv_contents.setText(post.getContents());
        tv_id.setText(post.getId());

        tv_startDate.setText(post.getStart_date());
        tv_dueDate.setText(post.getDue_date());

        // 부득이하게 결과값 받기 위해 얘만 Callback 함수 여기서 선언
        postNetworkService.mCallApplyCount.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()) {
                    if(response.body() != 0) {
                        tv_applyCount.setText(String.valueOf(response.body()));
                    } else {
                        tv_applyCount.setText("0");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "돌봄 신청에 실패하였습니다 !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

        // 추후 Additional 테이블과 연결해야 함.
        tv_address.setText("서울시 서대문구 북가좌동");

        // tag radio button checking
        int type = post.getType();
        if(type == 0) {
            tv_tagTitle.setText("장애인");
            tv_tagTitle.setBackground(getResources().getDrawable(R.drawable.tag_handicap));
        } else if(type == 1) {
            tv_tagTitle.setText("아동");
            tv_tagTitle.setBackground(getResources().getDrawable(R.drawable.tag_kid));
        } else {
            tv_tagTitle.setText("노인");
            tv_tagTitle.setBackground(getResources().getDrawable(R.drawable.tag_elder));
        }
    }
}
