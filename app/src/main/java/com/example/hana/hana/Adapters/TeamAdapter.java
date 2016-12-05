package com.example.hana.hana.Adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hana.hana.Activities.MainActivity;
import com.example.hana.hana.Activities.TDDActivity;
import com.example.hana.hana.Data.Team;
import com.example.hana.hana.DataBase.DataHandling;
import com.example.hana.hana.R;
import com.example.hana.hana.Utils.ContextUtil;

import java.util.ArrayList;

/**
 * Created by Jin Hee Lee on 2016-12-05.
 */

public class TeamAdapter extends BaseAdapter {
    ArrayList<Team> datas;
    LayoutInflater inflater;
    private Context context;

    public TeamAdapter(Context context, LayoutInflater inflater, ArrayList<Team> datas) {
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
        long mId = Long.parseLong(datas.get(position).getTeamData(0));
        return mId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_teamview, parent, false);
        }
        TextView teamId = (TextView) convertView.findViewById(R.id.teamId);
        TextView teamName = (TextView) convertView.findViewById(R.id.teamName);


        teamId.setText(datas.get(position).getTeamData(0));
        teamName.setText(datas.get(position).getTeamData(1));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtil.setLoginTeamId(context, datas.get(position).getTeamData(0) );
                Intent intent = new Intent(context, TDDActivity.class);
                context.startActivity(intent);
            }
        });
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
                        DataHandling dataHandling = new DataHandling(context);
                        dataHandling.delete(dataHandling.getTeamById(datas.get(position).getTeamData(0)), Long.parseLong(datas.get(position).getTeamData(0)));
                        ((MainActivity) context).onResume();
                        dialog.dismiss();
                    }
                };
                new AlertDialog.Builder(context)
                        .setTitle("해당 팀을 삭제하시겠습니까?")
                        .setPositiveButton("취소", cancelListener)
                        .setNegativeButton("삭제", deleteListener)
                        .show();
                return false;
            }
        });

        return convertView;
    }
}
