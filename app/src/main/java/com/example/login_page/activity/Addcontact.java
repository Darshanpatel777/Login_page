package com.example.login_page.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login_page.R;
import com.example.login_page.database.MyDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class Addcontact extends AppCompatActivity {

    Button save, cancel;

    TextInputEditText name, num, em, ad;

    ImageView pop;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        name = findViewById(R.id.name);
        num = findViewById(R.id.num);
        em = findViewById(R.id.em);
        ad = findViewById(R.id.ad);
        pop = findViewById(R.id.pop);


        int userid = getIntent().getIntExtra("id", 20);

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu pmenu = new PopupMenu(Addcontact.this,pop);
                pmenu.inflate(R.menu.mymenu);
                pmenu.show();

                pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getItemId() == R.id.logout)
                        {
                            SpaceScreen.edit.putBoolean("status", true);
                            SpaceScreen.edit.apply();

                            startActivity(new Intent(Addcontact.this,SignIn.class));
                            finish();

//                            Toast.makeText(Addcontact.this,"setting",Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
            }
        });




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabase db = new MyDatabase(Addcontact.this);


                // Retrieve the phone number input from the user
                String email = em.getText().toString();
                //email required check
                if (email.isEmpty()) {
                    em.setError("Email if required");
                    em.requestFocus();
                    return;
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    em.setError("Please enter a valid email address");
                    // Check if the email format is valid
                    em.requestFocus();
                    return;
                }


                //number  required check
                // Retrieve the phone number input from the user
                String number = num.getText().toString();
                // Validate the phone number
                if (number.isEmpty()) {
                    num.setError("number if required");
                    num.requestFocus();
                    return;
                }
                else if (number.length() > 10)
                {
                    // Check if the phone number exceeds 10 digits
                    num.setError("Phone number cannot exceed 10 digits");
                    num.requestFocus();
                    return;
                }
                else if (!number.matches("\\d{10}")) {
                    num.setError("Please enter a valid 10-digit phone number");
                    num.requestFocus();
                    return;
                }


                Boolean b = db.addcontact(userid, name.getText().toString()
                        , num.getText().toString(), em.getText().toString()
                        , ad.getText().toString());
                if (b) {
                    startActivity(new Intent(Addcontact.this, HomePage.class).putExtra("id", userid));
                    finish();
                }
            }
        });


    }
}


// Retrieve the phone number input from the user
//String phoneNumber = num.getText().toString();
//
//// Validate the phone number
//        if (phoneNumber.isEmpty()) {
//        // Check if the phone number field is empty
//        num.setError("Phone number is required");
//            num.requestFocus();
//            return;
//                    } else if (!phoneNumber.matches("\\d{10}")) { // Assuming a 10-digit phone number
//        // Check if the phone number format is valid (10 digits)
//        num.setError("Please enter a valid 10-digit phone number");
//            num.requestFocus();
//            return;
//                    }


// Retrieve the email input from the user
//String email = em.getText().toString();
//
//// Validate the email
//        if (email.isEmpty()) {
//        // Check if the email field is empty
//        em.setError("Email is required");
//            em.requestFocus();
//            return;
//                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//        // Check if the email format is valid
//        em.setError("Please enter a valid email address");
//            em.requestFocus();
//            return;
//                    }