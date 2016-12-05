package com.example.hana.hana.Activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.DataBase.HanaSQLiteOpenHelper;
import com.example.hana.hana.R;
import com.example.hana.hana.Utils.ContextUtil;

public class CreateTeamActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar customactiontoolbarcreateteam;
    private ImageView backgroundcreateteam;
    private EditText teamNameTv;
    private Button createMemberBtn;
    private FrameLayout teamBackgrounlyt;
    private LinearLayout activitycreateteam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        setCustomActionbar(R.id.custom_action_toolbar_createteam);
        bindView();
        setBackground(backgroundcreateteam);
        setOnEvents();
    }

    @Override
    public void setCustomActionbar(int toolbarId) {
        super.setCustomActionbar(toolbarId);
        okBtnActionBar.setVisibility(View.GONE);
        toggleBtnActionBar.setVisibility(View.GONE);
        titleTxt.setText("팀 추가하기");
    }

    @Override
    void bindView() {
        super.bindView();
        dataHandling = new DataHandling(getApplicationContext());
        hanaDb = new HanaSQLiteOpenHelper(getApplicationContext());
        this.activitycreateteam = (LinearLayout) findViewById(R.id.activity_create_team);
        this.teamBackgrounlyt = (FrameLayout) findViewById(R.id.teamBackgrounlyt);
        this.createMemberBtn = (Button) findViewById(R.id.createMemberBtn);
        this.teamNameTv = (EditText) findViewById(R.id.teamNameTv);
        this.backgroundcreateteam = (ImageView) findViewById(R.id.background_create_team);
        this.customactiontoolbarcreateteam = (Toolbar) findViewById(R.id.custom_action_toolbar_createteam);
    }

    @Override
    void setOnEvents() {
        super.setOnEvents();
        setCreateTeamBtn();
    }

    void setCreateTeamBtn() {
        createMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mName = teamNameTv.getText().toString();

                if (mName.equals("")) {
                    Toast.makeText(getApplicationContext(), "내용을 모두 채워주세요.", Toast.LENGTH_SHORT);
                    return;
                }
                addNewTeam(null, mName, null,ContextUtil.getLoginHana(getApplicationContext()).getHanaData(0));

                finish();
            }
        });
    }
}
