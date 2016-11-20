package com.example.hana.hana.DataBase;

import android.content.Context;
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
    private static final String KEY_COLUMN = "_id";

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
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
