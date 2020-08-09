package com.teamtips.android.saeut.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.TimberLogger;


public class DashboardFragment extends Fragment {

  private static final String ARG_NAME = "arg_name";

  public static final String ARG_OBJECT = "object";
  public static ViewPager viewPager;
  private DashboardViewModel dashboardViewModel;
  private PagerAdapter pagerAdapter;

  int i = 0;
  private static TabLayout tabLayout;

  public static DashboardFragment newInstance(String name) {
    Bundle bundle = new Bundle();
    bundle.putString(ARG_NAME, name);
    DashboardFragment dashboardFragment = new DashboardFragment();
    dashboardFragment.setArguments(bundle);
    return dashboardFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel.class);

    View view = inflater.inflate(R.layout.fragment_dashboard, container, false);


    viewPager = (ViewPager)view.findViewById(R.id.view_pager);
    viewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
    viewPager.setCurrentItem(0);

    tabLayout = view.findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ViewPager viewPager = view.findViewById(R.id.view_pager);
    GooglePlusFragmentPageAdapter adapter =
        new GooglePlusFragmentPageAdapter(
            getChildFragmentManager(), requireArguments().getString(ARG_NAME));
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
    TabLayout tabLayout = view.findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    getLifecycle().addObserver(new TimberLogger(this));
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    inflater.inflate(R.menu.send, menu);
  }

  private static class GooglePlusFragmentPageAdapter extends FragmentPagerAdapter {

    private final String name;

    public GooglePlusFragmentPageAdapter(FragmentManager fm, String name) {
      super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
      this.name = name;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
      return DashboardChildFragment.newInstance(position, name);
    }

    @Override
    public int getCount() {
      return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      return "Dashboard " + position;
    }
  }

  private class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm){
      super(fm);
      getItem(0);
    }

    public Fragment getItem(int position){
      return new DashboardChildFragment(position) ;
    }

    public int getCount(){
      return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      if(position == 0) {
        return "돌봄 요청 리스트";
      } else {
        return "돌봄 신청 리스트";

      }
    }
  }
}
