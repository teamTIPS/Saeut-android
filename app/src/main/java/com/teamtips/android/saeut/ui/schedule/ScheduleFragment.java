package com.teamtips.android.saeut.ui.schedule;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.tlaabs.timetableview.TimetableView;
import com.teamtips.android.saeut.R;

public class ScheduleFragment extends Fragment {
    private final static String Tag = "ScheduleFragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.e(Tag,"onCreateView");

        final ScheduleViewModel scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        scheduleViewModel.getTT().observe(getViewLifecycleOwner(), new Observer<TimetableView>() {
            @Override
            public void onChanged(TimetableView timetableView) {
//                scheduleViewModel.testaddTT(timetableView);
                Log.e(Tag,"onChanged");
            }
        });


        return root;
    }
}
