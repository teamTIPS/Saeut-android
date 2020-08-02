package com.teamtips.android.saeut.ui.schedule;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;
import com.teamtips.android.saeut.R;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {
    private final static String Tag = "ScheduleFragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.e(Tag,"onCreateView");


        final ScheduleViewModel scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        final TimetableView timetable = root.findViewById(R.id.timetable);

        Button bt_add = root.findViewById(R.id.add_btn);

        scheduleViewModel.getSD().observe(getViewLifecycleOwner(), new Observer<ArrayList<Schedule>>() {
            @Override
            public void onChanged(ArrayList<Schedule> schedules) {
                timetable.add(schedules);
                //schedules를 timetable에 띄움
                Log.e(Tag,"onChanged");
            }
        });

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleViewModel.addSchedule();
                //임의의 스케쥴을 뷰모델에 저장함
                Log.e(Tag,"onClick");
            }
        });

        return root;


    }
}
