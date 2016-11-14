package com.example.hana.hana.Data;

/**
 * Created by JinHee on 2016-11-14.
 */
public class Hana {
    final static int DATA_COUNT = 7;
    private String[] hanaData;

    public Hana(String[] hanaData) {
        this.hanaData = hanaData;
    }

    public Hana(String hanaId,
                String hanaName,
                String hanaThumbnail,
                String hanaNotice,
                String hanaMemberCount,
                String actBranchId,
                String albumBranchId) {

        this.hanaData = new String[DATA_COUNT];
        this.hanaData[0] = hanaId;
        this.hanaData[1] = hanaName;
        this.hanaData[2] = hanaThumbnail;
        this.hanaData[3] = hanaNotice;
        this.hanaData[4] = hanaMemberCount;
        this.hanaData[5] = actBranchId;
        this.hanaData[6] = albumBranchId;
    }

    public String[] getHanaData() {
        return hanaData;
    }

    public String getHanaData(int idx) {
        return hanaData[idx];
    }

    public void setHanaData(String[] hanaData) {
        this.hanaData = hanaData;
    }
}
