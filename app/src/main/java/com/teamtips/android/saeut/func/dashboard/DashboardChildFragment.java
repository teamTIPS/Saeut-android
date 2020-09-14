package com.teamtips.android.saeut.func.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.TimberLogger;
import com.teamtips.android.saeut.func.dashboard.model.Post;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkService;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkTask;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Retrofit;

public class DashboardChildFragment extends Fragment {

  private static final String ARGUMENT_POSITION = "argument_position";
  private static final String ARGUMENT_NAME = "argument_name";
  public static final String TAG = "DashboardChildFragment";

  private Retrofit retrofit;
  private PostNetworkService postNetworkService;
  private DashboardChildAdapter dashboardChildAdapter;
  private ListView listView;
  private ArrayList<Post> postArrayList;
  private PostNetworkTask postNetworkTask;

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

//    retrofit = new Retrofit.Builder().baseUrl(String.valueOf(R.string.base_url)).build();
//    postNetworkService = retrofit.create(PostNetworkService.class);
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    int position = requireArguments().getInt(ARGUMENT_POSITION, -1);

    // 삭제 구현...? -> 내가 작성한 게시물에서만 작동되도록 하는게 좋을듯
    if (position == 1) {
      inflater.inflate(R.menu.delete, menu);
    }
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {

    View root = inflater.inflate(R.layout.fragment_dashboard_child, container, false);

    postArrayList = new ArrayList<Post>();

//    postNetworkService = new PostNetworkService();
//    postNetworkService.setRetrofitInit();
//
//    try {
//      postArrayList = postNetworkService.callPostList();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    dashboardChildAdapter = new DashboardChildAdapter(root.getContext(), R.layout.adapter_dashboard, postArrayList);
    listView = root.findViewById(R.id.lv_child);

    listView.setAdapter(dashboardChildAdapter);

    // fragment position에 따른 url 구현
    String url;
    String account_id = "test";
    if(position == 0) {
      // 전체 리스트를 불러오는 URL
      url = "http://49.50.173.180:8080/saeut/post";
    } else if(position == 1) {
      // 내가 작성한 게시물만 불러오는 URL -> 추후 수정 필요
      url = "http://49.50.173.180:8080/saeut/post/" + account_id;
    } else  {
      // 내가 신청한 게시물만 불러오는 URL -> 추후 수정 필요.
      url = "http://49.50.173.180:8080/saeut/post/" + account_id;
    }


    postNetworkTask = new PostNetworkTask(url,null, dashboardChildAdapter);
    postNetworkTask.execute();


    // view 클릭 시 이벤트 처리
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 상세 페이지 뜨도록 정의해야 함.
        // 이 때 로그인 여부 체크
        // 기타 등등 ...
        Log.e(TAG, "onItemClick");
        // fragment position에 따른 url 구현
        String account_id = "test";
        String url = "http://49.50.173.180:8080/saeut/post/" + account_id;

        // 근데 이걸 똑같은 클래스로 넘겨도 되나? 흐음 안될거같은데
        postNetworkTask = new PostNetworkTask(url,null, dashboardChildAdapter);
        postNetworkTask.execute();

        // 수정되어야 함. 서버에서 Post 객체 전달받아야 하기 때문에
        Post post = new Post();

        Intent intent = new Intent(getActivity(), DetailPostActivity.class);
        intent.putExtra("post", (Serializable) post);
        startActivity(intent);
      }
    });

    return root;
  }
}
