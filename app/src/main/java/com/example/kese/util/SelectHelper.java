package com.example.kese.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kese.Model.ListModel;
import com.example.kese.flagment.HomeFragment;
import com.example.kese.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
public class SelectHelper extends SQLiteOpenHelper{
    public  static final String DatabaseName = "SelectLIST.db";
    public static final String TABLE_NAME = "SelectLIST";
    public static final String COLUME_ID = "id";                //分组id
    public static final String COLUME_Name = "name";        //分组名

    public SelectHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_NAME+"("+COLUME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUME_Name+" text)";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String addOne(String name)//返回添加结果
    {
        if(name==null||name.equals("")){
            return "请输入分组名";
        }

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUME_Name + "=?", new String[]{name});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return "已存在相同名字的记录，添加失败";
        }
        ContentValues v = new ContentValues();
        v.put(COLUME_Name,name);
        long insert = db.insert(TABLE_NAME,null,v);

        if (insert == -1) {
            return "添加失败";
        }
        return "添加成功";
    }
    public String deleteOne(String name)//返回删除结果
    {
        if(name==null||name.equals("")){
            return "请输入分组名";
        }
        SQLiteDatabase db = getWritableDatabase();
        if(db.delete(TABLE_NAME,COLUME_Name+"=?",new String[]{name})==0)
        {
            return "删除失败";
        }
        return "删除成功";
    }

    public ArrayList<String> getAll(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> s = new ArrayList<>();
        String sql = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);
        int nameIndex = cursor.getColumnIndex(COLUME_Name);
        try{
            if(cursor.moveToFirst()){
                do{
                    String name = cursor.getString(nameIndex);
                    s.add(name);
                }while (cursor.moveToNext());
            }

        }
        finally{
            cursor.close();
            db.close();
        }
        return s;
    }//查找全部
    public  ArrayList<ListModel> getAll_listModel(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ListModel> listModels = new ArrayList<>();
        String sql = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);
        int nameIndex = cursor.getColumnIndex(COLUME_Name);
        try{
            if(cursor.moveToFirst()){
                do{
                    String name = cursor.getString(nameIndex);
                    listModels.add(new ListModel(null,null,name,1));

                }while (cursor.moveToNext());
            }

        }
        finally {
            cursor.close();
            db.close();
        }
        return listModels;
    }//查找全部
    public String checkOne(String name){
        if(name==null||name.equals("")){
            return "输入为空";
        }
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from "+TABLE_NAME+" where "+COLUME_Name+"=?";
        Cursor cursor = db.rawQuery(sql,new String[]{name});
        if(cursor.moveToFirst()){
            return "已存在";
        }
        else{
            return "不存在";
        }
    }

    public ArrayList<String> getAll_show() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> s = new ArrayList<>();
        s.add("全部");
        s.add("收藏");
        s.add("未分组");
        String sql = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);
        int nameIndex = cursor.getColumnIndex(COLUME_Name);
        try{
            if(cursor.moveToFirst()){
                do{
                    String name = cursor.getString(nameIndex);
                    s.add(name);
                }while (cursor.moveToNext());
            }

        }
        finally{
            cursor.close();
            db.close();
        }
        return s;
    }
}
