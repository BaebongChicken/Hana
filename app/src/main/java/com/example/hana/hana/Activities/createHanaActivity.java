package com.example.hana.hana.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hana.hana.Constants.Constants;
import com.example.hana.hana.Data.Hana;
import com.example.hana.hana.Data.User;
import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.DataBase.HanaSQLiteOpenHelper;
import com.example.hana.hana.R;
import com.example.hana.hana.Utils.ContextUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CreateHanaActivity extends BaseActivity {


    private ImageView background;
    private android.widget.EditText hanaNameTv;
    private ImageView hanaThumbnailIv;
    private android.widget.Button createHanaBtn;
    private android.widget.LinearLayout activitycreatehana;
    private FrameLayout addPhotolyt;
    //camera
    private Uri mImageCaptureUri;
    private Bitmap hanaPhoto;
    public String hanaPhotoFull_path;
    private String hanaPhotoName;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_IMAGE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hana);

        setCustomActionbar(R.id.custom_action_toolbar_createhana);
        bindView();
        setBackground(background,ContextUtil.getHanaImagePath(CreateHanaActivity.this));
        setOnEvents();


    }

    @Override
    public void setCustomActionbar(int toolbarId) {
        super.setCustomActionbar(toolbarId);
        okBtnActionBar.setVisibility(View.GONE);
        toggleBtnActionBar.setVisibility(View.GONE);
        titleTxt.setText("하나 만들기");
    }

    @Override
    void bindView() {
        super.bindView();
        dataHandling = new DataHandling(getApplicationContext());
        hanaDb = new HanaSQLiteOpenHelper(getApplicationContext());
        this.activitycreatehana = (LinearLayout) findViewById(R.id.activity_create_hana);
        this.createHanaBtn = (Button) findViewById(R.id.createHanaBtn);
        this.hanaThumbnailIv = (ImageView) findViewById(R.id.hanaThumbnailIv);
        this.hanaNameTv = (EditText) findViewById(R.id.hanaNameTv);
        this.addPhotolyt = (FrameLayout) findViewById(R.id.addPhotoLyt);
        this.background = (ImageView) findViewById(R.id.background_hana);
    }

    @Override
    void setOnEvents() {
        super.setOnEvents();
        setProfileThumbnailEvent();
        setCreateHanaBtn();
    }

    void setCreateHanaBtn() {
        createHanaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mName = hanaNameTv.getText().toString();
                String mUri = hanaPhotoFull_path;
                if (mName.equals("") || mUri == null) {
                    Toast.makeText(getApplicationContext(), "이름 또는 대표사진이 없습니다.", Toast.LENGTH_SHORT);
                    return;
                }
                addNewHana(null, mName, mUri, "");
                Hana mHana = dataHandling.getListHana().get(dataHandling.getListHana().size()-1);
                User mUser = ContextUtil.getLoginUser(CreateHanaActivity.this);
                mUser.setUserData(4, mUser.getUserData(4)+"/"+String.valueOf(mHana.getHanaData(0)));
                ContextUtil.setLoginUser(CreateHanaActivity.this, mUser);
                ContextUtil.setLoginUser(CreateHanaActivity.this, mUser);
                ContextUtil.setLoginHana(CreateHanaActivity.this, mHana);
                ContextUtil.setLoginHanaId(CreateHanaActivity.this, mHana);
//                State state = new State("true",mUser.getUserData(0),mHana.getHanaData(0));
//                dataHandling.update(state);
                dataHandling.update(mUser);
                Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.isUserLoggedin(CreateHanaActivity.this));
                Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginUser(CreateHanaActivity.this));
                Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginUserId(CreateHanaActivity.this));
                Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginHana(CreateHanaActivity.this));
                Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginHanaId(CreateHanaActivity.this));

                Intent intent = new Intent(CreateHanaActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void setProfileThumbnailEvent() {
        addPhotolyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakePhotoAction();
                    }
                };

                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                new AlertDialog.Builder(CreateHanaActivity.this)
                        .setTitle("업로드 할 이미지 선택")
                        .setPositiveButton("직접 촬영", cameraListener)
                        .setNeutralButton("앨범에서 불러오기", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();

            }
        });


    }

    void doTakePhotoAction() {

        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/hanatmp");
        if (!file.isDirectory()) {
            file.mkdir();
        }

        mImageCaptureUri = createSaveCropFile();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);

    }

    void doTakeAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case PICK_FROM_ALBUM: {
                mImageCaptureUri = data.getData();

                File original_file = getImageFile(mImageCaptureUri);
                mImageCaptureUri = createSaveCropFile();
                File copy_file = new File(mImageCaptureUri.getPath());
                copyFile(original_file, copy_file);
            }

            case PICK_FROM_CAMERA: {

                calcWindowWidthHeight();
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("outputX", windowWidth);
                intent.putExtra("outputY", windowHeight);
                intent.putExtra("aspectX", windowWidth);
                intent.putExtra("aspectY", windowHeight);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
//                intent.putExtra("output", mImageCaptureUri);
                startActivityForResult(intent, CROP_FROM_IMAGE);
                break;
            }
            case CROP_FROM_IMAGE: {
                if (resultCode != RESULT_OK) {
                    return;
                }
                String full_path = mImageCaptureUri.getPath();
                hanaPhotoFull_path = mImageCaptureUri.getPath();
                ContextUtil.setHanaImagePath(CreateHanaActivity.this,hanaPhotoFull_path);
                hanaPhoto = BitmapFactory.decodeFile(full_path);
                ImageLoader.getInstance().displayImage("file://" + full_path, hanaThumbnailIv);

            }

        }
    }

    /**
     * @param : Crop된 이미지가 저장될 파일을 만든다.선언된 url로 파일이 네이밍되며, 선언된 uri에 파일이 저장된다.
     * @return : Uri
     * @author : pppdw
     */

    private Uri createSaveCropFile() {
        Uri uri;
        String url = "hanatmp" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        hanaPhotoName = url;
        uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + "/hanatmp/", url));

        return uri;
    }

    /**
     * @param "사진경로를 얻는다,Uri가 널이되는 경우엔, 가장최근(라스트인덱스) 사진을 가지고온다."
     * @return File (해당사진)
     * @author pppdw
     */
    private File getImageFile(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        if (uri == null) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        Cursor mCursor = getContentResolver().query(uri, projection, null, null,
                MediaStore.Images.Media.DATE_MODIFIED + " desc");
        if (mCursor == null || mCursor.getCount() < 1) {
            return null; // no cursor or no record
        }
        int column_index = mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        mCursor.moveToFirst();

        String path = mCursor.getString(column_index);

        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }

        return new File(path);
    }

    /**
     * @return
     * @author pppdw
     * @description 크롭을 위해 사진을 복사한다.
     */
    public static boolean copyFile(File srcFile, File destFile) {
        boolean result = false;
        try {
            InputStream in = new FileInputStream(srcFile);
            try {
                result = copyToFile(in, destFile);
            } finally {
                in.close();
            }
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    /**
     * @author : pppdw
     * @description : DestFile을 소스스트림에 복사한다 (데이터밸류)
     */

    private static boolean copyToFile(InputStream inputStream, File destFile) {
        try {
            OutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }


    }
}
