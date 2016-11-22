package com.example.hana.hana.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by JinHee on 2016-11-07.
 */

public class BaseActivity extends AppCompatActivity {

    void bindView() {
        verifyStoragePermissions(this);
    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
//    public void sqliteExport() {
//        Log.v(Constants.LOG_TAG, "sqlite EXPROTING");
//        verifyStoragePermissions(this);
//        try {
//
//            File sdDir = Environment.getExternalStorageDirectory();
//            File dataDir = Environment.getDataDirectory();
//            Log.v(Constants.LOG_TAG, Environment.getExternalStorageState()+"/"+sdDir.canWrite()+"/"+sdDir.canRead());
//            Log.v(Constants.LOG_TAG, getPackageName());
//            if (sdDir.canWrite()) {
//                String crDBPath = "//data//com.example.hana.hana/databases//hana_db.db";
//                String bkDBPath = "hana_db.sqlite";
//
//
//                File crDB = new File(dataDir, crDBPath);
//                File bkDB = new File(sdDir, bkDBPath);
//                Log.v(Constants.LOG_TAG, "bk DB 경로 : "+bkDB.getPath()+"***bkDB/crDB Exist? "+bkDB.exists()+"/"+crDB.exists());
//
//                if (crDB.exists()) {
//                    Log.v(Constants.LOG_TAG, "cr DB EXISTS");
//
//                    FileChannel src = new FileInputStream(crDB).getChannel();
//                    FileChannel dst = new FileOutputStream(bkDB).getChannel();
//
//                    dst.transferFrom(src, 0, src.size());
//
//                    src.close();
//
//                    dst.close();
//
//                }
//
//                if(bkDB.exists()){
//
//                    Toast.makeText(this, "DB file Export Success!", Toast.LENGTH_SHORT).show();
//
//                }
//
//
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//    }

}
