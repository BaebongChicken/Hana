package com.example.hana.hana.Data;

/**
 * Created by JinHee on 2016-11-07.
 */

public class User {
    final static int DATA_COUNT = 6;
    private String[] userData;

    public User(String[] userData) {
        this.userData = userData;
    }

    public User(String userId,
                String userName,
                String userPhone,
                String userThumbnailURL,
                String hanaId,
                String level) {

        this.userData = new String[DATA_COUNT];
        this.userData[0] = userId;
        this.userData[1] = userName;
        this.userData[2] = userPhone;
        this.userData[3] = userThumbnailURL;
        this.userData[4] = hanaId;
        this.userData[5] = level;
    }

    public String[] getUser(){
        return this.userData;
    }

    public String getUser(int idx){
        return this.userData[idx];
    }

    public void setUserData(String[] userData){
        this.userData = userData;
    }
}
