package com.example.hana.hana.Utils;

/**
 * Created by Jin Hee Lee on 2016-12-02.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hana.hana.Data.Hana;
import com.example.hana.hana.Data.User;

public class ContextUtil {
    private static final String PREFERENCE_NAME = "Hana";
    private static final String LOGGED_IN = "LOGGED_IN";
    private static final String USER_ID = "USER_ID";
    private static final String HANA_ID = "HANA_ID";
    private static final String HANA_PATH = "HANA_PATH";
    private static final String TEAM_ID = "TEAM_ID";

    private static final String LAST_TEAM_ID = "LAST_TEAM_ID";
    private static final String USER_VALIDATE_KEY = "USER_VALIDATE_KEY";
    private static final String FB_SESSION = "FB_SESSION";
    private static User loginUser = null;
    private static Hana loginHana = null;
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
    public static void setLoginUser(Context context, User user) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

//        prefs.edit().putString(USER_ID, user.getUserData(0)).apply();
        loginUser = user;
    }

    public static User getLoginUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        if (loginUser == null) {
            loginUser = new User();
            String[] emptyUserData = {"null", "null", "null", "null", "null"};
            loginUser.setUserData(emptyUserData);
        }
        return loginUser;
    }

    public static void setLoginUserId(Context context, User user) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        prefs.edit().putString(USER_ID, user.getUserData(0)).apply();
    }

    public static String getLoginUserId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        return prefs.getString(USER_ID, null);
    }

    public static void setLoginHana(Context context, Hana hana) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        prefs.edit().putString(HANA_ID, hana.getHanaData(0)).apply();
        loginHana = hana;
    }

    public static Hana getLoginHana(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        if (loginHana == null) {
            loginHana = new Hana();
            String[] emptyHanaData = {"null", "null", "null", "null"};
            loginHana.setHanaData(emptyHanaData);
        }
        return loginHana;
    }

    public static void setLoginHanaId(Context context, Hana hana) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        prefs.edit().putString(HANA_ID, hana.getHanaData(0)).apply();
    }

    public static String getLoginHanaId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        return prefs.getString(HANA_ID, null);
    }

    public static void setHanaImagePath(Context context, String path) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        prefs.edit().putString(HANA_PATH, path).apply();
    }

    public static String getHanaImagePath(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        return prefs.getString(HANA_PATH, null);
    }

    public static void setLoginTeamId(Context context, String teamId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        prefs.edit().putString(TEAM_ID, teamId).apply();
    }

    public static String getLoginTeamId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        return prefs.getString(TEAM_ID, null);
    }

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

