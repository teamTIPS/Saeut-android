package com.teamtips.android.saeut.func.dashboard;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.AsyncTask;
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
import com.teamtips.android.saeut.func.dashboard.model.Post;
import com.teamtips.android.saeut.network.RequestHttpURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
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

    dashboardChildAdapter = new DashboardChildAdapter(root.getContext(), R.layout.adapter_dashboard, postArrayList);
    listView = root.findViewById(R.id.lv_child);

    listView.setAdapter(dashboardChildAdapter);

    String url = "http://49.50.173.180:8080/saeut/post";
    NetworkTask networkTask = new NetworkTask(url,null);
    networkTask.execute();

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

  public class NetworkTask extends AsyncTask<Void, Void, String> {

    private String url;
    private ContentValues values;


    public NetworkTask(String url, ContentValues values) {

      this.url = url;
      this.values = values;
    }

    @Override
    protected String doInBackground(Void... params) {

      String result; // 요청 결과를 저장할 변수.
      RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
      result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

      return result;
    }

    @Override
    protected void onPostExecute(String s) {
      super.onPostExecute(s);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      try {
        JSONArray jsonArray = new JSONArray(s);
        for(int i=0;i<jsonArray.length();i++){
          JSONObject json = jsonArray.getJSONObject(i);
          int post_id = json.getInt("post_id");
          String account_id = json.getString("account_id");
          String title = json.getString("title");
          Date post_date = sdf.parse("2020-01-01");
          String address1 = json.getString("address1");
          dashboardChildAdapter.addItem(post_id, account_id, title, post_date, address1);
          dashboardChildAdapter.notifyDataSetChanged();
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  } //NetworkTask
}
