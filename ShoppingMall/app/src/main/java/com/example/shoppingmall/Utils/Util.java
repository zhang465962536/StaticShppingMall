package com.example.shoppingmall.Utils;

import android.content.Context;
import android.widget.Toast;

import com.example.shoppingmall.Currency.MyApplication;

public class Util {

    public static Context getContext(){
        return MyApplication.getContext();
    }

    public static void toast(String str){
        Toast.makeText(Util.getContext(),str,Toast.LENGTH_SHORT).show();
    }
}
