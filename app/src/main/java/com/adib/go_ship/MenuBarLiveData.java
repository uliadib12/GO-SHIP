package com.adib.go_ship;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MenuBarLiveData extends ViewModel {

    private MutableLiveData<Integer> idLocation ;

    public MutableLiveData<Integer> getCurrentLocation() {
        if (idLocation == null) {
            idLocation = new MutableLiveData<Integer>(0);
        }
        return idLocation;
    }
}
