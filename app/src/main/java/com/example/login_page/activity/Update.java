package com.example.login_page.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login_page.R;
import com.example.login_page.database.MyDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class Update extends AppCompatActivity {


    Button save;
    TextInputEditText  oldnum,oldname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);


        oldname = findViewById(R.id.newname);
        oldnum = findViewById(R.id.newnum);
        save = findViewById(R.id.sav);


        String updatename = getIntent().getStringExtra("name");
        String updatenum = getIntent().getStringExtra("num");
        int cid = getIntent().getIntExtra("cid", 90);


        oldname.setText(updatename);
        oldnum.setText(updatenum);
//
//        MyDatabase db = new MyDatabase(Update.this);
//
//        db.editdata(oldname.getText().toString(), oldnum.getText().toString(), cid);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabase db = new MyDatabase(Update.this);
                db.editdata(oldname.getText().toString(), oldnum.getText().toString(), cid);

                    startActivity(new Intent(Update.this, HomePage.class).putExtra("userid", cid));
                    finish();


            }
        });

    }
}