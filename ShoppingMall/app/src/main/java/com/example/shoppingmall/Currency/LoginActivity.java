package com.example.shoppingmall.Currency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingmall.R;
import com.example.shoppingmall.SQLite.DataBaseAdapter;
import com.example.shoppingmall.SQLite.UserBeanData;
import com.example.shoppingmall.Utils.CacheUtils;
import com.example.shoppingmall.Utils.Util;


/*
登录界面逻辑
* */
public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText etName;
    private EditText etPassword;
    private CheckBox keepPassword;
    private Button btnLogin;
    private Button btnRegistered;
    private TextView tvForget;
    private DataBaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbAdapter = new DataBaseAdapter(this);
        initView();
        btnLogin.setOnClickListener(this);
        btnRegistered.setOnClickListener(this);
        //设置选中的状态
        boolean isCheck = CacheUtils.getBoolean(this, "keeppass", false);
        keepPassword.setChecked(isCheck);
        if(isCheck){
            //设置密码
            etName.setText(CacheUtils.getString(this,"name",""));
            etPassword.setText(CacheUtils.getString(this,"password",""));
        }
    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        keepPassword = findViewById(R.id.keep_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegistered = findViewById(R.id.btn_registered);
        tvForget = findViewById(R.id.tv_forget);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //【1】获取输入框的值
                String name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                //【2】判断是否为空
                if(!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)){
                    //登录
                    UserBeanData user = dbAdapter.find(name);
                    if(user != null){
                        String username = user.getUsername();
                        String userPassword = user.getPassword();
                        if(username.equals(name)){
                            if(userPassword.equals(password)){
                                CacheUtils.saveString(this,"name",name);
                                startActivity(new Intent(this,MainActivity.class));
                                finish();
                            }else {
                                Util.toast("密码错误！");
                            }
                        }
                    }else {
                        Util.toast("帐号不存在，请注册！");
                    }


                }else {
                    Util.toast("输入框不能为空");
                }
                break;

            case R.id.btn_registered:
                startActivity(new Intent(this,RegisteredActivity.class));
                break;
        }
    }

    //假设已经输入用户名和密码 但是没有点击登录 而是直接退出

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存记住密码选择状态
        CacheUtils.putBoolean(this,"keeppass",keepPassword.isChecked());
        //是否记住密码
        if(keepPassword.isChecked()){
            //保存 用户名 和 密码
            CacheUtils.putString(this,"name",etName.getText().toString().trim());
            CacheUtils.putString(this,"password",etPassword.getText().toString().trim());
        }else {
            //如果不记住密码 就把输入框账号密码给清除掉
            CacheUtils.deleShare(this,"name");
            CacheUtils.deleShare(this,"password");
        }
    }
}

