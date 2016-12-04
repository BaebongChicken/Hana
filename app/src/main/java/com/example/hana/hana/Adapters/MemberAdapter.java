package com.example.hana.hana.Adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hana.hana.Data.User;
import com.example.hana.hana.R;

import java.util.ArrayList;

/**
 * Created by Jin Hee Lee on 2016-12-05.
 */

public class MemberAdapter extends BaseAdapter {
    ArrayList<User> datas;
    LayoutInflater inflater;
    private Context context;
    public MemberAdapter(Context context, LayoutInflater inflater, ArrayList<User> datas) {
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
        long mId = Long.parseLong(datas.get(position).getUserData(0));
        return mId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_memberview, parent, false);
        }
        TextView memberId = (TextView) convertView.findViewById(R.id.memberId);
        TextView memberName = (TextView) convertView.findViewById(R.id.memberName);
        TextView memberPhone = (TextView) convertView.findViewById(R.id.memberPhone);
        TextView memberLevel = (TextView) convertView.findViewById(R.id.memberLevel);
        ImageView memberCall = (ImageView) convertView.findViewById(R.id.memberCall);

        memberId.setText(datas.get(position).getUserData(0));
        memberName.setText(datas.get(position).getUserData(1));
        memberPhone.setText(datas.get(position).getUserData(2));
        memberLevel.setText(datas.get(position).getUserData(5));
        memberCall.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        memberCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+datas.get(position).getUserData(2)));
                context.startActivity(intent);
            }
        });
        if (memberLevel.getText().toString().equals("ADMIN")){
            memberPhone.setVisibility(View.GONE);
            memberId.setVisibility(View.GONE);
            memberCall.setVisibility(View.GONE);
            memberLevel.setTextColor(Color.parseColor("#FF5588"));
        }
        return convertView;
    }
}
