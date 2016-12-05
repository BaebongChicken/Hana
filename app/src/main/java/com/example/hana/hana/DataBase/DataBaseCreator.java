package com.example.hana.hana.DataBase;

/**
 * Created by Jin Hee Lee on 2016-11-20.
 */

public interface DataBaseCreator {

    public static final String DB_NAME="hana_db";
    public static final int DB_VERSION=2;

    //Definition Statement
    public String[] getCreateTableStmt();
    public String[] getCreateIndexStmt();
    public String[] getCreateViewStmt();
    public String[] getCreateTriggerStmt();
    public String[] getInitDataInsertStmt();
}
