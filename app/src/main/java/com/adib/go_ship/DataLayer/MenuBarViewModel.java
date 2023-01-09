package com.adib.go_ship.DataLayer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MenuBarViewModel extends ViewModel {
    private MutableLiveData<Integer> selectedMenu;
    private MutableLiveData<Integer> previousSelectedMenu;

    public MutableLiveData<Integer> getSelectedMenu() {
        if (selectedMenu == null) {
            selectedMenu = new MutableLiveData<>(1);
            previousSelectedMenu = new MutableLiveData<>(1);
        }
        return selectedMenu;
    }

    public MutableLiveData<Integer> getPreviousSelectedMenu() {
        if (previousSelectedMenu == null) {
            selectedMenu = new MutableLiveData<>(1);
            previousSelectedMenu = new MutableLiveData<>(1);
        }
        return previousSelectedMenu;
    }

    public void setSelectedMenu(int selectedMenu) {
        this.previousSelectedMenu.setValue(this.selectedMenu.getValue());
        this.selectedMenu.setValue(selectedMenu);
    }
}