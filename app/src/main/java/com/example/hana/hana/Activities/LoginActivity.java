package com.example.hana.hana.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hana.hana.Constants.Constants;
import com.example.hana.hana.Data.User;
import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.DataBase.HanaSQLiteOpenHelper;
import com.example.hana.hana.R;
import com.example.hana.hana.Utils.ContextUtil;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONObject;

import java.util.Arrays;


public class LoginActivity extends BaseActivity {

    CallbackManager callbackManager;
    HanaSQLiteOpenHelper hanaDb;
    private DataHandling dataHandling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        dataHandling = new DataHandling(getApplicationContext());
        hanaDb = new HanaSQLiteOpenHelper(getApplicationContext());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        startProperActivity();

        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        LoginButton facebookLoginButton = (LoginButton) findViewById(R.id.facebook_login_button);


        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions = {"public_profile", "user_friends", "email"};
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList(permissions));
            }
        });

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        Log.i(Constants.LOG_TAG, "Facebook Login Success");
//                        Log.i("JinHee", "User ID: " + loginResult.getAccessToken().getUserId());
//                        Log.i("JinHee", "Auth Token: " + loginResult.getAccessToken().getToken());
                        submit(loginResult);

                    }

                    @Override
                    public void onCancel() {
                        Log.w("JinHee", "Cancel");

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.e("JinHee", "Error", error);

                    }
                }

        );


        /* key Hash 값 찾기
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
        }*/


    }

    private void startProperActivity() {
        if (ContextUtil.isUserLoggedin(getApplicationContext())) {
            Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mIntent);
            finish();
        } else {
            Log.d(Constants.LOG_TAG, "We need to sign up");
        }
    }

    private void addNewUser(String userId, String userName, String userPhone, String userThumbnailURL, String hanaId, String level) {
        User user = new User(userId, userName, userPhone, userThumbnailURL, hanaId, level);
        dataHandling.insert(user);
    }


    private void submit(final LoginResult loginResult) {
        GraphRequest request;

        request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        AccessToken.setCurrentAccessToken(loginResult.getAccessToken());
                        try {
                            Log.d("GraphResponse", object.toString());
                            try {

                                String mId = object.getString("id");
                                String mName = object.getString("name");
                                String mThumbNail = "https://graph.facebook.com/" + object.getString("id") + "/picture?type=normal";
                                addNewUser(mId, mName, "", mThumbNail, "", "ADMIN");
                                ContextUtil.setLoggedIn(getApplicationContext(), true);
                                Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(mIntent);
                                finish();
                            } catch (Exception e) {
                                Log.e(Constants.LOG_TAG, "error");

                            }

                        } catch (Exception e) {
                            Log.e(Constants.LOG_TAG, "error");

                        }
                    }
                }
        );

        Bundle params = new Bundle();
        params.putString("fields", "id,name,email,gender");
        request.setParameters(params);
        request.executeAsync();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


    }
}
