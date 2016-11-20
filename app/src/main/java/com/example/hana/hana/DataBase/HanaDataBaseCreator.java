package com.example.hana.hana.DataBase;


/**
 * Created by Jin Hee Lee on 2016-11-20.
 */

import com.example.hana.hana.DataBase.HanaDatabase.*;

public class HanaDataBaseCreator implements DataBaseCreator {

    //
    //USER
    //
    private final String TABLE_CREATE_USER_TABLE = "CREATE TABLE" +
              UserTable.TABLE_NAME + "("
            + UserTable.COL_USER_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + UserTable.COL_USER_NAME + " TEXT,"
            + UserTable.COL_USER_PHONE + " TEXT,"
            + UserTable.COL_USER_THUMBNAIL_URL + " TEXT,"
            + UserTable.COL_HANA_ID + " INTEGER,"
            + UserTable.COL_LEVEL + " TEXT);";

    private final String INDEX_CREATE_USER_TABLE = "CREATE UNIQUE INDEX"
            + UserTable.TABLE_NAME + "_pk ON "
            + UserTable.TABLE_NAME + " (" + UserTable.COL_USER_ID + ");";
    //
    //HANA
    //
    private final String TABLE_CREATE_HANA_TABLE = "CREATE TABLE" +
              HanaTable.TABLE_NAME                + "("
            + HanaTable.COL_HANA_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + HanaTable.COL_HANA_NAME          + " TEXT,"
            + HanaTable.COL_HANA_THUMBNAIL     + " TEXT,"
            + HanaTable.COL_HANA_NOTICE        + " TEXT,"
            + HanaTable.COL_HANA_MEMBER_COUNT  + " INTEGER,"
            + HanaTable.COL_ACTBR_ID           + " INTEGER);";

    private final String INDEX_CREATE_HANA_TABLE = "CREATE UNIQUE INDEX"
            + HanaTable.TABLE_NAME + "_pk ON "
            + HanaTable.TABLE_NAME + " (" + UserTable.COL_USER_ID + ");";
    //
    //ACTBRANCH
    //
    private final String TABLE_CREATE_ACT_BRANCH_TABLE = "CREATE TABLE" +
            ActBranchTable.TABLE_NAME              + "("
            + ActBranchTable.COL_ACTBR_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + ActBranchTable.COL_ACTBR_NAME          + " TEXT,"
            + ActBranchTable.COL_TEAM_ID           + " INTEGER);";

    private final String INDEX_CREATE_ACT_BRANCH_TABLE = "CREATE UNIQUE INDEX"
            + ActBranchTable.TABLE_NAME + "_pk ON "
            + ActBranchTable.TABLE_NAME + " (" + ActBranchTable.COL_ACTBR_ID + ");";
    //
    //TEAM
    //
    private final String TABLE_CREATE_TEAM_TABLE = "CREATE TABLE" +
            TeamTable.TABLE_NAME              + "("
            + TeamTable.COL_TEAM_ID           + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + TeamTable.COL_TEAM_NAME         + " TEXT,"
            + TeamTable.COL_TEAMTDD_ID        + " INTEGER);";

    private final String INDEX_CREATE_TEAM_TABLE = "CREATE UNIQUE INDEX"
            + TeamTable.TABLE_NAME + "_pk ON "
            + TeamTable.TABLE_NAME + " (" + TeamTable.COL_TEAM_ID + ");";
    //
    //TEAMTDD
    //
    private final String TABLE_CREATE_TEAM_TDD_TABLE = "CREATE TABLE" +
            TeamTDDTable.TABLE_NAME              + "("
            + TeamTDDTable. COL_TEAMTDD_ID          + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + TeamTDDTable. COL_TEAMTDD_CONTENT     + " TEXT,"
            + TeamTDDTable. COL_TEAMTDD_STATE       + " INTEGER,"
            + TeamTDDTable. COL_COMMENTS_ID_        + " INTEGER);";

    private final String INDEX_CREATE_TEAM_TDD_TABLE = "CREATE UNIQUE INDEX"
            + TeamTDDTable.TABLE_NAME + "_pk ON "
            + TeamTDDTable.TABLE_NAME + " (" + TeamTDDTable.COL_TEAMTDD_ID + ");";
    //
    //COMMENT
    //
    private final String TABLE_CREATE_COMMENTS_TABLE = "CREATE TABLE"+
            CommentsTable.TABLE_NAME +"("
            +CommentsTable.COL_COMMENT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            +CommentsTable.COL_COMMENT_CONTENT+" TEXT);";

    private final String INDEX_CREATE_COMMENTS_TABLE = "CREATE UNIQUE INDEX"
            + CommentsTable.COL_COMMENT_ID + "_pk ON"
            + CommentsTable.COL_COMMENT_CONTENT + " ("+CommentsTable.COL_COMMENT_ID+");";

    @Override
    public String[] getCreateTableStmt() {
        return new String[] {
                TABLE_CREATE_USER_TABLE,
                TABLE_CREATE_HANA_TABLE,
                TABLE_CREATE_ACT_BRANCH_TABLE,
                TABLE_CREATE_TEAM_TABLE,
                TABLE_CREATE_TEAM_TDD_TABLE,
                TABLE_CREATE_COMMENTS_TABLE};
    }

    @Override
    public String[] getCreateIndexStmt() {
        return new String[] {
                INDEX_CREATE_USER_TABLE,
                INDEX_CREATE_HANA_TABLE,
                INDEX_CREATE_ACT_BRANCH_TABLE,
                INDEX_CREATE_TEAM_TABLE,
                INDEX_CREATE_TEAM_TDD_TABLE,
                INDEX_CREATE_COMMENTS_TABLE};
    }

    @Override
    public String[] getCreateViewStmt() {
        return null;
    }

    @Override
    public String[] getCreateTriggerStmt() {
        return null;
    }

    @Override
    public String[] getInitDataInsertStmt() {
        String[] initData = new String[2];
        String[][] initvalue = null;


        return initData;
    }
}