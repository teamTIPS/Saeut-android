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

    public void addSchedule(String caretype, String available, Time starttime, Time endtime){
        //classtitle: 희망 돌봄타입(Ex. 노인, 아이들, 장애인)
        //classplace: 가능 능력(Ex. 운전가능, 놀이가능 등...)
        ArrayList<Schedule> schedules = sdMLD.getValue();
        Schedule schedule = new Schedule();
        schedule.setClassTitle(caretype); // sets subject
        schedule.setClassPlace(available); // sets place
        schedule.setStartTime(starttime); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(endtime); // sets the end of class time (hour,minute)
        if (schedules != null) {
            schedules.add(schedule);
        }
        sdMLD.setValue(schedules);
    }
}