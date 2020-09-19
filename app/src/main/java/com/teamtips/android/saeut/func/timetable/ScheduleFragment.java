package com.teamtips.android.saeut.func.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.TimetableView;
import com.teamtips.android.saeut.R;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {
    private final static String Tag = "ScheduleFragment";

    public static final int REQUEST_ADD = 1;
    public static final int REQUEST_EDIT = 2;

    public static TimetableView timetable;

    public static ScheduleFragment newInstance(){
        return new ScheduleFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.e(Tag,"onCreateView");
        final ScheduleViewModel scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        timetable = root.findViewById(R.id.timetable);
        Button bt_add = root.findViewById(R.id.add_btn);
        Button bt_clear = root.findViewById(R.id.clear_btn);

        timetable.setOnStickerSelectEventListener((idx, schedules) -> {
            Log.e(Tag,"hi");
            Intent i = new Intent(getContext(), EditActivity.class);
            i.putExtra("mode",REQUEST_EDIT);
            i.putExtra("idx", idx);
            i.putExtra("schedules", schedules);
            startActivityForResult(i,REQUEST_EDIT);
        });

        scheduleViewModel.getSD().observe(getViewLifecycleOwner(), schedules -> {
            timetable.add(schedules);
            //schedules를 timetable에 반영함
            Log.e(Tag,"onChanged");
        });

        bt_add.setOnClickListener(view -> {
            Intent i = new Intent(getContext(),EditActivity.class);
            i.putExtra("mode",REQUEST_ADD);
            startActivityForResult(i,REQUEST_ADD);
            }
        );

        bt_clear.setOnClickListener(v -> timetable.removeAll());
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.delete, menu);
        inflater.inflate(R.menu.message, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_message) {
            Intent i = new Intent(getContext(),EditActivity.class);
            i.putExtra("mode",REQUEST_ADD);
            startActivityForResult(i,REQUEST_ADD);
            return true;
        }
        if (item.getItemId() == R.id.menu_delete) {
            timetable.removeAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.e(Tag,"onActivityResult:"+requestCode+" "+resultCode+" ");

        super.onActivityResult(requestCode, resultCode, data);
        TimetableView timetable = ScheduleFragment.timetable;
        switch (requestCode) {
            case REQUEST_ADD:
                if (resultCode == EditActivity.RESULT_OK_ADD) {
                    ArrayList<Schedule> item = (ArrayList<Schedule>) data.getSerializableExtra("schedules");
                    timetable.add(item);
                }
                break;
            case REQUEST_EDIT:
                /** Edit -> Submit */
                if (resultCode == EditActivity.RESULT_OK_EDIT) { //2,2
                    Log.e(Tag,"hereherehereEdit");

                    int idx = data.getIntExtra("idx", -1);
                    ArrayList<Schedule> item = (ArrayList<Schedule>) data.getSerializableExtra("schedules");
                    timetable.edit(idx, item);
                }
                /** Edit -> Delete */
                else if (resultCode == EditActivity.RESULT_OK_DELETE) { //2,3
                    Log.e(Tag,"delete");
                    int idx = data.getIntExtra("idx", -1);
                    timetable.remove(idx);
                }
                break;
        }
    }
}
