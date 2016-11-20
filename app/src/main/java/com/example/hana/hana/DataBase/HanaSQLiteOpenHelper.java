package com.example.hana.hana.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hana.hana.Constants.Constants;

/**
 * Created by Jin Hee Lee on 2016-11-20.
 */

public class HanaSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String CLASSNAME = HanaSQLiteOpenHelper.class.getSimpleName();
    private static final String KEY_COLUMN = "Id";

    private static HanaSQLiteOpenHelper mInstance;
    private static SQLiteDatabase db;

    /*
    Constructor
    @param (app context, db name, cursor factory, db version)
    */
    public HanaSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d(Constants.LOG_TAG, HanaSQLiteOpenHelper.CLASSNAME + " Create or Open DB : " + name);
    }

        /*
        Constructor
        @param (app context)
        */

    public HanaSQLiteOpenHelper(final Context context) {
        super(context, DataBaseCreator.DB_NAME, null, DataBaseCreator.DB_VERSION);
        Log.d(Constants.LOG_TAG, HanaSQLiteOpenHelper.CLASSNAME + " Create or Open DB : " +
                DataBaseCreator.DB_NAME);
    }

    /*
     initialize
     @param (app context)
    */
    private static void initialize(Context context) {
        if (mInstance == null) {
            Log.d(Constants.LOG_TAG, HanaSQLiteOpenHelper.CLASSNAME
                    + "Try to create instance of database ("
                    + DataBaseCreator.DB_NAME + ")");
            mInstance = new HanaSQLiteOpenHelper(context);

            try {
                Log.d(Constants.LOG_TAG, "Creating or Opening the database (" + DataBaseCreator.DB_NAME + ").");
                db = mInstance.getWritableDatabase();
            } catch (SQLiteException e) {
                Log.e(Constants.LOG_TAG, "Could not create and/or Onpen the database (" + DataBaseCreator.DB_NAME +
                        ") that will be used for reading and writing.");
            }
            Log.d(Constants.LOG_TAG, HanaSQLiteOpenHelper.CLASSNAME + "instance of databse (" + DataBaseCreator.DB_NAME + ") created.");
        }
    }

        /*
        Static method for getting <singleton> instance
        @param (app context)
        @return (singleton instance)
        */

    public static final HanaSQLiteOpenHelper getInstance(Context context) {
        initialize(context);
        return mInstance;
    }

         /*
        Method of closing DB and make Instance to null
         */

    public void close() {
        if (mInstance != null) {
            Log.d(Constants.LOG_TAG, HanaSQLiteOpenHelper.CLASSNAME + " Closing the DB [" +
                    DataBaseCreator.DB_NAME + "]....");
            db.close();
            mInstance = null;
        }
    }

    /*
   Method of selecting table
   db.query wrapper
   @param (table name, column name array)
   @return (cursor)
    */
    public Cursor get(String table, String[] columns) {
        return db.query(table, columns, null, null, null, null, null);
    }
      /*
        Method of selecting table

        @param (table name, column name array, record id(primary column name은 "Id"만 가능)
        @return (cursor)
       */

    public Cursor get(String table, String[] columns, long id) {
        Cursor cursor = db.query(true, table, columns, KEY_COLUMN + "=" + id, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

        /*
        Method of select statements(SQL)

        @param (SQL statements)
        @return (cursor)
        */

    public Cursor get(String SQL) {
        return db.rawQuery(SQL, null);
    }
        /*
        Method to insert record

        @param (table name, ContentValues instance)
        @return (long)
         */

    public long insert(String table, ContentValues values) {
        return db.insert(table, null, values);
    }

    /*
    Method to update record

    @param (table name, ContentValues instance, record id)
    @return (int)
     */
    public int update(String table, ContentValues values, long id) {
        return db.update(table, values, KEY_COLUMN + "=" + id, null);
    }

    /*
    Method to update record

    @param (table name, ContentValues instance, where Clause)
    @return (int)
     */
    public int update(String table, ContentValues values, String whereClause) {
        return db.update(table, values, whereClause, null);
    }

    /*
    Method to delete record

    @param (table name, Where Clause)
    @return (int)
     */
    public int delete(String table, String whereClause) {
        return db.delete(table, whereClause, null);
    }

    /*
    Method to delete record

    @param (table name, record id)
    @return (int)
     */
    public int delete(String table, long id) {
        return db.delete(table, KEY_COLUMN + "=" + id, null);
    }

    /*
    Method to run SQL
    @param (SQL)
     */
    public void exec(String SQL) {
        db.execSQL(SQL);
    }

    /*
    Method to Logging the resulting that returned by Cursor
     */
    public void logCursorInfo(Cursor c) {
        Log.d(Constants.LOG_TAG, "***** CURSOR BEGIN *****\n" + "Results : " + c.getCount() + "\n Columns : " + c.getColumnCount());
        int i;
        //Column name printing
        String rowHeaders = "|| ";
        for (i = 0; i < c.getColumnCount(); i++) {
            rowHeaders = rowHeaders.concat(c.getColumnName(i) + " || ");
        }

        Log.d(Constants.LOG_TAG, "COLUMNS NAME : " + rowHeaders);

        //Record Print
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            String rowResults = "|| ";
            for (i = 0; i < c.getColumnCount(); i++) {
                rowResults = rowResults.concat(c.getString(i) + " || ");
            }

            Log.d(Constants.LOG_TAG, "ROW [" + c.getPosition() + "] : " + rowResults);
            c.moveToNext();
        }

        Log.d(Constants.LOG_TAG, "***** CURSOR END *****");
    }

    /*
    Method to create DB
    데이트베이스 최초 한번만 생성
    @param (SQLLiteDataBase instance)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        DataBaseCreator mCreator = new HanaDataBaseCreator();
        String[] tableCreateStmt = mCreator.getCreateTableStmt();
        String[] indexCreateStmt = mCreator.getCreateIndexStmt();
        String[] initDataDml = mCreator.getInitDataInsertStmt();
        int i;
        try {
            if (tableCreateStmt != null & tableCreateStmt.length > 0) {
                Log.d(Constants.LOG_TAG, HanaSQLiteOpenHelper.CLASSNAME + " - onCreate() : Table Creation");
                for (i = 0; i < tableCreateStmt.length; i++) {
                    db.execSQL(tableCreateStmt[i]);
                }
            }

            if (indexCreateStmt != null & indexCreateStmt.length > 0) {
                Log.d(Constants.LOG_TAG, HanaSQLiteOpenHelper.CLASSNAME + " - onCreate() : Index Creation");
                for (i = 0; i < indexCreateStmt.length; i++) {
                    db.execSQL(indexCreateStmt[i]);
                }
            }

            if (initDataDml != null & initDataDml.length > 0) {
                Log.d(Constants.LOG_TAG, HanaSQLiteOpenHelper.CLASSNAME + " - onCreate() : initData Load");
                for (i = 0; i < initDataDml.length; i++) {
                    db.execSQL(initDataDml[i]);
                }
            }

        } catch (SQLException e) {
            Log.e(Constants.LOG_TAG, HanaSQLiteOpenHelper.CLASSNAME + " - on Create() : Table Creation Error", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(Constants.LOG_TAG, HanaSQLiteOpenHelper.CLASSNAME+" - onUpgrade() : Table Upgrade Action");

    }
}
