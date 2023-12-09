package com.teamtips.android.saeut.func.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.teamtips.android.saeut.MainActivity;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;

public class ProfileFragment extends Fragment {

    private String Tag = "ProfileFragment";

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView profile_tv_id = root.findViewById(R.id.profile_tv_id);
        TextView profile_tv_address = root.findViewById(R.id.profile_tv_address);

        profile_tv_id.setText(LoggedInUser.getLoggedInUser().getNickname());
        profile_tv_address.setText(LoggedInUser.getLoggedInUser().getAddress1());

        Button manage_account_btn = root.findViewById(R.id.manage_account_btn);
        Button revise_legion_btn = root.findViewById(R.id.revise_legion_btn);
        Button manage_post_btn = root.findViewById(R.id.manage_post_btn);
        Button manage_neighbors_btn = root.findViewById(R.id.manage_neighbors_btn);

        manage_account_btn.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), modify_account_info.class));
        });

        revise_legion_btn.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), ReviseLocationActivity.class));
        });

        manage_post_btn.setOnClickListener(view -> {
            ((MainActivity)getActivity()).initTitle("내가 쓴 게시물");
            ((MainActivity)getActivity()).replaceFragment(MyPostFragment.newInstance());
        });

        manage_neighbors_btn.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), RatingActivity.class));
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView profile_tv_id = getView().findViewById(R.id.profile_tv_id);
        TextView profile_tv_address = getView().findViewById(R.id.profile_tv_address);

        profile_tv_id.setText(LoggedInUser.getLoggedInUser().getNickname());
        profile_tv_address.setText(LoggedInUser.getLoggedInUser().getAddress1());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    }
}
