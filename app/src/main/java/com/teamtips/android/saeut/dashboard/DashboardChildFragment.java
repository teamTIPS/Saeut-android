package com.teamtips.android.saeut.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.TimberLogger;
import com.teamtips.android.saeut.dashboard.model.Post;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DashboardChildFragment extends Fragment {

  private static final String ARGUMENT_POSITION = "argument_position";
  private static final String ARGUMENT_NAME = "argument_name";

  private DashboardViewModel dashboardViewModel;
  private DashboardChildAdapter dashboardChildAdapter;
  private ListView listView;
  private ArrayList<Post> postArrayList;
  int position; // taglayout 구별하기 위한 일종의 flag라고 이해함

  public DashboardChildFragment(int position) {
    this.position = position;
  }

  public static DashboardChildFragment newInstance(int position, String name) {

    Bundle args = new Bundle();
    args.putInt(ARGUMENT_POSITION, position);
    args.putString(ARGUMENT_NAME, name);
    DashboardChildFragment fragment = new DashboardChildFragment(position);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    getLifecycle().addObserver(new TimberLogger(this));
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    int position = requireArguments().getInt(ARGUMENT_POSITION, -1);
    if (position == 1) {
      inflater.inflate(R.menu.delete, menu);
    }
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel.class);

    View root = inflater.inflate(R.layout.fragment_dashboard_child, container, false);

    TextView textView = root.findViewById(R.id.tv_child);

    postArrayList = new ArrayList<Post>();

    Calendar cal = Calendar.getInstance();
    Date nowDate = cal.getTime();

    postArrayList.add(new Post(1234, 1234, "title1", nowDate, "서울시 서대문구 북가좌동"));
    postArrayList.add(new Post(1434, 1214, "title2", nowDate, "서울시 서대문구 북가좌동"));
    postArrayList.add(new Post(1334, 1224, "title3", nowDate, "서울시 서대문구 북가좌동"));
    postArrayList.add(new Post(1534, 1254, "title4", nowDate, "서울시 서대문구 북가좌동"));
    postArrayList.add(new Post(1734, 1264, "title5", nowDate, "서울시 서대문구 북가좌동"));

    dashboardChildAdapter = new DashboardChildAdapter(root.getContext(), R.layout.adapter_dashboard, postArrayList);
    listView = root.findViewById(R.id.lv_child);

    listView.setAdapter(dashboardChildAdapter);

    dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s + position);
      }
    });

    // view 클릭 시 이벤트 처리
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 상세 페이지 뜨도록 정의해야 함.
        // 이 때 로그인 여부 체크
        // 기타 등등 ...
      }
    });

    return root;
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
//    TextView tvDashBoard = view.findViewById(R.id.tv_dashboard);
//    int position = requireArguments().getInt(ARGUMENT_POSITION, -1);
//    String name = requireArguments().getString(ARGUMENT_NAME);
//    tvDashBoard.setText(
//        position == 0
//            ? R.string.do_not_stop_believing
//            : R.string.life_is_a_very_funny_proposition_after_all);
//    tvDashBoard.append("\n");
//    tvDashBoard.append("\n");
//    tvDashBoard.append("\n");
//    tvDashBoard.append(name);
  }
}
