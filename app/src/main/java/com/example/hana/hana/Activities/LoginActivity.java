package com.example.hana.hana.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hana.hana.Constants.Constants;
import com.example.hana.hana.Data.State;
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
    State state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        findKeyHash();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        bindView();
        init();
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


    }

    @Override
    void bindView() {
        super.bindView();
        dataHandling = new DataHandling(getApplicationContext());
        hanaDb = new HanaSQLiteOpenHelper(getApplicationContext());

    }


    void init() {
        if (dataHandling.getListUser().size()!=0) {
            ContextUtil.setLoggedIn(getApplicationContext(), true);
            Log.d(Constants.LOG_TAG,dataHandling.getListUser().get(dataHandling.getListUser().size()-1).getUserData(1)+"<-LoginUser(firstUser)");
            ContextUtil.setLoginUser(getApplicationContext(), dataHandling.getListUser().get(dataHandling.getListUser().size()-1));
            ContextUtil.setLoginHana(getApplicationContext(), dataHandling.getHanaById("1"));
        }

    }

    private void startProperActivity() {

        if (ContextUtil.isUserLoggedin(LoginActivity.this)) {
            if (ContextUtil.getLoginUserId(getApplicationContext()) != null && ContextUtil.getLoginHanaId(getApplicationContext()) != null) {
                Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            } else if(ContextUtil.getLoginUserId(getApplicationContext()) != null){
                Intent mIntent = new Intent(LoginActivity.this, CreateHanaActivity.class);
                startActivity(mIntent);
                finish();
            }
        } else {
            Log.d(Constants.LOG_TAG, "We need to sign up");
        }
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
                                if (!ContextUtil.isUserLoggedin(getApplicationContext())) {
                                    String mId = object.getString("id");
                                    String mName = object.getString("name");
                                    String mThumbNail = "https://graph.facebook.com/" + object.getString("id") + "/picture?type=normal";

                                    Log.d(Constants.LOG_TAG, "new User ADD");

                                    ContextUtil.setLoggedIn(LoginActivity.this, true);
//                                    state = new State("true", mId, "null");
//                                    dataHandling.insert(state);
                                    User mUser = addNewUser(mId, mName, "", mThumbNail, "", "ADMIN");
                                    ContextUtil.setLoginUser(LoginActivity.this, mUser);
                                    ContextUtil.setLoginUserId(LoginActivity.this, mUser);

                                    Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.isUserLoggedin(LoginActivity.this));
                                    Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginUser(LoginActivity.this));
                                    Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginUserId(LoginActivity.this));
                                    Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginHana(LoginActivity.this));
                                    Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginHanaId(LoginActivity.this));

                                    Intent mIntent = new Intent(LoginActivity.this, CreateHanaActivity.class);
                                    startActivity(mIntent);
                                    finish();
                                } else {
                                    ContextUtil.setLoggedIn(getApplicationContext(), false);
                                }
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
