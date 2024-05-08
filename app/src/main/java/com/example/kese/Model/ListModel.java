package com.example.kese.Model;

import android.media.Image;

import com.example.kese.R;

public class ListModel {
    /*
    *
    * public  static final String DatabaseName = "LIST.db";
    public static final String TABLE_NAME = "LIST";
    public static final String COLUME_ID = "id";                //添加人标号id
    public static final String COLUME_NUMBER = "number";        //电话号码
    public static final String COLUME_ADDRESS = "address";      //name
    public static final String COLUME_EMAIL = "email";          //email
    public static final String COLUME_BIRTHDAY = "birthday";    //birthday
    public static final String COLUME_HOME= "home";             //家庭地址
    public static final String COLUME_COLLECT="collect";        //收藏组
    public static final String COLUME_BEIZHU="beizhu";          //备注
    *
    * */
    private Integer id;
    private String phoneNumber; //电话号码
    private String name;        //名字
    private String address;

    private String birthday;
    private String home;        //家庭地址
    private String collect;     //收藏组
    private String beizhu;      //备注
    private String email;       //email
    private String position;
    private Class<?> layout_page;
    private int photo;
    public ListModel() {
        this.collect="-1";
    }
    public ListModel(Integer id, String phoneNumber, String name) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        //this.address = address;
        //this.position = position;
    }
    public ListModel(Integer id, String phoneNumber, String name,int photo) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.photo= R.mipmap.next;
        //this.address = address;
        //this.position = position;
    }
    public ListModel(Integer id, String name, String phoneNumber,String email,String birthday,String home,String collect) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.home = home;
        this.birthday = birthday;
        this.email = email;
        this.collect = collect;


    }
    public ListModel(Integer id, String phoneNumber, String name,String home,String birthday,String email,String collect,String beizhu) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.home = home;
        this.birthday = birthday;
        this.email = email;
        this.collect = collect;
        this.beizhu = beizhu;

    }
    public ListModel(Integer id, String phoneNumber, String name,Class<?> layout_page) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.layout_page = layout_page;
        this.photo= R.mipmap.next;
        //this.address = address;
        //this.position = position;
    }
    @Override
    public String toString() {
        return "ListModel{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", position='"+'}';
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
    }
    public int getPhoto() {
        return photo;
    }
    public void setPhoto(int photo) {
        this.photo = photo;
    }
    public Class<?> getLayout_page() {
        return layout_page;
    }
    public void setLayout_page(Class<?> layout_page) {
        this.layout_page = layout_page;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getHome() {
        return home;
    }
    public void setHome(String home) {
        this.home = home;
    }
    public String getCollect() {
        return collect;
    }
    public void setCollect(String collect) {
        this.collect = collect;
    }
    public String getBeizhu() {
        return beizhu;
    }
    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoto(Integer photo) {
        this.photo = photo;
    }

}
