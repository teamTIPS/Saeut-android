package com.teamtips.android.saeut.func.group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Board;
import com.teamtips.android.saeut.data.Comment;
import com.teamtips.android.saeut.data.Group;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String Tag = "GroupAdapter";
    private List<Comment> comments;
    private Context mContext;
    private GroupNetworkService api;

    public GroupAdapter(Context mContext, List<Comment> comments, GroupNetworkService api){
        this.mContext = mContext;
        this.comments = comments;
        this.api = api;
    }

    public void updateArrayList(List<Comment> comments){
        this.comments = comments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.fragment_group, parent, false);

        return new othersViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Comment bindedComment = comments.get(position);

        if(holder instanceof myViewholder){
            ((myViewholder) holder).reply_mine_nickname.setText(bindedComment.getId());
            ((myViewholder) holder).reply_mine_content.setText(bindedComment.getContents());
            ((myViewholder) holder).reply_mine_time.setText(bindedComment.getWrite_time().toString());
        }
        else{
            ((othersViewholder) holder).reply_others_nickname.setText(bindedComment.getId());
            ((othersViewholder) holder).reply_others_content.setText(bindedComment.getContents());
            ((othersViewholder) holder).reply_others_time.setText(bindedComment.getWrite_time().toString());
        }
    }

    @Override
    public int getItemCount() {
        if(comments == null) return 0;
        return comments.size();
    }

    public class myViewholder extends RecyclerView.ViewHolder {
        private ConstraintLayout myreply_view;
        private TextView reply_mine_time;
        private TextView reply_mine_nickname;
        private TextView reply_mine_content;

        public myViewholder(View convertView) {
            super(convertView);
            reply_mine_time = convertView.findViewById(R.id.reply_mine_time);
            myreply_view = convertView.findViewById(R.id.reply_mine_container);
            reply_mine_nickname = convertView.findViewById(R.id.reply_mine_nickname);
            reply_mine_content = convertView.findViewById(R.id.reply_mine_content);
        }
    }

    public class othersViewholder extends RecyclerView.ViewHolder{
        private ConstraintLayout othersreply_view;
        private TextView reply_others_time;
        private TextView reply_others_nickname;
        private TextView reply_others_content;

        public othersViewholder(View convertView) {
            super(convertView);
            reply_others_time = convertView.findViewById(R.id.reply_others_time);
            reply_others_nickname = convertView.findViewById(R.id.reply_others_nickname);
            reply_others_content = convertView.findViewById(R.id.reply_others_content);
        }
    }
}
