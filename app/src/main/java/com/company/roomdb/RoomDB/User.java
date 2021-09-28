package com.company.roomdb.RoomDB;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import javax.xml.namespace.QName;

@Entity
public class User {




    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="first_name")
    public String firstName;
     @ColumnInfo(name = "last_name")
    public String lastName;




}
