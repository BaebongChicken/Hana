package com.example.hana.hana.Activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.DataBase.HanaSQLiteOpenHelper;
import com.example.hana.hana.R;
import com.example.hana.hana.Utils.ContextUtil;

public class CreateMemberActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar customactiontoolbarcreatemember;
    private ImageView background;
    private EditText memberIdTv;
    private EditText memberNameTv;
    private EditText memberPhoneTv;
    private EditText memberLevelTv;
    private Button createMemberBtn;
    private LinearLayout activitycreatemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_member);
        setCustomActionbar(R.id.custom_action_toolbar_createmember);
        bindView();
        setBackground(this.background);
        setOnEvents();
    }

    @Override
    public void setCustomActionbar(int toolbarId) {
        super.setCustomActionbar(toolbarId);
        okBtnActionBar.setVisibility(View.GONE);
        toggleBtnActionBar.setVisibility(View.GONE);
        titleTxt.setText("멤버 추가하기");
    }

    @Override
    void bindView() {
        super.bindView();
        dataHandling = new DataHandling(getApplicationContext());
        hanaDb = new HanaSQLiteOpenHelper(getApplicationContext());
        this.activitycreatemember = (LinearLayout) findViewById(R.id.activity_create_member);
        this.createMemberBtn = (Button) findViewById(R.id.createMemberBtn);
        this.memberLevelTv = (EditText) findViewById(R.id.memberLevelTv);
        this.memberPhoneTv = (EditText) findViewById(R.id.memberPhoneTv);
        this.memberNameTv = (EditText) findViewById(R.id.memberNameTv);
        this.memberIdTv = (EditText) findViewById(R.id.memberIdTv);
        this.background = (ImageView) findViewById(R.id.background_main);
        this.customactiontoolbarcreatemember = (Toolbar) findViewById(R.id.custom_action_toolbar_createmember);
    }

    @Override
    void setOnEvents() {
        super.setOnEvents();
        setCreateMemberBtn();
    }

    void setCreateMemberBtn() {
        createMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mId = memberIdTv.getText().toString();
                String mName = memberNameTv.getText().toString();
                String mPhone = memberPhoneTv.getText().toString();
                String mLevel = memberLevelTv.getText().toString();
                if (mName.equals("") || mId.equals("") || mPhone.equals("") || mLevel.equals("")) {
                    Toast.makeText(getApplicationContext(), "내용을 모두 채워주세요.", Toast.LENGTH_SHORT);
                    return;
                }
                addNewUser(mId, mName, mPhone, "", ContextUtil.getLoginHanaId(CreateMemberActivity.this), mLevel);

                finish();
            }
        });
    }
}
