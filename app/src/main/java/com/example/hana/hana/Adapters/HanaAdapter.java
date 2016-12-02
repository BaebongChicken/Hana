package com.example.hana.hana.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hana.hana.Data.Hana;
import com.example.hana.hana.Data.User;
import com.example.hana.hana.R;

import java.util.ArrayList;

/**
 * Created by JinHee on 2016-11-28.
 */

public class HanaAdapter extends BaseAdapter {
    ArrayList<Hana> datas;
    LayoutInflater inflater;

    public HanaAdapter(LayoutInflater inflater, ArrayList<Hana> datas) {
        this.datas = datas;
        this.inflater = inflater;
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
        int mId = Integer.parseInt(datas.get(position).getHanaData(0));
        return mId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
        }
//        TextView userId = (TextView) convertView.findViewById(R.id.userId);
//        TextView userName = (TextView) convertView.findViewById(R.id.usertv1);
//        TextView userPhone = (TextView) convertView.findViewById(R.id.usertv2);
//        TextView userURL = (TextView) convertView.findViewById(R.id.usertv3);
//        TextView userHana = (TextView) convertView.findViewById(R.id.usertv4);
//        TextView userLevel = (TextView) convertView.findViewById(R.id.usertv5);
//
//        userId.setText(datas.get(position).getHanaData(0));
//        userName.setText(datas.get(position).getHanaData(1));
//        userPhone.setText(datas.get(position).getHanaData(2));
//        userURL.setText(datas.get(position).getHanaData(3));
//        userHana.setText(datas.get(position).getHanaData(4));
//        userLevel.setText(datas.get(position).getHanaData(5));

        return convertView;
    }

}
