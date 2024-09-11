package com.example.login_page.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_page.R;
import com.example.login_page.database.MyDatabase;

import java.util.ArrayList;
import java.util.Comparator;

public class MyAdapter extends BaseAdapter {


    ArrayList<ModelClass> datalist = new ArrayList<>();
    Context context;
    int uid;

    MyAdapter(Context context, int uid) {
        this.context = context;
        this.uid = uid;

        MyDatabase db = new MyDatabase(context);
        Cursor cr = db.selectcon(uid);

        while (cr.moveToNext()) {
            ModelClass d = new ModelClass();
            d.setName(cr.getString(2));
            d.setNum(cr.getString(3));
            d.setId(cr.getInt(0));
            datalist.add(d);
        }


        // contact list name alphabet ma aava mate
        ArrayList<String> namelist = new ArrayList<>();
        for (int i = 0; i < datalist.size(); i++) {
            namelist.add(datalist.get(i).getName());
        }
        namelist.sort(Comparator.naturalOrder());

        ArrayList<ModelClass> temp = new ArrayList<>();
        for (int i = 0; i < datalist.size(); i++) {
            for (int j = 0; j < datalist.size(); j++) {
                if (namelist.get(i) == datalist.get(j).getName()) {
                    temp.add(datalist.get(j));
                    break;
                }
            }
        }
        datalist = temp;
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


        View vv = LayoutInflater.from(context).inflate(R.layout.myview, parent, false);

        TextView name = vv.findViewById(R.id.sname), num = vv.findViewById(R.id.snum);

        name.setText(datalist.get(position).getName());
        num.setText(datalist.get(position).getNum());


        // date edit-delete karava mate & complete thay gaya  bad main page ma aava mate
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                context.startActivity(new Intent(context, Update.class).
                        putExtra("name", name.getText()).
                        putExtra("num", num.getText()).
                        putExtra("cid", datalist.get(position).getId()).
                        putExtra("userid", uid));


            }
        });


        return vv;
    }
}
