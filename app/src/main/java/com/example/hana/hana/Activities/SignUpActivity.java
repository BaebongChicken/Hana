package com.example.hana.hana.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.hana.hana.Adapters.UserAdapter;
import com.example.hana.hana.Constants.Constants;
import com.example.hana.hana.Data.User;
import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.R;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.Arrays;

public class SignUpActivity extends BaseActivity {

    private android.widget.EditText signupTxt1;
    private android.widget.EditText signupTxt2;
    private android.widget.EditText signupTxt3;
    private android.widget.EditText signupTxt4;
    private android.widget.EditText signupTxt5;
    private android.widget.EditText signupTxt6;
    private android.widget.Button addBtn;
    private android.widget.ListView signuplv;
    private android.widget.LinearLayout activitysignup;
    private android.widget.Button checkBtn;

    private DataHandling dataHandling;
    private ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dataHandling = new DataHandling(getApplicationContext());
        userArrayList = new ArrayList<User>();
        bindView();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User mUser = new User(
                        null,
                        signupTxt2.getText().toString(),
                        signupTxt3.getText().toString(),
                        signupTxt4.getText().toString(),
                        signupTxt5.getText().toString(),
                        signupTxt6.getText().toString());
                dataHandling.insert(mUser);
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userArrayList = dataHandling.getListUser();
                Log.d(Constants.LOG_TAG,Arrays.toString(userArrayList.toArray()));
                UserAdapter mAdapter= new UserAdapter(getLayoutInflater(),userArrayList);
                signuplv.setAdapter(mAdapter);
                setListViewHeightBasedOnItems(signuplv);
            }
        });

    }

    @Override
    void bindView() {
        super.bindView();
        this.activitysignup = (LinearLayout) findViewById(R.id.activity_sign_up);
        this.signuplv = (ListView) findViewById(R.id.signuplv);
        this.addBtn = (Button) findViewById(R.id.addBtn);
        this.checkBtn = (Button) findViewById(R.id.checkBtn);
        this.signupTxt6 = (EditText) findViewById(R.id.signupTxt6);
        this.signupTxt5 = (EditText) findViewById(R.id.signupTxt5);
        this.signupTxt4 = (EditText) findViewById(R.id.signupTxt4);
        this.signupTxt3 = (EditText) findViewById(R.id.signupTxt3);
        this.signupTxt2 = (EditText) findViewById(R.id.signupTxt2);
        this.signupTxt1 = (EditText) findViewById(R.id.signupTxt1);
    }
}
