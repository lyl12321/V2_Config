package com.lqwq.v2_config.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();

    }

    @Override
    protected void onCleared() {

    }

    public LiveData<String> getText() {
        return mText;
    }
}