package com.example.hana.hana.Data;

import android.content.ContentValues;

/**
 * Created by Jin Hee Lee on 2016-12-04.
 */

public class State {
    private String userLogin;
    private String currentUserId;
    private String currentHanaId;

    public State(String userLogin, String currentUserId, String currentHanaId) {
        this.userLogin = userLogin;
        this.currentUserId = currentUserId;
        this.currentHanaId = currentHanaId;
    }

    public State(String[] data) {
        this.userLogin = data[0];
        this.currentUserId = data[1];
        this.currentHanaId = data[2];
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put("STATE_ID", 1);
        values.put("USER_LOGIN", userLogin);
        values.put("CURRENT_USER", currentUserId);
        values.put("CURRENT_HANA", currentHanaId);


        return values;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getCurrentHanaId() {
        return currentHanaId;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }
}
