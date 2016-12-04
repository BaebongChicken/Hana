package com.example.hana.hana.Data;

import android.content.ContentValues;

import com.example.hana.hana.DataBase.HanaDatabase;

import java.util.Arrays;

/**
 * Created by JinHee on 2016-11-07.
 */

public class User {
    private final static int USER_DATA_COUNT = 6;
    private String[] userData;

    public User() {
        this.userData = null;
    }

    public User(String[] userData) {
        this.userData = userData;
    }


    public User(String userId,
                String userName,
                String userPhone,
                String userThumbnailURL,
                String hanaId,
                String level) {

        this.userData = new String[USER_DATA_COUNT];
        this.userData[0] = userId;
        this.userData[1] = userName;
        this.userData[2] = userPhone;
        this.userData[3] = userThumbnailURL;
        this.userData[4] = hanaId;
        this.userData[5] = level;
    }

    @Override
    public String toString() {
        return Arrays.toString(getUserData());
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();

        for (int i = 0; i < userData.length; i++) {
            values.put(HanaDatabase.UserTable.getColumnNames()[i], userData[i]);
        }

        return values;
    }

    public String[] getUserData() {
        return this.userData;
    }

    public String getUserData(int idx) {
        return this.userData[idx];
    }

    public void setUserData(String[] userData) {
        this.userData = userData;
    }

    public void setUserData(int idx, String userData) {
        this.userData[idx] = userData;
    }
}
