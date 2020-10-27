package com.teamtips.android.saeut.func.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Board;

import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String Tag = "CommunityAdapter";
    private List<Board> communities;
    private Context mContext;
    private CommunityNetworkService api;

    public CommunityAdapter(List<Board> communities, Context mContext, CommunityNetworkService api) {
        this.communities = communities;
        this.mContext = mContext;
        this.api = api;
    }

    public void updateArrayList(List<Board> boardList){
        this.communities = boardList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.community_recycler_item, parent, false);
        return new CommunityItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Board bindedBoard = communities.get(position);

        ((CommunityItem) holder).writer_nickname_tv.setText(bindedBoard.getNickname());
        ((CommunityItem) holder).community_location_tv.setText(bindedBoard.getAddress1());
        ((CommunityItem) holder).community_rank_tv.setText(bindedBoard.getRankString());
        ((CommunityItem) holder).community_text_tv.setText(bindedBoard.getContents());
        ((CommunityItem) holder).community_like_tv.setText(String.valueOf(bindedBoard.getCnt_like()));
        ((CommunityItem) holder).community_message_tv.setText(String.valueOf(bindedBoard.getCnt_reply()));
        ((CommunityItem) holder).community_post_date.setText(bindedBoard.getDateByString());
    }

    @Override
    public int getItemCount() {
        if(communities == null) return 0;
        return communities.size();
    }

    public class CommunityItem extends RecyclerView.ViewHolder {

        private TextView writer_nickname_tv;
        private TextView community_location_tv;
        private TextView community_rank_tv;
        private TextView community_text_tv;
        private TextView community_like_tv;
        private TextView community_message_tv;
        private TextView community_post_date;

        public CommunityItem(@NonNull View itemView) {
            super(itemView);
            writer_nickname_tv = itemView.findViewById(R.id.writer_nickname_tv);
            community_location_tv = itemView.findViewById(R.id.community_location_tv);
            community_rank_tv = itemView.findViewById(R.id.community_rank_tv);
            community_text_tv = itemView.findViewById(R.id.community_text_tv);
            community_like_tv = itemView.findViewById(R.id.community_like_tv);
            community_message_tv = itemView.findViewById(R.id.community_message_tv);
            community_post_date = itemView.findViewById(R.id.community_post_date);
        }
    }
}
