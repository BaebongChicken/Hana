package com.example.hana.hana.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.hana.hana.Constants.Constants;
import com.example.hana.hana.Data.Hana;
import com.example.hana.hana.Data.Team;
import com.example.hana.hana.Data.TeamTDD;
import com.example.hana.hana.Data.User;
import com.example.hana.hana.DataBase.HanaDatabase.HanaTable;
import com.example.hana.hana.DataBase.HanaDatabase.TeamTDDTable;
import com.example.hana.hana.DataBase.HanaDatabase.TeamTable;
import com.example.hana.hana.DataBase.HanaDatabase.UserTable;

import java.util.ArrayList;

import static com.example.hana.hana.DataBase.HanaDatabase.UserTable.COL_USER_ID;

/**
 * Created by Jin Hee Lee on 2016-11-20.
 */

public class DataHandling {
    private static final String CLASSNAME = DataHandling.class.getSimpleName();
    private HanaSQLiteOpenHelper db;

    public DataHandling(Context context) {
        db = HanaSQLiteOpenHelper.getInstance(context);
    }

    public void close() {
        db.close();
    }

    //insert

    public void insert(final User data) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + data.toString());
        long rowId = db.insert(HanaDatabase.UserTable.TABLE_NAME, data.getContentValues());
        if (rowId < 0) throw new SQLException("FAIL at Insert");
    }

    public void insert(final Hana data) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + data.toString());
        long rowId = db.insert(HanaDatabase.HanaTable.TABLE_NAME, data.getContentValues());
        if (rowId < 0) throw new SQLException("FAIL at Insert");
    }
//
//    public void insert(final ActBranch data) {
//        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + data.toString());
//        long rowId = db.insert(HanaDatabase.ActBranchTable.TABLE_NAME, data.getContentValues());
//        if (rowId < 0) throw new SQLException("FAIL at Insert");
//    }

    public void insert(final Team data) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + data.toString());
        long rowId = db.insert(HanaDatabase.TeamTable.TABLE_NAME, data.getContentValues());
        if (rowId < 0) throw new SQLException("FAIL at Insert");
    }

    public void insert(final TeamTDD data) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + data.toString());
        long rowId = db.insert(HanaDatabase.TeamTDDTable.TABLE_NAME, data.getContentValues());
        if (rowId < 0) throw new SQLException("FAIL at Insert");
    }
//
//    public void insert(final Comment data) {
//        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + data.toString());
//        long rowId = db.insert(HanaDatabase.CommentsTable.TABLE_NAME, data.getContentValues());
//        if (rowId < 0) throw new SQLException("FAIL at Insert");
//    }

    //update

    public void update(final User data) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " update - " + data.toString());
        db.update(HanaDatabase.UserTable.TABLE_NAME, data.getContentValues(), data.getUser(0));

    }

    public void update(final Hana data) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " update - " + data.toString());
        db.update(HanaDatabase.HanaTable.TABLE_NAME, data.getContentValues(), data.getHanaData(0));
    }
//
//    public void update(final ActBranch data) {
//        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " update - " + data.toString());
//        db.update(HanaDatabase.ActBranchTable.TABLE_NAME, data.getContentValues(), data.getActBranchData(0));
//
//    }

    public void update(final Team data) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " update - " + data.toString());
        db.update(HanaDatabase.TeamTable.TABLE_NAME, data.getContentValues(), data.getTeamData(0));

    }

    public void update(final TeamTDD data) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " update - " + data.toString());
        db.update(HanaDatabase.TeamTDDTable.TABLE_NAME, data.getContentValues(), data.getTeamTddData(0));

    }
//
//    public void update(final Comment data) {
//        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " update - " + data.toString());
//        db.update(HanaDatabase.CommentsTable.TABLE_NAME, data.getContentValues(), data.getCommentData(0));
//
//    }

    //delete

    public void delete(final User data, final int id) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " delete - " + id);
        db.delete(HanaDatabase.UserTable.TABLE_NAME, id);

    }

    public void delete(final Hana data, final int id) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + id);
        db.delete(HanaDatabase.HanaTable.TABLE_NAME, id);
    }
//
//    public void delete(final ActBranch data, final int id) {
//        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + id);
//        db.delete(HanaDatabase.ActBranchTable.TABLE_NAME, id);
//
//    }

    public void delete(final Team data, final int id) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + id);
        db.delete(HanaDatabase.TeamTable.TABLE_NAME, id);

    }

    public void delete(final TeamTDD data, final int id) {
        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + id);
        db.delete(HanaDatabase.TeamTDDTable.TABLE_NAME, id);

    }
//
//    public void delete(final Comment data, final int id) {
//        Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " insert - " + id);
//        db.delete(HanaDatabase.CommentsTable.TABLE_NAME, id);
//
//    }
    public User getUserById(String userId){
        Cursor c=null;
        User mUser = null;

        ArrayList<User> ret = null;

        String sql = "SELECT "+COL_USER_ID+" FROM "+ UserTable.TABLE_NAME +" WHERE "+COL_USER_ID+"="+userId;
        try{
            c= db.get(sql);
            db.logCursorInfo(c);
            ret = setBindCursorUser(c);
            mUser = ret.get(0);
        }catch (SQLiteException e){
            Log.e(Constants.LOG_TAG, DataHandling.CLASSNAME + " getList ", e);
        }finally {
            if(c!=null&&c.isClosed()){
                c.close();
            }
        }
        return mUser;
    }
    public ArrayList<User> getListUser() {
        Cursor c = null;
        ArrayList<User> ret = null;
        String sql = "SELECT * FROM " + UserTable.TABLE_NAME + " ORDER BY 1";
        try {
            Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " get - ALL");
            c = db.get(sql);
            db.logCursorInfo(c);
            ret = setBindCursorUser(c);
        } catch (SQLiteException e) {
            Log.e(Constants.LOG_TAG, DataHandling.CLASSNAME + " getList ", e);
        } finally {
            if (c != null && c.isClosed()) {
                c.close();
            }
        }
        return ret;
    }

    public ArrayList<Hana> getListHana() {
        Cursor c = null;
        ArrayList<Hana> ret = null;
        String sql = "SELECT * FROM " + HanaDatabase.HanaTable.TABLE_NAME + " ORDER BY 1";
        try {
            Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " get - ALL");
            c = db.get(sql);
            db.logCursorInfo(c);
            ret = setBindCursorHana(c);
        } catch (SQLiteException e) {
            Log.e(Constants.LOG_TAG, DataHandling.CLASSNAME + " getList ", e);
        } finally {
            if (c != null && c.isClosed()) {
                c.close();
            }
        }
        return ret;
    }

    public ArrayList<Team> getListTeam() {
        Cursor c = null;
        ArrayList<Team> ret = null;
        String sql = "SELECT * FROM " + HanaDatabase.TeamTable.TABLE_NAME + " ORDER BY 1";
        try {
            Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " get - ALL");
            c = db.get(sql);
            db.logCursorInfo(c);
            ret = setBindCursorTeam(c);
        } catch (SQLiteException e) {
            Log.e(Constants.LOG_TAG, DataHandling.CLASSNAME + " getList ", e);
        } finally {
            if (c != null && c.isClosed()) {
                c.close();
            }
        }
        return ret;
    }

    public ArrayList<TeamTDD> getListTeamTdd() {
        Cursor c = null;
        ArrayList<TeamTDD> ret = null;
        String sql = "SELECT * FROM " + HanaDatabase.TeamTDDTable.TABLE_NAME + " ORDER BY 1";
        try {
            Log.d(Constants.LOG_TAG, DataHandling.CLASSNAME + " get - ALL");
            c = db.get(sql);
            db.logCursorInfo(c);
            ret = setBindCursorTeamTDD(c);
        } catch (SQLiteException e) {
            Log.e(Constants.LOG_TAG, DataHandling.CLASSNAME + " getList ", e);
        } finally {
            if (c != null && c.isClosed()) {
                c.close();
            }
        }
        return ret;
    }

    private ArrayList<User> setBindCursorUser(final Cursor c) {
        ArrayList<User> ret = new ArrayList<User>();
        int numRows = c.getCount();
        c.moveToFirst();
        for (int i = 0; i < numRows; i++) {
            User mObj = new User();
            String[] mDatas = new String[UserTable.getColumnCount()];
            for (int j = 0; j < mDatas.length; j++) {
                mDatas[j] = c.getString(c.getColumnIndex(UserTable.getColumnNames()[j]));
            }
            mObj.setUserData(mDatas);
            ret.add(mObj);
            c.moveToNext();
        }
        return ret;
    }

    private ArrayList<Hana> setBindCursorHana(final Cursor c) {
        ArrayList<Hana> ret = new ArrayList<Hana>();
        int numRows = c.getCount();
        c.moveToFirst();
        for (int i = 0; i < numRows; i++) {
            Hana mObj = new Hana();
            String[] mDatas = new String[HanaTable.getColumnCount()];
            for (int j = 0; j < mDatas.length; j++) {
                mDatas[j] = c.getString(c.getColumnIndex(HanaTable.getColumnNames()[j]));
            }
            mObj.setHanaData(mDatas);
            ret.add(mObj);
            c.moveToNext();
        }
        return ret;
    }
    private ArrayList<Team> setBindCursorTeam(final Cursor c) {
        ArrayList<Team> ret = new ArrayList<Team>();
        int numRows = c.getCount();
        c.moveToFirst();
        for (int i = 0; i < numRows; i++) {
            Team mObj = new Team();
            String[] mDatas = new String[TeamTable.getColumnCount()];
            for (int j = 0; j < mDatas.length; j++) {
                mDatas[j] = c.getString(c.getColumnIndex(TeamTable.getColumnNames()[j]));
            }
            mObj.setTeamData(mDatas);
            ret.add(mObj);
            c.moveToNext();
        }
        return ret;
    }

    private ArrayList<TeamTDD> setBindCursorTeamTDD(final Cursor c) {
        ArrayList<TeamTDD> ret = new ArrayList<TeamTDD>();
        int numRows = c.getCount();
        c.moveToFirst();
        for (int i = 0; i < numRows; i++) {
            TeamTDD mObj = new TeamTDD();
            String[] mDatas = new String[TeamTDDTable.getColumnCount()];
            for (int j = 0; j < mDatas.length; j++) {
                mDatas[j] = c.getString(c.getColumnIndex(TeamTDDTable.getColumnNames()[j]));
            }
            mObj.setTeamTddData(mDatas);
            ret.add(mObj);
            c.moveToNext();
        }
        return ret;
    }


}
