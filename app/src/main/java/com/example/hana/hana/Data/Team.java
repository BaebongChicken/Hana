package com.example.hana.hana.Data;

/**
 * Created by JinHee on 2016-11-14.
 */

public class Team {
    final static int DATA_COUNT = 3;
    private String[] teamData;

    public Team(String[] teamData) {

        this.teamData = teamData;
    }

    public Team(String teamId,
                String teamName,
                String teamTddId) {
        teamData = new String[DATA_COUNT];
        teamData[0] = teamId;
        teamData[1] = teamName;
        teamData[2] = teamTddId;

    }

    public String[] getTeamData() {
        return teamData;
    }

    public String getTeamData(int idx){
        return teamData[idx];
    }
    public void setTeamData(String[] teamData) {
        this.teamData = teamData;
    }


}