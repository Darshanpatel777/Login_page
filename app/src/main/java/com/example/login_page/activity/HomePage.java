package com.example.login_page.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login_page.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePage extends AppCompatActivity {

    TextView name;

    ListView list;

    FloatingActionButton add ,back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_page);


        add = findViewById(R.id.add);
        list = findViewById(R.id.list);
        back = findViewById(R.id.back);


        int userid = getIntent().getIntExtra("id",10);


        list.setAdapter(new MyAdapter(this,userid));



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(HomePage.this, Addcontact.class).putExtra("id",userid));
                finish();


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SpaceScreen.edit.putBoolean("status", true);
                SpaceScreen.edit.apply();

                startActivity(new Intent(HomePage.this,SignIn.class));
                finish();

            }
        });

    }
}