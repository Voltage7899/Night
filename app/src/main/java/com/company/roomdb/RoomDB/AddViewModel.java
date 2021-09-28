package com.company.roomdb.RoomDB;

import android.content.Context;

import androidx.lifecycle.ViewModel;

public class AddViewModel extends ViewModel {
    public void AddPartyThroughViewModel(String name, String last){

        User user =new User();
        user.firstName=name;
        user.lastName=last;
        Repository.getDataBase().userDAO().insertUser(user);
    }
}
