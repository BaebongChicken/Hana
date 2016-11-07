package com.example.hana.hana.Data;

/**
 * Created by JinHee on 2016-11-07.
 */
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserData {
    public String userId;
    public String userName;
    public String userPhone;
    public String userThumbnailURL;

    public UserData(){

    }
    public UserData(String userId, String userName, String userPhone, String userThumbnailURL){
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userThumbnailURL = userThumbnailURL;
    }
}
