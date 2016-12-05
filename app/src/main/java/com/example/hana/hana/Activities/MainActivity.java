package com.example.hana.hana.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hana.hana.Adapters.MemberAdapter;
import com.example.hana.hana.Adapters.TeamAdapter;
import com.example.hana.hana.Constants.Constants;
import com.example.hana.hana.Data.Team;
import com.example.hana.hana.Data.User;
import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.DataBase.HanaSQLiteOpenHelper;
import com.example.hana.hana.R;
import com.example.hana.hana.Utils.BackPressCloseHandler;
import com.example.hana.hana.Utils.ContextUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    //main
    private DrawerLayout dlactivitymaindrawer;
    private LinearLayout drawLayout;
    private BackPressCloseHandler backPressCloseHandler;
    private ViewPager mainViewPager;
    private Toolbar custom_action_toolbar_main;
    private TextView mainTab;
    private TextView mainIndicator;
    private TextView actTab;
    private TextView actIndicator;
    private TextView memberTab;
    private TextView memberIndicator;
    private LinearLayout TabLayout;
    private LinearLayout mainLayout;
    private LinearLayout actLayout;
    private LinearLayout memberLayout;
    private TextView myProfileTxt;
    private LinearLayout profileLayout;
    private TextView mainTextView;
    private ListView actlv;
    private ListView memberlv;
    private ArrayList<Team> teamArrayList;
    private ArrayList<User> memberArrayList;
    private Button teamAddBtn;

    private ImageView background;
    //member
    private Button memberAddBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCustomActionbar(R.id.custom_action_toolbar_main);
        bindView();
        setValues();
        setBackground(this.background, ContextUtil.getHanaImagePath(MainActivity.this));
        setOnEvents();
    }


    @Override
    public void onResume() {
        super.onResume();
        setMemberArrayList();
        setTeamArrayList();
    }

    @Override
    void bindView() {
        super.bindView();
        dataHandling = new DataHandling(getApplicationContext());
        hanaDb = new HanaSQLiteOpenHelper(getApplicationContext());
        this.mainTextView = (TextView)findViewById(R.id.mainTextView);
        this.mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        this.dlactivitymaindrawer = (DrawerLayout) findViewById(R.id.dl_activity_main_drawer);
        this.drawLayout = (LinearLayout) findViewById(R.id.drawLayout);
        this.backPressCloseHandler = new BackPressCloseHandler(this);
        this.profileLayout = (LinearLayout) findViewById(R.id.profileLayout);
        this.myProfileTxt = (TextView) findViewById(R.id.myProfileTxt);
        this.memberLayout = (LinearLayout) findViewById(R.id.memberLayout);
        this.actLayout = (LinearLayout) findViewById(R.id.actLayout);
        this.mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        this.TabLayout = (LinearLayout) findViewById(R.id.TabLayout);
        this.memberIndicator = (TextView) findViewById(R.id.memberIndicator);
        this.memberTab = (TextView) findViewById(R.id.memberTab);
        this.actIndicator = (TextView) findViewById(R.id.actIndicator);
        this.actTab = (TextView) findViewById(R.id.actTab);
        this.mainIndicator = (TextView) findViewById(R.id.mainIndicator);
        this.mainTab = (TextView) findViewById(R.id.mainTab);
        this.background = (ImageView) findViewById(R.id.background);
        //teamAct
        this.actlv = (ListView) findViewById(R.id.actlv);
        this.teamArrayList = new ArrayList<Team>();
        this.teamAddBtn = (Button) findViewById(R.id.teamAddBtn);
        //member
        this.memberlv = (ListView) findViewById(R.id.memberlv);
        this.memberArrayList = new ArrayList<User>();
        this.memberAddBtn = (Button) findViewById(R.id.memberAddBtn);

    }

    @Override
    void setValues() {
        super.setValues();
        Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.isUserLoggedin(MainActivity.this));
        Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginUser(MainActivity.this));
        Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginUserId(MainActivity.this));
        Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginHana(MainActivity.this));
        Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginHanaId(MainActivity.this));
        mainTextView.setText(""+ContextUtil.getLoginUser(MainActivity.this).getUserData(1)+"님! \nHANA에 오신 것을 환영합니다!");
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter();
        mainViewPager.setAdapter(mainViewPagerAdapter);
        mainViewPager.setOffscreenPageLimit(2);
        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);

    }

    @Override
    void setOnEvents() {
        super.setOnEvents();
        //title
        String currentHanaName = ContextUtil.getLoginHana(MainActivity.this).getHanaData(1);
        titleTxt.setText(currentHanaName);
        //tab
        mainTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewPager.setCurrentItem(0);
            }
        });

        actTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewPager.setCurrentItem(1);
            }
        });

        memberTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewPager.setCurrentItem(2);
            }
        });
        //indicator
        mainViewPager.clearOnPageChangeListeners();
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        memberAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateMemberActivity.class);
                startActivity(intent);
            }
        });
        teamAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateTeamActivity.class);
                startActivity(intent);
            }
        });
        setMemberArrayList();
        setTeamArrayList();
    }


    @Override
    public void onBackPressed() {
//뒤로가기 버튼 눌렸을 때
        if (dlactivitymaindrawer.isDrawerOpen(drawLayout)) {
            dlactivitymaindrawer.closeDrawer(drawLayout);
        } else {
            backPressCloseHandler.onBackPressed();
        }

    }


    void setMemberArrayList() {
        memberArrayList = dataHandling.getListUser();

        MemberAdapter memberAdapter = new MemberAdapter(MainActivity.this, getLayoutInflater(), memberArrayList);
        memberlv.setAdapter(memberAdapter);
        setListViewHeightBasedOnItems(memberlv);
    }

    void setTeamArrayList() {
        teamArrayList = dataHandling.getListTeam();

        TeamAdapter teamAdapter = new TeamAdapter(MainActivity.this, getLayoutInflater(), teamArrayList);
        actlv.setAdapter(teamAdapter);
        setListViewHeightBasedOnItems(actlv);

    }

    void openOrCloseSideMenu() {
//왼쪽 사이드바 열고 닫는 함수
        if (dlactivitymaindrawer.isDrawerOpen(drawLayout)) {
            dlactivitymaindrawer.closeDrawer(drawLayout);
        } else {
            dlactivitymaindrawer.openDrawer(drawLayout);
        }
    }

    @Override
    public void setCustomActionbar(int toolbarId) {
        super.setCustomActionbar(toolbarId);
        toggleBtnActionBar.setOnClickListener(new View.OnClickListener() {
            //왼쪽 사이드바 열고 닫는 버튼
            @Override
            public void onClick(View v) {
                openOrCloseSideMenu();
            }
        });

    }


    //함수화 시킨것들
    private void setIndicators(int pos) {
        switch (pos) {
            case 0:
                mainIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_active));
                actIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));
                memberIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));

                mainTab.setTextColor(getResources().getColor(R.color.colorPrimaryTextInv));
                actTab.setTextColor(getResources().getColor(R.color.colorThirdText));
                memberTab.setTextColor(getResources().getColor(R.color.colorThirdText));

                break;
            case 1:
                mainIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));
                actIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_active));
                memberIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));

                mainTab.setTextColor(getResources().getColor(R.color.colorThirdText));
                actTab.setTextColor(getResources().getColor(R.color.colorPrimaryTextInv));
                memberTab.setTextColor(getResources().getColor(R.color.colorThirdText));

                break;
            case 2:
                mainIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));
                actIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));
                memberIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_active));

                mainTab.setTextColor(getResources().getColor(R.color.colorThirdText));
                actTab.setTextColor(getResources().getColor(R.color.colorThirdText));
                memberTab.setTextColor(getResources().getColor(R.color.colorPrimaryTextInv));

                break;
        }
    }

    class MainViewPagerAdapter extends PagerAdapter {

        public Object instantiateItem(View collection, int position) {

            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.id.mainLayout;
                    break;
                case 1:
                    resId = R.id.actLayout;
                    break;
                case 2:
                    resId = R.id.memberLayout;
                    break;
            }
            return findViewById(resId);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }
    }
}
