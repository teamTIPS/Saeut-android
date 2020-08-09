package com.teamtips.android.saeut.ui.schedule;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;

import static com.teamtips.android.saeut.ui.schedule.ScheduleFragment.REQUEST_ADD;
import static com.teamtips.android.saeut.ui.schedule.ScheduleFragment.REQUEST_EDIT;

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