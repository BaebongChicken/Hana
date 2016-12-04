package com.example.hana.hana.Activities;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hana.hana.Constants.Constants;
import com.example.hana.hana.R;
import com.example.hana.hana.Utils.BackPressCloseHandler;
import com.example.hana.hana.Utils.ContextUtil;

public class MainActivity extends BaseActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCustomActionbar(R.id.custom_action_toolbar_main);
        bindView();
        setValues();
        setOnEvents();
    }


    @Override
    void bindView() {
        super.bindView();
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
    }

    @Override
    void setValues() {
        super.setValues();
        Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.isUserLoggedin(MainActivity.this));
        Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginUser(MainActivity.this));
        Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginUserId(MainActivity.this));
        Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginHana(MainActivity.this));
        Log.d(Constants.LOG_TAG, "start state : " + ContextUtil.getLoginHanaId(MainActivity.this));

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter();
        mainViewPager.setAdapter(mainViewPagerAdapter);
        mainViewPager.setOffscreenPageLimit(2);
        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);

    }

    @Override
    void setOnEvents() {
        super.setOnEvents();
        //title
        int index = 0;
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
    void setIndicators(int pos) {
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
