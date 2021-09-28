package com.company.roomdb.RoomDB;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ListViewModel extends ViewModel {



    public LiveData<List<User>> getListFromViewModel(){

        Log.d(TAG,"Переменная живойдаты во вьюмодели "+Repository.getDataBase().userDAO().getAllUser().getValue());
        return Repository.getDataBase().userDAO().getAllUser();

    }
    public void deleteParty(User user) {

        Repository.getDataBase().userDAO().DeleteUser(user);
    }
}
