package com.example.hana.hana.Data;

/**
 * Created by JinHee on 2016-11-07.
 */

public class User {
    public String userId;
    public String userName;
    public String userPhone;
    public String userThumbnailURL;
    public String hanaId;
    public String level;
    public User(){

    }
    public User(String userId, String userName, String userPhone, String userThumbnailURL, String hanaId, String level){
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userThumbnailURL = userThumbnailURL;
        this.hanaId = hanaId;
        this.level = level;
    }
}
