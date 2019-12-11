package com.example.shoppingmall.Currency;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.shoppingmall.R;
import com.example.shoppingmall.SAX.Chanel;
import com.example.shoppingmall.SAX.SAXUtil;

import java.util.List;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        List<Chanel> list = SAXUtil.parseSaxXml(this);
        for(Chanel chanel : list){
            System.out.println(chanel);
        }


        //两秒钟进入主界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Handler new 在主线程 所以 Runnable也是运行在主线程
                //启动主页面
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                //关闭当前页面
                finish();
            }
        },2000);
    }
}
