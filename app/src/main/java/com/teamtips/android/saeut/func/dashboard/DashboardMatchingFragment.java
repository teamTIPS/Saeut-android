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
import com.teamtips.android.saeut.data.Matching;
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

    private DashboardListAdapter dashboardListAdapter;
    private ListView listView;
    private ArrayList<Post> postArrayList;
    private PostNetworkTask postNetworkTask;
    private static String url;
    // 세션에 저장된 유저 객체 저장하는 변수 -> 구현 덜 됨.
    private static LoggedInUser sessionUser;

    public static DashboardMatchingFragment newInstance() {
        return new DashboardMatchingFragment();
    }

    public DashboardMatchingFragment() { }

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

        setAdapter(view);
        btn_matching_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = cb_typeAlone.isChecked()? 0 : 1; // type 값 저장
                AlertDialog.Builder check_matching_ad = new AlertDialog.Builder(getContext());

                check_matching_ad.setMessage("매칭을 하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //String post_schedule, int wage, String location, int type

                                Matching matching = new Matching(
                                        et_schedule.getText().toString(),
                                        Integer.parseInt(et_hourlyWage.getText().toString()),
                                        et_location.getText().toString(),
                                        type
                                );
                                Log.d("Dialog", "확인");
                                Log.d("Dialog", matching.toString());

                                // 리스트 새로고침
                                setListChanged(view, type);
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
        et_hourlyWage = (EditText) root.findViewById(R.id.et_hourlyWage);
        et_schedule = (EditText) root.findViewById(R.id.et_schedule);
        et_location = (EditText) root.findViewById(R.id.et_location);

        cb_typeAlone = (CheckBox) root.findViewById(R.id.cb_typeAlone);
        cb_typeGroup = (CheckBox) root.findViewById(R.id.cb_typeGroup);

        btn_matching_submit = (Button) root.findViewById(R.id.btn_matching_submit);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        dashboardListAdapter.notifyDataSetChanged();
    }

    private void setAdapter(View view) {
        postArrayList = new ArrayList<Post>();
        dashboardListAdapter = new DashboardListAdapter(view.getContext(), R.layout.adapter_dashboard, postArrayList, position);
        listView = view.findViewById(R.id.lv_matchingList);
        listView.setAdapter(dashboardListAdapter);
    }

    private void setListChanged(View view, int type) {
        setAdapter(view);
        url = "http://49.50.173.180:8080/saeut/post/type/" + type;
        postNetworkTask = new PostNetworkTask(url, null, dashboardListAdapter);
        postNetworkTask.execute();
    }
}
