package com.example.storepro.sqllite;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.storepro.User;

import java.util.ArrayList;

public class sqlliteDB extends SQLiteOpenHelper {
    SQLiteDatabase db;

    public sqlliteDB(@Nullable Context context) {
        super(context, "sqluser.db", null, 1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(_id INTEGER PRIMARY KEY AUTOINCREMENT,username varchar(20),email varchar(50),phone varchar(11),password varchar(50),sex varchar(4),birthday varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void add(String username, String email, String phone, String password, String sex, String birthday) {
        db.execSQL("INSERT INTO user (username,email,phone,password,sex,birthday) VALUES(?,?,?,?,?,?)", new Object[]{username, email, phone, password, sex, birthday});
    }

    public void update1(String password) {
        db.execSQL("UPDATE user SET password = ?", new Object[]{password});
    }

    public void update3(String username){
        db.execSQL("UPDATE user SET username = ?", new Object[]{username});
    }
    public void update4(String sex){
        db.execSQL("UPDATE user SET sex = ?", new Object[]{sex});
    }
    public void update5(String birthday){
        db.execSQL("UPDATE user SET birthday = ?", new Object[]{birthday});
    }
    public void update2(String username, String sex, String birthday) {
        db.execSQL("UPDATE user SET username = ?,sex = ?,birthad = ? ", new Object[]{username, sex, birthday});
    }

    public ArrayList<User> getAllData() {
        ArrayList<User> list = new ArrayList
                <User>();
        db = getReadableDatabase();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));

            list.add(new User(username, email, phone, password, sex, birthday));
        }
        return list;
    }
}
