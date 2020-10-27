package com.teamtips.android.saeut.func.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Post;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkTask;
import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;
import java.util.ArrayList;

public class DashboardMatchingFragment extends Fragment {

    private static final String ARGUMENT_POSITION = "argument_position";
    public static final String TAG = "DashboardMatchingFragment";

    private EditText et_hourlyWage;
    private EditText et_schedule;
    private EditText et_location;

    private CheckBox cb_typeAlone;
    private CheckBox cb_typeGroup;

    private Button btn_matching_submit;

    private int position;        // taglayout 구별하기 위한 일종의 flag라고 이해함

    // 세션에 저장된 유저 객체 저장하는 변수 -> 구현 덜 됨.
    private static LoggedInUser sessionUser;

    public DashboardMatchingFragment(int position) {
        this.position = position;
    }

    public static DashboardMatchingFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_POSITION, position);
        DashboardMatchingFragment fragment = new DashboardMatchingFragment(position);
        fragment.setArguments(args);

        sessionUser = LoggedInUser.getLoggedInUser();   // 세션에 저장된 유저 정보 가져오기
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_hourlyWage = (EditText) view.findViewById(R.id.et_hourlyWage);
        et_schedule = (EditText) view.findViewById(R.id.et_schedule);
        et_location = (EditText) view.findViewById(R.id.et_location);

        cb_typeAlone = (CheckBox) view.findViewById(R.id.cb_typeAlone);
        cb_typeGroup = (CheckBox) view.findViewById(R.id.cb_typeGroup);

        btn_matching_submit = (Button) view.findViewById(R.id.btn_matching_submit);

        btn_matching_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder check_matching_ad = new AlertDialog.Builder(getContext());

                check_matching_ad.setMessage("매칭을 하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Dialog", "확인");
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Dialog", "취소");
                            }
                        })
                        .setCancelable(true) // 백버튼으로 팝업창이 닫히지 않도록 한다.
                        .show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard_matching, container, false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
