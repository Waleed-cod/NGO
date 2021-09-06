package com.codembeded.ngo.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    //Initialize variable

    MutableLiveData<String> mutableLiveData = new MutableLiveData<>();

    //Create set text method
    public void setText(String s){
        //set value
        mutableLiveData.setValue(s);
    }
    public MutableLiveData<String> getText(){
        return mutableLiveData;
    }

}
