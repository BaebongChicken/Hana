package com.example.hana.hana.DataBase;

/**
 * Created by Jin Hee Lee on 2016-11-20.
 */

public class HanaDatabase {
    HanaDatabase() {
    }

    public static final class UserTable {
        private UserTable() {
        }

        public static final String TABLE_NAME = "USER";

        public static final String COL_USER_ID = "userId";
        public static final String COL_USER_NAME = "userName";
        public static final String COL_USER_PHONE = "userPhone";
        public static final String COL_USER_THUMBNAIL_URL = "userThumbnailURL";
        public static final String COL_HANA_ID = "hanaId";
        public static final String COL_LEVEL = "level";

        public static String[] getColumnNames() {
            String[] COLUMNS =
                    {
                            COL_USER_ID, COL_USER_NAME, COL_USER_PHONE,
                            COL_USER_THUMBNAIL_URL, COL_HANA_ID, COL_LEVEL
                    };
            return COLUMNS;
        }

        public static int getColumnCount() {
            return getColumnNames().length;
        }


    }

    public static final class HanaTable {
        private HanaTable() {
        }

        public static final String TABLE_NAME = "HANA";

        public static final String COL_HANA_ID = "hanaId";
        public static final String COL_HANA_NAME = "hanaName";
        public static final String COL_HANA_THUMBNAIL = "hanaThumbnail";
        public static final String COL_HANA_LEVELLIST = "hanaLevelList";



        public static String[] getColumnNames() {
            String[] COLUMNS =
                    {
                            COL_HANA_ID,
                            COL_HANA_NAME,
                            COL_HANA_THUMBNAIL,
                            COL_HANA_LEVELLIST
                    };
            return COLUMNS;
        }

        public static int getColumnCount() {
            return getColumnNames().length;
        }
    }

//    public static final class ActBranchTable {
//        private ActBranchTable() {
//        }
//
//        public static final String TABLE_NAME               = "ACT_BRANCH";
//        public static final String COL_ACTBR_ID             = "actBranchId";
//        public static final String COL_ACTBR_NAME           = "actBranchName";
//        public static final String COL_TEAM_ID              = "teamId";
//
//
//        public static String[] getColumnNames() {
//            String[] COLUMNS =
//                    {
//                            COL_ACTBR_ID,
//                            COL_ACTBR_NAME,
//                            COL_TEAM_ID
//                    };
//            return COLUMNS;
//        }
//    }

    public static final class TeamTable {
        private TeamTable() {
        }

        public static final String TABLE_NAME = "TEAM";
        public static final String COL_TEAM_ID = "teamId";
        public static final String COL_TEAM_NAME = "teamName";
        public static final String COL_TEAM_MEMBER_ID = "memberId";
        public static final String COL_HANA_ID = "hanaId";


        public static String[] getColumnNames() {
            String[] COLUMNS =
                    {
                            COL_TEAM_ID,
                            COL_TEAM_NAME,
                            COL_TEAM_MEMBER_ID,
                            COL_HANA_ID
                    };
            return COLUMNS;
        }

        public static int getColumnCount() {
            return getColumnNames().length;
        }
    }

    public static final class TeamTDDTable {
        private TeamTDDTable() {
        }

        public static final String TABLE_NAME = "TEAMTDD";
        public static final String COL_TEAMTDD_ID = "teamTddId";
        public static final String COL_TEAMTDD_CONTENT = "content";
        public static final String COL_TEAMTDD_STATE = "state";
        public static final String COL_TEAM_ID = "teamId";


        public static String[] getColumnNames() {
            String[] COLUMNS =
                    {
                            COL_TEAMTDD_ID,
                            COL_TEAMTDD_CONTENT,
                            COL_TEAMTDD_STATE,
                            COL_TEAM_ID
                    };
            return COLUMNS;
        }

        public static int getColumnCount() {
            return getColumnNames().length;
        }
    }
//
//    public static final class CommentsTable {
//        private CommentsTable() {
//        }
//
//        public static final String TABLE_NAME               = "COMMENTS";
//        public static final String COL_COMMENT_ID           = "commentId";
//        public static final String COL_COMMENT_CONTENT      = "content";
//
//
//
//        public static String[] getColumnNames() {
//            String[] COLUMNS =
//                    {
//                           COL_COMMENT_ID,
//                           COL_COMMENT_CONTENT,
//                    };
//            return COLUMNS;
//        }
//    }


}
