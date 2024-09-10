package com.example.login_page.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login_page.R;
import com.example.login_page.database.MyDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class SignIn extends AppCompatActivity {

    TextView signup;
    Button loginuser;
    TextInputEditText username;
    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signup = findViewById(R.id.signup);
        loginuser = findViewById(R.id.loginuser);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        MyDatabase db = new MyDatabase(SignIn.this);

        loginuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor data = db.userlogin(username.getText().toString(), password.getText().toString());

                while (data.moveToNext()) {
                    Log.d("====d====", "onClick: id ==> " + data.getInt(0));
                    Log.d("====d====", "onClick: name ==> " + data.getString(1));
                    Log.d("====d====", "onClick: email ==> " + data.getString(2));


                    SpaceScreen.edit.putBoolean("status", true);
                    SpaceScreen.edit.putInt("uid", data.getInt(0));
                    SpaceScreen.edit.apply();

                    startActivity(new Intent(SignIn.this, HomePage.class).
                            putExtra("userid", data.getInt(0))
                            .putExtra("name", data.getString(1)));


                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignIn.this, Signup.class));
                finish();
            }
        });


    }
}