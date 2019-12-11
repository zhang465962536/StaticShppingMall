package com.example.shoppingmall.SQLite;

import android.provider.BaseColumns;

//数据库元的定义
public final class UserMetaData {

    private UserMetaData(){}

    //创建表 实现 基列User表的定义
    public static abstract class UserTable implements BaseColumns{

        public static final String TABLE_NAME = "user";
        public static final String USERNAME ="userName";
        public static final String  PASSWORD ="password";
        public static final String DESCRIPTION ="description";
        public static final String AGE = "age";
        public static final String SEX = "sex";
        public static final String E_MAIL = "E_mail";
    }
}
