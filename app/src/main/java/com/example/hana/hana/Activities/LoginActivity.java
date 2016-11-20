package com.example.hana.hana.Activities;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.hana.hana.Data.User;
import com.example.hana.hana.R;
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

import java.net.URL;
import java.util.Arrays;


public class LoginActivity extends BaseActivity {

    CallbackManager callbackManager;
    TextView name;
    TextView email;
    String user_id = "";
    URL user_thumbnail_url = null;
    ImageView user_thumbnail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        // [START initialize_auth]
        // Initialize Firebase Auth


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        setContentView(R.layout.activity_login);
        name = (TextView) findViewById(R.id.textview1);
        email = (TextView) findViewById(R.id.textview2);
        user_thumbnail = (ImageView) findViewById(R.id.thumbnail);
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

                        Log.i("JinHee", "User ID: " + loginResult.getAccessToken().getUserId());
                        Log.i("JinHee", "Auth Token: " + loginResult.getAccessToken().getToken());
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


        /*try {
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

    private void addNewUser(String userId, String userName, String userPhone, String userThumbnailURL,String hanaId,String level){
        User user = new User(userId, userName, userPhone, userThumbnailURL, hanaId, level);
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

                                name.setText(object.getString("name"));
                                email.setText(object.getString("email"));
                                user_id = object.getString("id");

                                ImageLoader imageLoader = ImageLoader.getInstance();

                                user_thumbnail_url = new URL("https://graph.facebook.com/" + user_id + "/picture?type=normal");
                                imageLoader.displayImage(user_thumbnail_url.toString(), user_thumbnail);

                            } catch (Exception e) {
                                Log.e("jinhee","error");

                                Log.e("jinhee","error");
                            }

                        } catch (Exception e) {
                            Log.e("jinhee","error");

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