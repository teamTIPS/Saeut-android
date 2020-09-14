package com.teamtips.android.saeut.func.dashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.dashboard.model.Post;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DashboardChildAdapter extends BaseAdapter {

    private Context context;                    // inflater 객체 생성 시 필요함
    private int layout;                         // AdapterView 항목에 대한 layout
    private ArrayList<Post> postArrayList;      // 원본 데이터 리스트
    private LayoutInflater layoutInflater;      // inflater 객체

    public DashboardChildAdapter() { }

    public DashboardChildAdapter(Context context, int layout, ArrayList<Post> postArrayList) {
        this.context = context;
        this.layout = layout;
        this.postArrayList = postArrayList;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        Log.d("DashboardChildAdapter", "dashboardChildAdapter 지롱");

        if(view == null) {
            view = layoutInflater.inflate(layout, viewGroup, false);

            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.tv_title);
            holder.date = (TextView) view.findViewById(R.id.tv_date);
            holder.color = (ImageView) view.findViewById(R.id.iv_color);
            holder.applyCount = (TextView) view.findViewById(R.id.tv_apply);
            holder.status = (Button) view.findViewById(R.id.btn_status);

            view.setTag(holder);    // ViewHolder 삽입
        } else {
            holder = (ViewHolder) view.getTag();
        }

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        holder.title.setText(postArrayList.get(position).getTitle());

        // Date 연결
         String date = dateFormat.format(postArrayList.get(position).getStart_date())
                + "~" + dateFormat.format(postArrayList.get(position).getDue_date());
        holder.date.setText(date);

        int status = postArrayList.get(position).getStatus();
        holder.status.setText(postArrayList.get(position).getStatusForText(status));

        // 나머지는 추후 다시 정의해야 함.
        return view;
    }

    // 새로운 게시글을 추가하는 메서드
    public void addItem(int post_id, String id, String title, Date start_date, Date due_date){
        Post post = new Post(post_id, id, title, start_date, due_date);
        postArrayList.add(post);
    }

    static class ViewHolder {
        TextView title;
        TextView date;
        TextView applyCount;
        Button status;
        ImageView color;
    }
}
