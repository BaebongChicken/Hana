package com.example.hana.hana.DataBase;


/**
 * Created by Jin Hee Lee on 2016-11-20.
 */

import com.example.hana.hana.DataBase.HanaDatabase.HanaTable;
import com.example.hana.hana.DataBase.HanaDatabase.TeamTDDTable;
import com.example.hana.hana.DataBase.HanaDatabase.TeamTable;
import com.example.hana.hana.DataBase.HanaDatabase.UserTable;

public class HanaDataBaseCreator implements DataBaseCreator {

    //
    //USER
    //
    private final String TABLE_CREATE_USER_TABLE = "CREATE TABLE " +
            UserTable.TABLE_NAME + "("
//            + UserTable.COL_USER_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + UserTable.COL_USER_ID + " INTEGER NOT NULL PRIMARY KEY, "
            + UserTable.COL_USER_NAME + " TEXT,"
            + UserTable.COL_USER_PHONE + " TEXT,"
            + UserTable.COL_USER_THUMBNAIL_URL + " TEXT,"
            + UserTable.COL_HANA_ID + " TEXT,"
            + UserTable.COL_LEVEL + " TEXT);";

    private final String INDEX_CREATE_USER_TABLE = "CREATE UNIQUE INDEX "
            + UserTable.TABLE_NAME + "_pk ON "
            + UserTable.TABLE_NAME + " (" + UserTable.COL_USER_ID + ");";
    //
    //HANA
    //
    private final String TABLE_CREATE_HANA_TABLE = "CREATE TABLE " +
            HanaTable.TABLE_NAME + "("
            + HanaTable.COL_HANA_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + HanaTable.COL_HANA_NAME + " TEXT,"
            + HanaTable.COL_HANA_THUMBNAIL + " TEXT,"
            + HanaTable.COL_HANA_LEVELLIST + " TEXT);";

    private final String INDEX_CREATE_HANA_TABLE = "CREATE UNIQUE INDEX "
            + HanaTable.TABLE_NAME + "_pk ON "
            + HanaTable.TABLE_NAME + " (" + UserTable.COL_HANA_ID + ");";
    //    //
//    //ACTBRANCH
//    //
//    private final String TABLE_CREATE_ACT_BRANCH_TABLE = "CREATE TABLE" +
//            ActBranchTable.TABLE_NAME + "("
//            + ActBranchTable.COL_ACTBR_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
//            + ActBranchTable.COL_ACTBR_NAME + " TEXT,"
//            + ActBranchTable.COL_TEAM_ID + " INTEGER);";
//
//    private final String INDEX_CREATE_ACT_BRANCH_TABLE = "CREATE UNIQUE INDEX"
//            + ActBranchTable.TABLE_NAME + "_pk ON "
//            + ActBranchTable.TABLE_NAME + " (" + ActBranchTable.COL_ACTBR_ID + ");";
    //
    //TEAM
    //
    private final String TABLE_CREATE_TEAM_TABLE = "CREATE TABLE " +
            TeamTable.TABLE_NAME + "("
            + TeamTable.COL_TEAM_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + TeamTable.COL_TEAM_NAME + " TEXT,"
            + TeamTable.COL_HANA_ID + " INTEGER);";

    private final String INDEX_CREATE_TEAM_TABLE = "CREATE UNIQUE INDEX "
            + TeamTable.TABLE_NAME + "_pk ON "
            + TeamTable.TABLE_NAME + " (" + TeamTable.COL_TEAM_ID + ");";
    //
    //TEAMTDD
    //
    private final String TABLE_CREATE_TEAM_TDD_TABLE = "CREATE TABLE " +
            TeamTDDTable.TABLE_NAME + "("
            + TeamTDDTable.COL_TEAMTDD_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + TeamTDDTable.COL_TEAMTDD_CONTENT + " TEXT,"
            + TeamTDDTable.COL_TEAMTDD_STATE + " INTEGER,"
            + TeamTDDTable.COL_TEAM_ID + " INTEGER);";

    private final String INDEX_CREATE_TEAM_TDD_TABLE = "CREATE UNIQUE INDEX "
            + TeamTDDTable.TABLE_NAME + "_pk ON "
            + TeamTDDTable.TABLE_NAME + " (" + TeamTDDTable.COL_TEAMTDD_ID + ");";
    //
    //state Table
    //
    private final String STATE_TABLE = "CREATE TABLE STATE(" +
            "STATE_ID INTEGER NOT NULL," +
            "USER_LOGIN TEXT," +
            "CURRENT_USER TEXT," +
            "CURRENT_HANA TEXT" +
            ");";
//    //
//    //COMMENT
//    //
//    private final String TABLE_CREATE_COMMENTS_TABLE = "CREATE TABLE" +
//            CommentsTable.TABLE_NAME + "("
//            + CommentsTable.COL_COMMENT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
//            + CommentsTable.COL_COMMENT_CONTENT + " TEXT);";
//
//    private final String INDEX_CREATE_COMMENTS_TABLE = "CREATE UNIQUE INDEX"
//            + CommentsTable.COL_COMMENT_ID + "_pk ON"
//            + CommentsTable.COL_COMMENT_CONTENT + " (" + CommentsTable.COL_COMMENT_ID + ");";

    @Override
    public String[] getCreateTableStmt() {
        return new String[]{
                TABLE_CREATE_USER_TABLE,
                TABLE_CREATE_HANA_TABLE,
//                TABLE_CREATE_ACT_BRANCH_TABLE,
                TABLE_CREATE_TEAM_TABLE,
                TABLE_CREATE_TEAM_TDD_TABLE,
//                TABLE_CREATE_COMMENTS_TABLE
                STATE_TABLE
        };
    }

    @Override
    public String[] getCreateIndexStmt() {
        return new String[]{
                INDEX_CREATE_USER_TABLE,
                INDEX_CREATE_HANA_TABLE,
//                INDEX_CREATE_ACT_BRANCH_TABLE,
                INDEX_CREATE_TEAM_TABLE,
                INDEX_CREATE_TEAM_TDD_TABLE,
//                INDEX_CREATE_COMMENTS_TABLE
        };
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
//        String[] init = {"INSERT INTO STATE VALUES('false','null','null');"};

        return null;
//        int i;
//
//        String[][] initUserValues = {
//                {"0", "이코딩", "01000001111", null, "0", "admin"},
//                {"1", "염클론", "01022223333", null, "0", "member"},
//                {"2", "이용우", "01033334444", null, "1", "admin"},
//                {"3", "이겐주", "01012345124", null, "1", "member"},
//                {"4", "개문녕", "01012355882", null, "1", "member"},
//        };
//        String[][] initHanaValues = {
//                {
//                        "0", "소프트웨어실습", null,""
//                },
//                {
//                        "1", "컴퓨터네트워크", null,""
//                }
//        };
////        String[] initData = new String[4];
////        String[] initStateValue = {"1","false","null","null"};
////        for(i=0; i<initStateValue.length;i++){
////            initData[i] = "INSERT INTO STATE VALUES("
////        }
//        String[] initData = new String[initUserValues.length + initHanaValues.length];
//        Log.v(Constants.LOG_TAG, "init Data Length :"+initData.length);
//        for (i = 0; i < initUserValues.length; i++) {
////            Log.v(Constants.LOG_TAG, "i  :"+i);
//
//            initData[i] = "INSERT INTO " + UserTable.TABLE_NAME
//                    + " VALUES("
//                    + "'" + initUserValues[i][0] + "',"
//                    + "'" + initUserValues[i][1] + "',"
//                    + "'" + initUserValues[i][2] + "',"
//                    + "'" + initUserValues[i][3] + "',"
//                    + "'" + initUserValues[i][4] + "',"
//                    + "'" + initUserValues[i][5] + "'"
//                    + ");";
//        }
//
//        for (i = initUserValues.length; i < (initUserValues.length + initHanaValues.length); i++) {
////            Log.v(Constants.LOG_TAG, "i  :"+i);
//
//            initData[i] = "INSERT INTO " + HanaTable.TABLE_NAME
//                    + " VALUES("
//                    + "'" + initHanaValues[i-initUserValues.length][0] + "',"
//                    + "'" + initHanaValues[i-initUserValues.length][1] + "',"
//                    + "'" + initHanaValues[i-initUserValues.length][2] + "'"
//                    + ");";
//        }
//
//        return initData;

    }
}
