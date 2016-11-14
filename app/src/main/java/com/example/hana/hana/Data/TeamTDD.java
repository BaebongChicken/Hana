package com.example.hana.hana.Data;

/**
 * Created by JinHee on 2016-11-14.
 */

public class TeamTDD {
    final static int DATA_COUNT = 4;
    private String[] teamTddData;

    public TeamTDD(String[] teamTddData){
        this.teamTddData = teamTddData;
    }

    public TeamTDD(String teamTddId,
                   String content,
                   String state,
                   String commentsId){
        teamTddData = new String[DATA_COUNT];
        teamTddData[0] = teamTddId;
        teamTddData[1] = content;
        teamTddData[2] = state;
        teamTddData[3] = commentsId;

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
