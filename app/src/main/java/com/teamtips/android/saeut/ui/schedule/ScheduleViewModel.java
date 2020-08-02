package com.teamtips.android.saeut.ui.schedule;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;

public class ScheduleViewModel extends ViewModel {

    private MutableLiveData<TimetableView> ttMLD;

    public ScheduleViewModel() {
        ttMLD = new MutableLiveData<>();
    }

    public LiveData<TimetableView> getTT() {
        return ttMLD;
    }

    public void testaddTT(TimetableView TT){
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("Data Structure"); // sets subject
        schedule.setClassPlace("IT-601"); // sets place
        schedule.setProfessorName("Won Kim"); // sets professor
        schedule.setStartTime(new Time(10,0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13,30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
//.. add one or more schedules
        TT.add(schedules);
    }
}