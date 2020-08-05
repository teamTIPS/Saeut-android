package com.teamtips.android.saeut.ui.schedule;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;

public class ScheduleViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Schedule>> sdMLD;

    public ScheduleViewModel(){
        sdMLD = new MutableLiveData<>();
        sdMLD.setValue(new ArrayList<Schedule>());
    }

    public LiveData<ArrayList<Schedule>> getSD(){
        return sdMLD;
    }

    public void addSchedule(){
        ArrayList<Schedule> schedules = sdMLD.getValue();//new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("Data Structure"); // sets subject
        schedule.setClassPlace("IT-601"); // sets place
        schedule.setProfessorName("Won Kim"); // sets professor
        schedule.setStartTime(new Time(10,0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13,30)); // sets the end of class time (hour,minute)
        if (schedules != null) {
            schedules.add(schedule);
        }
        sdMLD.setValue(schedules);
    }
}