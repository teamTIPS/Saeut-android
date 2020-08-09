package com.teamtips.android.saeut.func.timetable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.tlaabs.timetableview.Schedule;

import java.util.ArrayList;

public class ScheduleViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Schedule>> sdMLD;

    public ScheduleViewModel(){
        sdMLD = new MutableLiveData<>();
        sdMLD.setValue(new ArrayList<>());
    }

    public LiveData<ArrayList<Schedule>> getSD(){
        return sdMLD;
    }

}