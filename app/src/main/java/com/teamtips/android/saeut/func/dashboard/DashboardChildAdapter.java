package com.teamtips.android.saeut.func.dashboard;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Post;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkService;

import java.io.Serializable;
import java.util.ArrayList;

// 삭제가 되긴 하는데 리스트 업데이트 문제가 있어서 CursorAdapter로 변경 예정
public class DashboardChildAdapter extends BaseAdapter {

    public static final String TAG = "DashboardChildAdapter";

    private Context context;                    // inflater 객체 생성 시 필요함
    private int layout;                         // AdapterView 항목에 대한 layout
    private ArrayList<Post> postArrayList;      // 원본 데이터 리스트
    private LayoutInflater layoutInflater;      // inflater 객체
    private static PostNetworkService postNetworkService = new PostNetworkService();
    private int tab_position;

    public DashboardChildAdapter() { }

    public DashboardChildAdapter(Context context, int layout, ArrayList<Post> postArrayList, int tab_position) {
        this.context = context;
        this.layout = layout;
        this.postArrayList = postArrayList;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.tab_position = tab_position;
    }

    @Override
    public int getCount() {
        return postArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return postArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return postArrayList.get(position).getPost_id();
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        // 리스트뷰의 position 위치를 저장, onClick에 사용하기 위해 상수로 선언

        final int position = pos;
        ViewHolder holder;

        Log.d(TAG, "dashboardChildAdapter 지롱");

        if(view == null) {
            view = layoutInflater.inflate(layout, viewGroup, false);
            holder = AllFindViewAdapter(view, tab_position);
            view.setTag(holder);    // ViewHolder 삽입
        } else {
            holder = (ViewHolder) view.getTag();
        }

        setDataInHolder(holder, position); // holder에 data 넣기

        // view 클릭 시 이벤트 처리
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 상세 페이지 뜨도록 정의해야 함.
                // 이 때 로그인 여부 체크
                // 기타 등등 ...

                Log.e(TAG, "onItemClick");
                Post post = postArrayList.get(pos);
                Log.e(TAG, post.toString());
                Intent intent = new Intent(context, DetailPostActivity.class);
                intent.putExtra("post", (Serializable) post);
                context.startActivity(intent);
            }
        });

        // Private Layout OnClickListener
        holder.private_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditPostActivity.class);
                intent.putExtra("post", postArrayList.get(position));
                context.startActivity(intent);
            }
        });

        holder.private_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postNetworkService.deletePost(postArrayList.get(position).getPost_id(), context);
                notifyDataSetChanged();
            }
        });

        // 나머지는 추후 다시 정의해야 함.
        return view;
    }

    // 새로운 게시글을 추가하는 메서드
    public void addItem(int post_id, String id, String title, String post_date, String contents, String start_date, String due_date, int type){
        Post post = new Post(post_id, id, title, post_date, contents, start_date, due_date, type);
        postArrayList.add(post);
    }

    static class ViewHolder {
        TextView tagTitle;
        TextView title;
        TextView date;
        TextView applyCount;
        Button status;
        ImageView color;

        //Private Layout
        LinearLayout private_layout;
        Button private_update;
        Button private_delete;
    }

    private ViewHolder AllFindViewAdapter(View view, int tab_position) {
        ViewHolder holder = new ViewHolder();

        holder.tagTitle = (TextView) view.findViewById(R.id.tv_tagTitle);
        holder.title = (TextView) view.findViewById(R.id.tv_title);
        holder.date = (TextView) view.findViewById(R.id.tv_date);
        holder.color = (ImageView) view.findViewById(R.id.iv_color);
        holder.applyCount = (TextView) view.findViewById(R.id.tv_apply);
        holder.status = (Button) view.findViewById(R.id.btn_status);

        // Private Layout
        holder.private_layout = (LinearLayout) view.findViewById(R.id.private_layout);
        holder.private_update = (Button) view.findViewById(R.id.btn_private_update);
        holder.private_delete = (Button) view.findViewById(R.id.btn_private_delete);

        return holder;
    }

    private void setDataInHolder(ViewHolder holder, int position) {
        holder.title.setText(
                postArrayList.get(position).getTitle());

        // Date 연결
        String date = postArrayList.get(position).getStart_date()
                + "~" + postArrayList.get(position).getDue_date();
        holder.date.setText(date);

        int status = postArrayList.get(position).getStatus();
        holder.status.setText(postArrayList.get(position).getStatusForText(status));

        int type = postArrayList.get(position).getType();

        holder.applyCount.setText("1");

        if(type == 0) {
            holder.tagTitle.setText("장애인");
            holder.tagTitle.setBackground(context.getResources().getDrawable(R.drawable.tag_handicap));
        } else if(type == 1) {
            holder.tagTitle.setText("아동");
            holder.tagTitle.setBackground(context.getResources().getDrawable(R.drawable.tag_kid));
        } else {
            holder.tagTitle.setText("노인");
            holder.tagTitle.setBackground(context.getResources().getDrawable(R.drawable.tag_elder));
        }

        if(tab_position == 1) {
            holder.private_layout.setVisibility(View.VISIBLE);
        } else {
            holder.private_layout.setVisibility(View.GONE);
        }
    }
}
