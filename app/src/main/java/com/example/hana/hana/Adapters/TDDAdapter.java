package com.example.hana.hana.Adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.hana.hana.Activities.TDDActivity;
import com.example.hana.hana.Data.TeamTDD;
import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.R;

import java.util.ArrayList;

/**
 * Created by Jin Hee Lee on 2016-12-05.
 */

public class TDDAdapter extends BaseAdapter {
    ArrayList<TeamTDD> datas;
    LayoutInflater inflater;
    private Context context;

    public TDDAdapter(Context context, LayoutInflater inflater, ArrayList<TeamTDD> datas) {
        this.datas = datas;
        this.inflater = inflater;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        long mId = Long.parseLong(datas.get(position).getTeamTddData(0));
        return mId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_tddview, parent, false);
        }
        TextView tddContentView = (TextView) convertView.findViewById(R.id.tddContentView);
        FrameLayout prevBtn = (FrameLayout) convertView.findViewById(R.id.prevBtn);
        FrameLayout nextBtn = (FrameLayout) convertView.findViewById(R.id.nextBtn);

        tddContentView.setText(datas.get(position).getTeamTddData(1));
        final DataHandling dataHandling = new DataHandling(context);
        final TeamTDD teamTDD = dataHandling.getTeamTddById(datas.get(position).getTeamTddData(0));
//        TeamTDD mTeamTDD = dataHandling.getTeamTddById(datas.get(position).getTeamTddData(0));


        switch (dataHandling.getTeamTddById(datas.get(position).getTeamTddData(0)).getTeamTddData(2)) {

            case "todo":

                prevBtn.setVisibility(View.INVISIBLE);

                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamTDD.setTeamTddData(2, "doing");
                        dataHandling.update(teamTDD);
                        ((TDDActivity) context).onResume();
                    }
                });
                break;
            case "doing":
                prevBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamTDD.setTeamTddData(2, "todo");
                        dataHandling.update(teamTDD);
                        ((TDDActivity) context).onResume();

                    }
                });

                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamTDD.setTeamTddData(2, "done");
                        dataHandling.update(teamTDD);
                        ((TDDActivity) context).onResume();

                    }
                });
                break;
            case "done":
                prevBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamTDD.setTeamTddData(2, "doing");
                        dataHandling.update(teamTDD);
                        ((TDDActivity) context).onResume();

                    }
                });

                nextBtn.setVisibility(View.INVISIBLE);
                break;
        }


        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                DialogInterface.OnClickListener deleteListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dataHandling.delete(teamTDD, Long.parseLong(teamTDD.getTeamTddData(0)));
                        ((TDDActivity) context).onResume();
                        dialog.dismiss();
                    }
                };
                new AlertDialog.Builder(context)
                        .setTitle("해당 활동을 삭제하시겠습니까?")
                        .setPositiveButton("취소", cancelListener)
                        .setNegativeButton("삭제", deleteListener)
                        .show();
                return false;
            }
        });

        return convertView;
    }
}
