package com.example.hana.hana.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hana.hana.Adapters.TDDAdapter;
import com.example.hana.hana.Constants.Constants;
import com.example.hana.hana.Data.Team;
import com.example.hana.hana.Data.TeamTDD;
import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.DataBase.HanaSQLiteOpenHelper;
import com.example.hana.hana.R;
import com.example.hana.hana.Utils.ContextUtil;

import java.util.ArrayList;


public class TDDActivity extends BaseActivity {

    private String currentTeamId;
    private Team currentTeam;
    private android.widget.ImageView backgroundtdd;
    private android.support.v7.widget.Toolbar customactiontoolbartdd;
    private android.widget.TextView todoTab;
    private android.widget.TextView todoIndicator;
    private android.widget.TextView doingTab;
    private android.widget.TextView doingIndicator;
    private android.widget.TextView doneTab;
    private android.widget.TextView doneIndicator;
    private android.widget.LinearLayout TabLayouttdd;
    private android.widget.ListView todolv;
    private android.widget.Button todoAddBtn;
    private android.widget.LinearLayout todoLayout;
    private android.widget.ListView doinglv;
    private android.widget.LinearLayout doingLayout;
    private android.widget.ListView donelv;
    private android.widget.LinearLayout doneLayout;
    private android.support.v4.view.ViewPager tddViewPager;
    private android.widget.FrameLayout activitytdd;

    private ArrayList<TeamTDD> todoArrayList = new ArrayList<>();
    private ArrayList<TeamTDD> doingArrayList = new ArrayList<>();
    private ArrayList<TeamTDD> doneArrayList = new ArrayList<>();
    private ArrayList<TeamTDD> wholeTddArrayList = new ArrayList<>();
    private TDDAdapter tddAdapterTodo;
    private TDDAdapter tddAdapterDoing;
    private TDDAdapter tddAdapterDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdd);
        bindView();
        setBackground(this.backgroundtdd, ContextUtil.getHanaImagePath(TDDActivity.this));

        setCustomActionbar(R.id.custom_action_toolbar_tdd);
        setValues();
        setOnEvents();
    }

    @Override
    public void onResume() {
        super.onResume();
        setTddArrayList();
    }

    @Override
    void bindView() {
        super.bindView();
        dataHandling = new DataHandling(getApplicationContext());
        hanaDb = new HanaSQLiteOpenHelper(getApplicationContext());

        this.currentTeamId = ContextUtil.getLoginTeamId(TDDActivity.this);
        Log.d(Constants.LOG_TAG, "Current Team Id : " + currentTeamId);
        this.currentTeam = dataHandling.getTeamById(currentTeamId);
        this.titleTxt = (TextView) findViewById(R.id.titleTxt);
        this.activitytdd = (FrameLayout) findViewById(R.id.activity_tdd);
        this.tddViewPager = (ViewPager) findViewById(R.id.tddViewPager);
        this.doneLayout = (LinearLayout) findViewById(R.id.doneLayout);
        this.donelv = (ListView) findViewById(R.id.donelv);
        this.doingLayout = (LinearLayout) findViewById(R.id.doingLayout);
        this.doinglv = (ListView) findViewById(R.id.doinglv);
        this.todoLayout = (LinearLayout) findViewById(R.id.todoLayout);
        this.todoAddBtn = (Button) findViewById(R.id.todoAddBtn);
        this.todolv = (ListView) findViewById(R.id.todolv);
        this.TabLayouttdd = (LinearLayout) findViewById(R.id.TabLayout_tdd);
        this.doneIndicator = (TextView) findViewById(R.id.doneIndicator);
        this.doneTab = (TextView) findViewById(R.id.doneTab);
        this.doingIndicator = (TextView) findViewById(R.id.doingIndicator);
        this.doingTab = (TextView) findViewById(R.id.doingTab);
        this.todoIndicator = (TextView) findViewById(R.id.todoIndicator);
        this.todoTab = (TextView) findViewById(R.id.todoTab);
        this.customactiontoolbartdd = (Toolbar) findViewById(R.id.custom_action_toolbar_tdd);
        this.backgroundtdd = (ImageView) findViewById(R.id.background_tdd);
        currentTeamId = ContextUtil.getLoginTeamId(TDDActivity.this);
    }


    @Override
    void setValues() {
        super.setValues();

        TDDViewPagerAdapter tddViewPagerAdapter = new TDDViewPagerAdapter();
        tddViewPager.setAdapter(tddViewPagerAdapter);
        tddViewPager.setOffscreenPageLimit(2);
        tddViewPager = (ViewPager) findViewById(R.id.tddViewPager);
    }

    @Override
    public void setCustomActionbar(int toolbarId) {
        super.setCustomActionbar(toolbarId);
        titleTxt.setText(currentTeam.getTeamData(1));
        toggleBtnActionBar.setVisibility(View.GONE);
    }

    @Override
    void setOnEvents() {
        super.setOnEvents();
        //tab
        todoTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tddViewPager.setCurrentItem(0);
            }
        });

        doingTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tddViewPager.setCurrentItem(1);
            }
        });

        doneTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tddViewPager.setCurrentItem(2);
            }
        });
        //indicators
        tddViewPager.clearOnPageChangeListeners();
        tddViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        todoAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDialogWithEditText();
            }
        });

        setTddArrayList();

    }

    void makeDialogWithEditText() {

        final EditText edittext = new EditText(TDDActivity.this);

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        DialogInterface.OnClickListener submitListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addNewTDD(null, edittext.getText().toString(), "todo", currentTeamId);
                onResume();
                dialog.dismiss();
            }
        };
        new AlertDialog.Builder(TDDActivity.this)
                .setView(edittext)
                .setTitle("To do 추가하기\n")
                .setPositiveButton("취소", cancelListener)
                .setNegativeButton("입력", submitListener)
                .show();

    }

    void setTddArrayList() {

        wholeTddArrayList = dataHandling.getListTeamTDDByTeamId(currentTeamId);
        todoArrayList.clear();
        doingArrayList.clear();
        doneArrayList.clear();
        int i;
        for (i = 0; i < wholeTddArrayList.size(); i++) {
            TeamTDD currentTdd = wholeTddArrayList.get(i);
            switch (currentTdd.getTeamTddData(2)) {
                case "todo":
                    todoArrayList.add(currentTdd);
                    break;
                case "doing":
                    doingArrayList.add(currentTdd);
                    break;
                case "done":
                    doneArrayList.add(currentTdd);
                    break;
            }
        }
        wholeTddArrayList.clear();

        tddAdapterTodo = new TDDAdapter(TDDActivity.this, getLayoutInflater(), todoArrayList);
        tddAdapterDoing = new TDDAdapter(TDDActivity.this, getLayoutInflater(), doingArrayList);
        tddAdapterDone = new TDDAdapter(TDDActivity.this, getLayoutInflater(), doneArrayList);
        todolv.setAdapter(tddAdapterTodo);
        doinglv.setAdapter(tddAdapterDoing);
        donelv.setAdapter(tddAdapterDone);
        setListViewHeightBasedOnItems(todolv);
        setListViewHeightBasedOnItems(doinglv);
        setListViewHeightBasedOnItems(donelv);
    }

    private void setIndicators(int pos) {
        switch (pos) {
            case 0:
                todoIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_active));
                doingIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));
                doneIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));

                todoTab.setTextColor(getResources().getColor(R.color.colorPrimaryTextInv));
                doingTab.setTextColor(getResources().getColor(R.color.colorThirdText));
                doneTab.setTextColor(getResources().getColor(R.color.colorThirdText));

                break;
            case 1:
                todoIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));
                doingIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_active));
                doneIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));

                todoTab.setTextColor(getResources().getColor(R.color.colorThirdText));
                doingTab.setTextColor(getResources().getColor(R.color.colorPrimaryTextInv));
                doneTab.setTextColor(getResources().getColor(R.color.colorThirdText));

                break;
            case 2:
                todoIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));
                doingIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_inactive));
                doneIndicator.setBackgroundColor(getResources().getColor(R.color.indicator_active));

                todoTab.setTextColor(getResources().getColor(R.color.colorThirdText));
                doingTab.setTextColor(getResources().getColor(R.color.colorThirdText));
                doneTab.setTextColor(getResources().getColor(R.color.colorPrimaryTextInv));

                break;
        }
    }

    class TDDViewPagerAdapter extends PagerAdapter {

        public Object instantiateItem(View collection, int position) {

            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.id.todoLayout;
                    break;
                case 1:
                    resId = R.id.doingLayout;
                    break;
                case 2:
                    resId = R.id.doneLayout;
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
