package com.example.login_page.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login_page.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePage extends AppCompatActivity {

    TextView name;

    ListView list;

    FloatingActionButton add,pop;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_page);


        add = findViewById(R.id.add);
        list = findViewById(R.id.list);
        pop = findViewById(R.id.pop);


        int userid = getIntent().getIntExtra("id",10);

        list.setAdapter(new MyAdapter(this,userid));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(HomePage.this, Addcontact.class).putExtra("id",userid));
                finish();


            }
        });

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu pmenu = new PopupMenu(HomePage.this,pop);

                pmenu.inflate(R.menu.mymenu);
                pmenu.show();

                pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getItemId() == R.id.logout)
                        {
                            Dialog dialog = new Dialog(HomePage.this);
                            dialog.setContentView(R.layout.dialogview);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.show();

                            TextView tex = dialog.findViewById(R.id.tex);
                            Button yes = dialog.findViewById(R.id.yes);
                            Button no = dialog.findViewById(R.id.no);

                            tex.getText();

                            yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    SpaceScreen.edit.putBoolean("status", false);
                                    SpaceScreen.edit.apply();

                                    startActivity(new Intent(HomePage.this,SignIn.class));
                                    finish();
                                }
                            });

                            no.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    dialog.dismiss();
                                }
                            });
                        }
                        else if(item.getItemId() == R.id.setting)
                        {
                            Toast.makeText(HomePage.this,"setting",Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
            }
        });









    }
}