package com.example.shoppingmall.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user.db";
    private static final int VERSION = 1;


    private static final String CREATE_TABLE_USER = "CREATE TABLE user(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "userName TEXT," +
            "age INTEGER," +
            "description TEXT," +
            "sex TEXT," +
            "password TEXT," +
            "E_mail TEXT)";


    private static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS user";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USER);
        db.execSQL(CREATE_TABLE_USER);
    }
}
