package com.example.hana.hana.Data;

import android.content.ContentValues;

import com.example.hana.hana.DataBase.HanaDatabase;

/**
 * Created by JinHee on 2016-11-14.
 */

public class Team {
    final static int DATA_COUNT = 4;
    private String[] teamData;

    public Team() {
        this.teamData = null;
    }

    public Team(String[] teamData) {

        this.teamData = teamData;
    }

    public Team(String teamId,
                String teamName,
                String memberId,
                String hanaId) {
        teamData = new String[DATA_COUNT];
        teamData[0] = teamId;
        teamData[1] = teamName;
        teamData[2] = memberId;
        teamData[3] = hanaId;

    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();

        for (int i = 0; i < teamData.length; i++) {
            values.put(HanaDatabase.TeamTable.getColumnNames()[i], teamData[i]);
        }

        return values;
    }

    public String[] getTeamData() {
        return teamData;
    }

    public String getTeamData(int idx) {
        return teamData[idx];
    }

    public void setTeamData(String[] teamData) {
        this.teamData = teamData;
    }


}
