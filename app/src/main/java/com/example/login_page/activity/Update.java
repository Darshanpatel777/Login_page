package com.example.login_page.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login_page.R;
import com.example.login_page.database.MyDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class Update extends AppCompatActivity {


    Button save,cancel;
    TextInputEditText  oldnum,oldname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);


        oldname = findViewById(R.id.newname);
        oldnum = findViewById(R.id.newnum);
        save = findViewById(R.id.sav);
        cancel = findViewById(R.id.cancel);


        String updatename = getIntent().getStringExtra("name");
        String updatenum = getIntent().getStringExtra("num");
        int cid = getIntent().getIntExtra("cid", 90);


        oldname.setText(updatename);
        oldnum.setText(updatenum);

        int userid = getIntent().getIntExtra("userid", 50);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabase db = new MyDatabase(Update.this);
                db.editdata(oldname.getText().toString(), oldnum.getText().toString(), cid);



                    startActivity(new Intent(Update.this, HomePage.class)
                            .putExtra("userid", userid));
                    finish();


            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Update.this, HomePage.class)
                        .putExtra("userid", userid));
                finish();
            }
        });

    }
}