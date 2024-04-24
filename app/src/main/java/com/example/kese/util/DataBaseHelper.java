package com.example.kese.util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public  static final String DatabaseName = "ACCOUNT.db";

    public DataBaseHelper(@Nullable Context context){
        super(context,DatabaseName,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table alluser(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("drop Table if exists alluser");

    }

    public Boolean insertData(String username,String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = MyDatabase.insert("alluser",null,contentValues);

        if(result==-1){
            return false;
        }
        return true;
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from alluser where username = ?",new String[]{username});
        return cursor.getCount() > 0;
    }
    public Boolean checkUsernamePassword(String username,String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = MyDatabase.rawQuery("Select * from alluser where username = ? and password = ?",new String[]{username,password});
        return cursor.getCount() > 0;
    }
}
