package com.teamtips.android.saeut.func.dashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.dashboard.model.Post;

public class DetailPostActivity extends AppCompatActivity {

    private TextView tv_title;
    private int type;
    private TextView tv_contents;
    private Button btn_detail_submit;
    private Button btn_detail_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_contents = (TextView) findViewById(R.id.tv_contents);

        btn_detail_submit = (Button) findViewById(R.id.btn_detail_submit);
        btn_detail_cancel = (Button)findViewById(R.id.btn_detail_cancel);

        // 서버에서 전달받은 Post 객체 저장
        Post post = (Post) getIntent().getSerializableExtra("post");
        tv_title.setText(post.getTitle());
        tv_contents.setText(post.getContents());

        // OnClickListener
        btn_detail_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 돌봄 신청 페이지 뜨게끔 수정하기.
            }
        });

        btn_detail_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
