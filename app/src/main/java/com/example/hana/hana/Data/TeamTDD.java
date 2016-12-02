package com.example.hana.hana.Data;

import android.content.ContentValues;

import com.example.hana.hana.DataBase.HanaDatabase;

/**
 * Created by JinHee on 2016-11-14.
 */

public class TeamTDD {
    final static int DATA_COUNT = 4;
    private String[] teamTddData;
    public TeamTDD() {
        this.teamTddData = null;
    }

    public TeamTDD(String[] teamTddData){
        this.teamTddData = teamTddData;
    }

    public TeamTDD(String teamTddId,
                   String content,
                   String state,
                   String teamId){
        teamTddData = new String[DATA_COUNT];
        teamTddData[0] = teamTddId;
        teamTddData[1] = content;
        teamTddData[2] = state;
        teamTddData[3] = teamId;

    }
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();

        for (int i = 0; i < teamTddData.length; i++) {
            values.put(HanaDatabase.TeamTDDTable.getColumnNames()[i], teamTddData[i]);
        }

        return values;
    }
    public String[] getTeamTddData(){
        return teamTddData;
    }

    public String getTeamTddData(int idx){
        return teamTddData[idx];
    }

    public void setTeamTddData(String[] teamTddData){
        this.teamTddData =teamTddData;
    }
}
