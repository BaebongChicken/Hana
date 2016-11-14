package com.example.hana.hana.Data;

/**
 * Created by JinHee on 2016-11-14.
 */
public class Hana {
    public String hanaId;
    public String actBranchId;
    public String albumBranchId;
    public String hanaName;
    public String hanaMemberCount;
    public String hanaNotice;
    public String hanaThumbnail;

    public Hana() {

    }

    public Hana(String hanaId,
                String actBranchId,
                String albumBranchId,
                String hanaName,
                String hanaMemberCount,
                String hanaNotice,
                String hanaThumbnail) {
        this.hanaId = hanaId;
        this.actBranchId = actBranchId;
        this.albumBranchId = albumBranchId;
        this.hanaName = hanaName;
        this.hanaMemberCount = hanaMemberCount;
        this.hanaNotice = hanaNotice;
        this.hanaThumbnail = hanaThumbnail;
    }
}
