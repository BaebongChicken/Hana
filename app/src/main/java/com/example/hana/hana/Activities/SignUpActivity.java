package com.example.hana.hana.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.hana.hana.Data.User;
import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.R;

public class SignUpActivity extends BaseActivity {

    private android.widget.EditText signupTxt1;
    private android.widget.EditText signupTxt2;
    private android.widget.EditText signupTxt3;
    private android.widget.EditText signupTxt4;
    private android.widget.EditText signupTxt5;
    private android.widget.EditText signupTxt6;
    private android.widget.Button addBtn;
    private android.widget.ListView signuplv;
    private android.widget.RelativeLayout activitysignup;


    private DataHandling dataHandling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dataHandling = new DataHandling(getApplicationContext());
        bindView();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User mUser = new User(
                        signupTxt1.getText().toString(),
                        signupTxt2.getText().toString(),
                        signupTxt3.getText().toString(),
                        signupTxt4.getText().toString(),
                        signupTxt5.getText().toString(),
                        signupTxt6.getText().toString());
                dataHandling.insert(mUser);
            }
        });


    }

    @Override
    void bindView() {
        super.bindView();
        this.activitysignup = (RelativeLayout) findViewById(R.id.activity_sign_up);
        this.signuplv = (ListView) findViewById(R.id.signuplv);
        this.addBtn = (Button) findViewById(R.id.addBtn);
        this.signupTxt6 = (EditText) findViewById(R.id.signupTxt6);
        this.signupTxt5 = (EditText) findViewById(R.id.signupTxt5);
        this.signupTxt4 = (EditText) findViewById(R.id.signupTxt4);
        this.signupTxt3 = (EditText) findViewById(R.id.signupTxt3);
        this.signupTxt2 = (EditText) findViewById(R.id.signupTxt2);
        this.signupTxt1 = (EditText) findViewById(R.id.signupTxt1);
    }
}
