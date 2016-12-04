package com.example.hana.hana.Data;

import android.content.ContentValues;

import com.example.hana.hana.DataBase.HanaDatabase;

/**
 * Created by JinHee on 2016-11-14.
 */
public class Hana {
    private final static int HANA_DATA_COUNT = 4;
    private String[] hanaData;
    public Hana() {
        this.hanaData = null;
    }

    public Hana(String[] hanaData) {
        this.hanaData = hanaData;
    }

    public Hana(String hanaId,
                String hanaName,
                String hanaThumbnail,
                String hanaLevelList)
              {

        this.hanaData = new String[HANA_DATA_COUNT];
        this.hanaData[0] = hanaId;
        this.hanaData[1] = hanaName;
        this.hanaData[2] = hanaThumbnail;
        this.hanaData[3] = hanaLevelList;
//        this.hanaData[4] = hanaMemberCount;
//        this.hanaData[5] = actBranchId;
//        this.hanaData[6] = albumBranchId;
    }

    @Override
    public String toString() {
        return "HANA [hanaId="
                + String.valueOf(hanaData[0])
                + ", hanaName=" + String.valueOf(hanaData[1])
                + ", hanaThumbnail=" + String.valueOf(hanaData[2])
                + ", hanaLevelList=" + String.valueOf(hanaData[3])
                + "]";

    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();

        for (int i = 0; i < hanaData.length; i++) {
            values.put(HanaDatabase.HanaTable.getColumnNames()[i], hanaData[i]);
        }

        return values;
    }
    public String[] getHanaData() {
        return this.hanaData;
    }

    public String getHanaData(int idx) {
        return this.hanaData[idx];
    }

    public void setHanaData(String[] hanaData) {
        this.hanaData = hanaData;
    }
    public void setHanaData(int idx, String hanaData){
        this.hanaData[idx] = hanaData;
    }
}
