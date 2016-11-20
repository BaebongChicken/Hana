package com.example.hana.hana.Data;

import android.content.ContentValues;

import com.example.hana.hana.DataBase.HanaDatabase;

/**
 * Created by JinHee on 2016-11-14.
 */

public class ActBranch {
    final static int DATA_COUNT = 3;
    private String[] actBranchData;

    public ActBranch(String[] actBranchData) {
        this.actBranchData = actBranchData;
    }

    public ActBranch(String actBranchId,
                     String actBranchName,
                     String teamId) {

        this.actBranchData = new String[DATA_COUNT];
        this.actBranchData[0] = actBranchId;
        this.actBranchData[1] = actBranchName;
        this.actBranchData[2] = teamId;

    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();

        for (int i = 0; i < actBranchData.length; i++) {
            values.put(HanaDatabase.ActBranchTable.getColumnNames()[i], actBranchData[i]);
        }

        return values;
    }

    public String[] getActBranchData() {
        return actBranchData;
    }

    public String getActBranchData(int idx) {
        return actBranchData[idx];
    }

    public void setActBranchData(String[] actBranchData) {
        this.actBranchData = actBranchData;
    }
}
