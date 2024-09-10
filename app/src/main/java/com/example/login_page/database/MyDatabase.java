package com.example.login_page.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(Context context) {
        super(context, "mydata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = "CREATE TABLE user (id integer primary key autoincrement ,username text unique, email text, password text)";
        db.execSQL(table);

        String contact = "CREATE TABLE contact (id integer primary key autoincrement,userid integer ,name text , number text , email text , adress text)";
        db.execSQL(contact);


    }

    public Boolean addcontact(int userid, String name, String number, String email, String adress) {
        try {

            String insrt = "INSERT INTO contact (userid,name,number,email,adress) VALUES (" + userid + " , '" + name + "' , '" + number + "' , '" + email + "' , '" + adress + "' )";
            getWritableDatabase().execSQL(insrt);
            return true;
        } catch (Exception exception) {
            return false;
        }

    }

    public Boolean insertdata(String username, String email, String pass) {
        try {
            String insert = "INSERT INTO user (username , email , password) VALUES ('" + username + "','" + email + "','" + pass + "')";
            getWritableDatabase().execSQL(insert);
            return true;
        } catch (Exception e) {
            Log.e("---cr---", "insertdata: " + e);
            return false;
        }
    }

    //user name , number, add etc......... add kare te show karave
    public Cursor selectcon(int userid) {
        String s = "SELECT * FROM contact WHERE userid = " + userid;
        return getReadableDatabase().rawQuery(s, null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor userlogin(String user, String pass) {

        String select = "SELECT * FROM user WHERE username = '" + user + "' AND password = '" + pass + "'";
        Cursor cr = getReadableDatabase().rawQuery(select, null);

        return cr;
    }

    public void editdata(String newname, String newnum, int contactid) {

        String update = "UPDATE contact SET name = '" + newname + "' , number = '" + newnum + "' WHERE id = " + contactid;
        getWritableDatabase().execSQL(update);

    }

    public void delete(int contactid) {
        String delete = "DELETE FROM contact WHERE ID = " + contactid;
        getWritableDatabase().execSQL(delete);
    }
}
