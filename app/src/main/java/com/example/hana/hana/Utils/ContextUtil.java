package com.example.hana.hana.Utils;

/**
 * Created by Jin Hee Lee on 2016-12-02.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hana.hana.Data.User;

public class ContextUtil {
    private static final String PREFERENCE_NAME = "Hana";
    private static final String LOGGED_IN = "LOGGED_IN";
    private static final String USER_ID = "USER_ID";
    private static final String LAST_TEAM_ID = "LAST_TEAM_ID";
    private static final String USER_VALIDATE_KEY = "USER_VALIDATE_KEY";
    private static final String FB_SESSION = "FB_SESSION";
    private static User loginUser = null;
//    private static ArrayList<TeamData> myTeamDataArrayList = null;
    public static int lastCategoryId;

    public static boolean isUserLoggedin(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        return prefs.getBoolean(LOGGED_IN, false);
    }

    public static void setLoggedIn(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        prefs.edit().putBoolean(LOGGED_IN, value).apply();

    }

//
//    public static void setUserData(Context context, UserData userData) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//
//        prefs.edit().putInt(USER_ID, userData.userId).commit();
//        prefs.edit().putInt(LAST_TEAM_ID, userData.last_team).commit();
//        prefs.edit().putString(USER_VALIDATE_KEY, userData.validate_key).commit();
//        loginUser = userData;
//    }
//
//    public static UserData getMyUserData(Context context) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//        if (loginUser == null) {
//            loginUser = new UserData();
//            loginUser.userId = prefs.getInt(USER_ID, -1);
//            loginUser.last_team = prefs.getInt(LAST_TEAM_ID, -1);
//            loginUser.validate_key = prefs.getString(USER_VALIDATE_KEY, "");
//        }
//        return loginUser;
//    }
//    public static void setLoginUserId(Context context, int userId) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//
//        prefs.edit().putInt(USER_ID, userId).commit();
//    }
//
//    public static int getLoginUserId(Context context) {
//
//        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//
//        return prefs.getInt(USER_ID, -1);
//    }

//
//    public static void setUserLastTeam(Context context, int teamId) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//
//        prefs.edit().putInt(LAST_TEAM_ID, teamId).commit();
//    }
//
//    public static int getUserLastTeam(Context context) {
//
//        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//
//        return prefs.getInt(LAST_TEAM_ID, -1);
//    }
//
//    public static String getLoginUserValidateKey(Context context) {
//
//        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//
//        return prefs.getString(USER_VALIDATE_KEY, "null");
//    }
//
//    public static void setFacebookSession(Context context, int session) {
//        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//
//        prefs.edit().putInt(FB_SESSION, session).commit();
//    }
//
//    public static int getFacebookSession(Context context) {
//
//        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//
//        return prefs.getInt(FB_SESSION, -1);
//    }
//
//    public static void setMyTeamDataArrayList (ArrayList<TeamData> datas) {
//        myTeamDataArrayList = datas;
//    }
//
//
//    public static ArrayList<TeamData> getMyTeamDataArrayList() {
//        return myTeamDataArrayList;
//    }
//
//    public static void setLastCategoryId(int num) {
//        lastCategoryId = num;
//    }
//
//    public static int getLastCategoryId () {
//        return lastCategoryId;
//    }
}

