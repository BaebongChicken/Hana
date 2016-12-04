package com.example.hana.hana.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hana.hana.Data.Hana;
import com.example.hana.hana.Data.User;
import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.DataBase.HanaSQLiteOpenHelper;
import com.example.hana.hana.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by JinHee on 2016-11-07.
 */

public class BaseActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.INTERNET,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public TextView titleTxt;
    public android.widget.LinearLayout titleLayout;
    public ImageButton toggleBtnActionBar;
    public Button okBtnActionBar;
    public HanaSQLiteOpenHelper hanaDb;
    public DataHandling dataHandling;
    public static int windowWidth;
    public static int windowHeight;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    void bindView() {
        verifyStoragePermissions(this);

    }

    void setValues() {

    }

    void setOnEvents() {

    }
// Storage Permissions

    void calcWindowWidthHeight() {
        Display mDispaly = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        mDispaly.getSize(size);
        windowWidth = size.x;
        windowHeight = size.y;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//                ,
//                ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE),
//                ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA),
//                ActivityCompat.checkSelfPermission(activity, Manifest.permission.INTERNET),
//                ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)


        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );

        }
    }

    public void setCustomActionbar(int toolbarId) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.layout_custom_actionbar, null);


        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        this.okBtnActionBar = (Button) getSupportActionBar().getCustomView().findViewById(R.id.okBtnActionBar);
        this.toggleBtnActionBar = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.toggleBtnActionBar);
        this.titleLayout = (LinearLayout) getSupportActionBar().getCustomView().findViewById(R.id.titleLayout);
        this.titleTxt = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.titleTxt);

    }

    public void setListViewHeightBasedOnItems(ListView listView) {

        // Get list adpter of listview;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;

        int numberOfItems = listAdapter.getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = listAdapter.getView(itemPos, null, listView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }

        // Get total height of all item dividers.
        int totalDividersHeight = listView.getDividerHeight() * (numberOfItems - 1);

        // Set list height.
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    void findKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.hana.hana", //앱의 패키지 명
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }


    }

    void setBackground(ImageView background) {

        String imgUri = "drawable://" + R.drawable.background_sea;
        ImageLoader.getInstance().displayImage(imgUri, background, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Drawable drawable = new BitmapDrawable(loadedImage);
                view.setBackground(drawable);
                ((ImageView) view).setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    void setBackground(ImageView background, String imgUri) {

        ImageLoader.getInstance().displayImage("file://"+imgUri, background, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Drawable drawable = new BitmapDrawable(loadedImage);
                view.setBackground(drawable);
                ((ImageView) view).setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    //add function
    protected User addNewUser(String userId, String userName, String userPhone, String userThumbnailURL, String hanaId, String level) {
        User user = new User(userId, userName, userPhone, userThumbnailURL, hanaId, level);
        dataHandling.insert(user);
        return user;
    }

    protected Hana addNewHana(String hanaId, String hanaName, String hanaThumbnail, String hanaLevelList) {
        Hana hana = new Hana(hanaId, hanaName, hanaThumbnail, hanaLevelList);
        dataHandling.insert(hana);
        return hana;

    }
}
