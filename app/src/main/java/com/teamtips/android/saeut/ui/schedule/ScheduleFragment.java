package com.teamtips.android.saeut.ui.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    public static final int REQUEST_ADD = 1;
    public static final int REQUEST_EDIT = 2;

    private Button addBtn;
    private Button clearBtn;

    public static TimetableView timetable;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.e(Tag,"onCreateView");


        final ScheduleViewModel scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        timetable = root.findViewById(R.id.timetable);
        Button bt_add = root.findViewById(R.id.add_btn);
        Button bt_clear = root.findViewById(R.id.clear_btn);
        timetable.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
            @Override
            public void OnStickerSelected(int idx, ArrayList<Schedule> schedules) {
                Intent i = new Intent(getContext(), EditActivity.class);
                i.putExtra("mode",REQUEST_EDIT);
                i.putExtra("idx", idx);
                i.putExtra("schedules", schedules);
                getActivity().startActivityForResult(i,REQUEST_EDIT);
            }
        });


        scheduleViewModel.getSD().observe(getViewLifecycleOwner(), new Observer<ArrayList<Schedule>>() {
            @Override
            public void onChanged(ArrayList<Schedule> schedules) {
                timetable.add(schedules);
                //schedules를 timetable에 반영함
                Log.e(Tag,"onChanged");
            }
        });

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                scheduleViewModel.addSchedule("노인 돌봄 희망", "운전 가능", new Time(10,30), new Time(17,30));
                //임의의 스케쥴을 뷰모델에 저장함
//                switch (view.getId()){
//                    case R.id.add_btn:
                        Intent i = new Intent(getContext(),EditActivity.class);
                        i.putExtra("mode",REQUEST_ADD);
                        startActivityForResult(i,REQUEST_ADD);
//                        break;
//                    case R.id.clear_btn:
//                        break;
                }
            }
        );
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            timetable.removeAll();

           }
        });

        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.e(Tag,"onActivityResult");

        super.onActivityResult(requestCode, resultCode, data);
        TimetableView timetable = ScheduleFragment.timetable;
        switch (requestCode) {
            case REQUEST_ADD:
                if (resultCode == EditActivity.RESULT_OK_ADD) {
                    ArrayList<Schedule> item = (ArrayList<Schedule>) data.getSerializableExtra("schedules");
                    Log.e(Tag,"item:"+item.toString());
                    timetable.add(item);
                }
                break;
            case REQUEST_EDIT:
                /** Edit -> Submit */
                if (resultCode == EditActivity.RESULT_OK_EDIT) {
                    Log.e(Tag,"Edit");

                    int idx = data.getIntExtra("idx", -1);
                    ArrayList<Schedule> item = (ArrayList<Schedule>) data.getSerializableExtra("schedules");
                    timetable.edit(idx, item);
                }
                /** Edit -> Delete */
                else if (resultCode == EditActivity.RESULT_OK_DELETE) {
                    Log.e(Tag,"delete");
                    int idx = data.getIntExtra("idx", -1);
                    timetable.remove(idx);
                }
                break;
        }
    }
}
