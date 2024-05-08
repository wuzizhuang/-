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


public class MyDataHelper extends SQLiteOpenHelper {

    public  static final String DatabaseName = "LIST.db";
    public static final String TABLE_NAME = "LIST";
    public static final String COLUME_ID = "id";                //添加人标号id
    public static final String COLUME_NUMBER = "number";        //电话号码
    public static final String COLUME_ADDRESS = "address";      //name
    public static final String COLUME_PinYin = "pinyin";      //拼音
    public static final String COLUME_EMAIL = "email";          //email
    public static final String COLUME_BIRTHDAY = "birthday";    //birthday
    public static final String COLUME_WORK= "work";             //工作单位
    public static final String COLUME_HOME= "home";             //家庭地址
    public static final String COLUME_ATTENTION="attention";    //收藏
    public static final String COLUME_COLLECT="collect";        //分类组
    public static final String COLUME_BEIZHU="beizhu";          //备注





    Context mContext;
    String name;
    public MyDataHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME+ " ("
                + COLUME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUME_NUMBER + " TEXT,"
                + COLUME_ADDRESS + " TEXT,"
                + COLUME_PinYin + " TEXT,"
                + COLUME_EMAIL + " TEXT,"
                + COLUME_BIRTHDAY + " TEXT,"
                + COLUME_HOME + " TEXT,"
                + COLUME_COLLECT + " TEXT,"
                + COLUME_WORK + " TEXT,"
                + COLUME_ATTENTION + " TEXT,"
                + COLUME_BEIZHU + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists LIST");
    }
    public String addOne(ListModel listModel)
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUME_NUMBER,listModel.getPhoneNumber());
        cv.put(COLUME_ADDRESS,listModel.getName());

        SQLiteDatabase db = this.getWritableDatabase();
        long insert=db.insert("LIST",null,cv);
        if(insert==-1){
            return "fail";
        }
        return "success";
    }
    public String addOne_check(ListModel listModel)
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUME_NUMBER,listModel.getPhoneNumber());
        cv.put(COLUME_ADDRESS,listModel.getName());

       cv.put(COLUME_PinYin , StringUtils.getInitials(listModel.getName()));
        if(listModel.getPhoneNumber().equals("")||listModel.getName().equals(""))
        {
            return "fail";
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM LIST WHERE number = ?", new String[]{listModel.getPhoneNumber()});
        boolean numberExists = cursor.moveToFirst();


        if (numberExists) {
            cursor.close();
            db.close();
            return "exist"; // Return "2" if the number already exists
        }

        long insert=db.insert("LIST",null,cv);

        if(insert==-1){
            cursor.close();
            db.close();
            return "fail";
        }
        cursor.close();
        db.close();
        return "success";
    }

    public String deleteOne(ListModel listModel){
        SQLiteDatabase db = this.getWritableDatabase();
        long delete;
        delete = db.delete(TABLE_NAME,COLUME_NUMBER+"=?",new String[]{String.valueOf(listModel.getPhoneNumber())});
        db.close();
        if(delete==0){
            return "fail";
        }
        return "success";
    }
    public String upDataOne(ListModel listModel)//不可改动手机号码
    {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        cv.put(COLUME_ID,listModel.getId());
        cv.put(COLUME_NUMBER,listModel.getPhoneNumber());
        cv.put(COLUME_ADDRESS,listModel.getName());
        cv.put(COLUME_BIRTHDAY,listModel.getBirthday());
        cv.put(COLUME_EMAIL,listModel.getEmail());
        cv.put(COLUME_HOME,listModel.getHome());
        cv.put(COLUME_COLLECT,listModel.getCollect());
        cv.put(COLUME_BEIZHU,listModel.getBeizhu());
        cv.put(COLUME_ATTENTION,listModel.getAttention());
        int update=db.update(TABLE_NAME,cv,COLUME_NUMBER+"=?",new String[]{String.valueOf(listModel.getPhoneNumber())});
        db.close();

        if(update==0){
            return "fail";
        }
        return "success";
    }
    public ListModel getOne_ByNumber(String number){
        ListModel listModel = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LIST WHERE number = ?", new String[]{number});
        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(COLUME_ID);
            int numberIndex = cursor.getColumnIndex(COLUME_NUMBER);
            int addressIndex = cursor.getColumnIndex(COLUME_ADDRESS);
            int homeIndex = cursor.getColumnIndex(COLUME_HOME);
            int birthdayIndex = cursor.getColumnIndex(COLUME_BIRTHDAY);
            int emailIndex = cursor.getColumnIndex(COLUME_EMAIL);
            int collectIndex = cursor.getColumnIndex(COLUME_COLLECT);
            int attentionIndex = cursor.getColumnIndex(COLUME_ATTENTION);

            int id = cursor.getInt(idIndex);
            String name = cursor.getString(addressIndex);
            String phoneNumber = cursor.getString(numberIndex);
            String home = cursor.getString(homeIndex);
            String birthday = cursor.getString(birthdayIndex);
            String email = cursor.getString(emailIndex);
            String collect = cursor.getString(collectIndex);
            String attention = cursor.getString(attentionIndex);
            listModel = new ListModel(id,phoneNumber,name,email,birthday,home,collect,attention);
            cursor.close();
            db.close();

        }
        return listModel;
    }
    public List<ListModel> getAll()
    {
        ListModel listModel;
        List<ListModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql="SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);

        int idIndex = cursor.getColumnIndex(COLUME_ID);
        int numberIndex = cursor.getColumnIndex(COLUME_NUMBER);
        int addressIndex = cursor.getColumnIndex(COLUME_ADDRESS);

        while(cursor.moveToNext()){
            listModel = new ListModel(cursor.getInt(idIndex),cursor.getString(numberIndex),cursor.getString(addressIndex));
            boolean isAdd = list.add(listModel);
        }
        db.close();
        cursor.close();
        return list;
    }
    public ArrayList<ListModel> getAllList()
    {
        ListModel listModel;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ListModel> arrayList=new ArrayList<ListModel>();

        String sql="SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);

        int idIndex = cursor.getColumnIndex(COLUME_ID);
        int numberIndex = cursor.getColumnIndex(COLUME_NUMBER);
        int addressIndex = cursor.getColumnIndex(COLUME_ADDRESS);
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(addressIndex);
                String number = cursor.getString(numberIndex);
                arrayList.add(new ListModel(idIndex,  number,name,1));
            }
        } finally {
            cursor.close();
            db.close();
        }
        return arrayList;
    }
    public ArrayList<ListModel> getAllList_byPhone()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ListModel> arrayList=new ArrayList<ListModel>();

        String sql="SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);

        int idIndex = cursor.getColumnIndex(COLUME_ID);
        int numberIndex = cursor.getColumnIndex(COLUME_NUMBER);
        int addressIndex = cursor.getColumnIndex(COLUME_ADDRESS);
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(addressIndex);
                String number = cursor.getString(numberIndex);
                arrayList.add(new ListModel(idIndex, number,name,1));
            }
        } finally {
            cursor.close();
            db.close();
        }
        return arrayList;
    }
    public ArrayList<ListModel> getAllList_byName(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ListModel> arrayList=new ArrayList<ListModel>();
        String sql = "SELECT * FROM TABLE_NAME WHERE name LIKE '%substring%'";

        // 准备参数化查询，避免 SQL 注入风险
        String[] selectionArgs = { name };

        Cursor cursor = db.rawQuery(sql, selectionArgs);

        while(cursor.moveToNext())
        {
            int idIndex = cursor.getColumnIndex(COLUME_ID);
            int numberIndex = cursor.getColumnIndex(COLUME_NUMBER);
            int addressIndex = cursor.getColumnIndex(COLUME_ADDRESS);

            int id = cursor.getInt(idIndex);
            String nam = cursor.getString(addressIndex);
            String number = cursor.getString(numberIndex);

            arrayList.add(new ListModel(id,number,nam,1));
        }

        return arrayList;
    }
    public ArrayList<ListModel> getList_byselect(String Select){
        SQLiteDatabase db =this.getReadableDatabase();
        ArrayList<ListModel> arrayList=new ArrayList<ListModel>();
        String sql="SELECT * FROM " + TABLE_NAME + " WHERE " + COLUME_COLLECT + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{Select});

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(COLUME_ID);
            int numberIndex = cursor.getColumnIndex(COLUME_NUMBER);
            int addressIndex = cursor.getColumnIndex(COLUME_ADDRESS);
            int id = cursor.getInt(idIndex);
            String nam = cursor.getString(addressIndex);
            String number = cursor.getString(numberIndex);
            arrayList.add(new ListModel(id,number,nam,1));
        }
        return arrayList;
    }
    public ArrayList<ListModel> getList_byselect_Null(String select){
        SQLiteDatabase db =this.getReadableDatabase();
        ArrayList<ListModel> arrayList=new ArrayList<ListModel>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUME_COLLECT + " IS NULL";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(COLUME_ID);
            int numberIndex = cursor.getColumnIndex(COLUME_NUMBER);
            int addressIndex = cursor.getColumnIndex(COLUME_ADDRESS);
            int id = cursor.getInt(idIndex);
            String nam = cursor.getString(addressIndex);
            String number = cursor.getString(numberIndex);
            arrayList.add(new ListModel(id,number,nam,1));
        }
        return arrayList;
    }
    public ArrayList<ListModel> getList_byattention(String Attention){
        SQLiteDatabase db =this.getReadableDatabase();
        ArrayList<ListModel> arrayList=new ArrayList<ListModel>();
        String sql="SELECT * FROM " + TABLE_NAME + " WHERE " + COLUME_ATTENTION + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{Attention});

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(COLUME_ID);
            int numberIndex = cursor.getColumnIndex(COLUME_NUMBER);
            int addressIndex = cursor.getColumnIndex(COLUME_ADDRESS);
            int id = cursor.getInt(idIndex);
            String nam = cursor.getString(addressIndex);
            String number = cursor.getString(numberIndex);
            arrayList.add(new ListModel(id,number,nam,1));
        }
        return arrayList;
    }
    public ArrayList<ListModel> getAllList_byPhoneNumber(String phoneNumber)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ListModel> arrayList=new ArrayList<ListModel>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "+COLUME_NUMBER+" =? ";

        // 准备参数化查询，避免 SQL 注入风险


        Cursor cursor = db.rawQuery(sql, new String[]{phoneNumber});

        while(cursor.moveToNext())
        {
            int idIndex = cursor.getColumnIndex(COLUME_ID);
            int numberIndex = cursor.getColumnIndex(COLUME_NUMBER);
            int addressIndex = cursor.getColumnIndex(COLUME_ADDRESS);
            int homeIndex = cursor.getColumnIndex(COLUME_HOME);
            int birthdayIndex = cursor.getColumnIndex(COLUME_BIRTHDAY);
            int emailIndex = cursor.getColumnIndex(COLUME_EMAIL);
            int collectIndex = cursor.getColumnIndex(COLUME_COLLECT);

            int id = cursor.getInt(idIndex);
            String nam = cursor.getString(addressIndex);
            String number = cursor.getString(numberIndex);
            String home = cursor.getString(homeIndex);
            String birthday = cursor.getString(birthdayIndex);
            String email = cursor.getString(emailIndex);
            String collect = cursor.getString(collectIndex);

            arrayList.add(new ListModel(id,number,nam,home,birthday,email,collect,null));
        }
        db.close();
        return arrayList;
    }
    public ArrayList<ListModel> getAllList_byPrivate()
    {

        ArrayList<ListModel> arrayList=new ArrayList<ListModel>();
        String setting_1 ="设置";
        arrayList.add(new ListModel(1, null,setting_1,1));

        String setting_2 ="设置";
        arrayList.add(new ListModel(2, null,setting_2,1));

        String setting_3 ="设置";
        arrayList.add(new ListModel(3, null,setting_3,1));
        return arrayList;
    }
    public ArrayList<ListModel> sellectALL(String sellect_informantion)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ListModel> arrayList=new ArrayList<ListModel>();
        int flag=StringUtils.get_What_Type(sellect_informantion);
        if(flag==0) return arrayList;
        else if(flag==1)
            return getAllList_byPhone();
        else if(flag==2)
            return getAllList_byPrivate();
        else if(flag==3)
        {
            String sql="SELECT * FROM " + TABLE_NAME+" WHERE name LIKE '%"+sellect_informantion+"%'";
            Cursor cursor = db.rawQuery(sql,null);
            int idIndex = cursor.getColumnIndex(COLUME_ID);
            int numberIndex = cursor.getColumnIndex(COLUME_NUMBER);
            int addressIndex = cursor.getColumnIndex(COLUME_ADDRESS);

        }
        return arrayList;
    }

}
