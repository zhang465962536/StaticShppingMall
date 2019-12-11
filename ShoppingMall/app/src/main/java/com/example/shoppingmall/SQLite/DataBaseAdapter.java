package com.example.shoppingmall.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseAdapter {

    private DatabaseHelper dbHelper;

    public DataBaseAdapter(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }



    //注册信息插入数据库
    public void add(UserBeanData user){

        String sql = "insert into user(userName,age,description,sex,password,E_mail) values(?,?,?,?,?,?)";
        Object[] args = {user.getUsername(),user.getAge(),user.getDesc(),user.getSex(),user.getPassword(),user.getE_mail()};
        //获取操作数据库的工具类
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql,args);
        db.close();
    }



   //对账号进行查询
    public UserBeanData find(String username){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select userName,password from user where userName = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{username});
        UserBeanData user = null;
        if(cursor.moveToNext()){
            user = new UserBeanData();
            user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(UserMetaData.UserTable.USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(UserMetaData.UserTable.PASSWORD)));
        }
        cursor.close();
        db.close();
        return user;
    }
}
