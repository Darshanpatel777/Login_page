package com.example.login_page.activity;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_page.R;
import com.example.login_page.database.MyDatabase;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {


    ArrayList<ModelClass> datalist = new ArrayList<>();

    Context context;

    MyAdapter(Context context,int uid) {
        this.context = context;

        MyDatabase db = new MyDatabase(context);
        Cursor cr = db.selectcon(uid);

        while (cr.moveToNext())
        {
            ModelClass d = new ModelClass();
            d.setName(cr.getString(2));
            d.setNum(cr.getString(3));
            datalist.add(d);
        }
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View vv = LayoutInflater.from(context).inflate(R.layout.myview,parent,false);

        TextView name = vv.findViewById(R.id.sname) , num = vv.findViewById(R.id.snum);

        name.setText(datalist.get(position).getName());
        num.setText(datalist.get(position).getNum());

        return vv;
    }
}
