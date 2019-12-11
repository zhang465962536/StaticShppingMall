package com.example.shoppingmall.Currency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.shoppingmall.R;
import com.example.shoppingmall.SQLite.DataBaseAdapter;
import com.example.shoppingmall.SQLite.UserBeanData;
import com.example.shoppingmall.Utils.Util;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * 注册界面逻辑
 * */
public class RegisteredActivity extends Activity implements View.OnClickListener {

    private EditText etUser;
    private EditText etAge;
    private EditText etDesc;
    private RadioButton rbBoy;
    private RadioButton rbGirl;
    private RadioGroup mRadioGroup;
    private EditText etPass;
    private EditText etPassword;
    private EditText etEmail;
    private Button btnRegistered;

    private String gender = "男";
    private DataBaseAdapter dbAdapter;

    //性别
    private boolean isGender = true; //默认为true 为男生

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        dbAdapter = new DataBaseAdapter(this);
        initView();
    }

    private void initView() {
        etUser = findViewById(R.id.et_user);
        etAge = findViewById(R.id.et_age);
        etDesc = findViewById(R.id.et_desc);
        rbBoy = findViewById(R.id.rb_boy);
        rbGirl = findViewById(R.id.rb_girl);
        mRadioGroup = findViewById(R.id.mRadioGroup);
        etPass = findViewById(R.id.et_pass);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        btnRegistered = findViewById(R.id.btn_Registered);
        btnRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Registered:
                //点击注册的时候获取到输入框的值
                String name = etUser.getText().toString().trim();;
                String age = etAge.getText().toString().trim();
                String desc = etDesc.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String email = etEmail.getText().toString().trim();


                //判断输入框的值是否为空
                //判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(age) &
                        !TextUtils.isEmpty(pass) &
                        !TextUtils.isEmpty(password) &
                        !TextUtils.isEmpty(email)) {
                    //判断两次输入的密码是否一致
                    if (pass.equals(password)) {
                        //判断性别
                        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId == R.id.rb_boy) {
                                    isGender = true;
                                    gender = "男";
                                } else if (checkedId == R.id.rb_girl) {
                                    isGender = false;
                                    gender = "女";
                                }
                            }
                        });

                        //判断简介是否为空
                        if (TextUtils.isEmpty(desc)) {  //如果为空 提交的时候 默认赋值一句话
                            desc = "这个人很懒，什么都没有留下！";
                        }

                        //注册信息
                        UserBeanData user = new UserBeanData();
                       user.setUsername(name);
                        user.setPassword(password);
                        user.setE_mail(email);
                        user.setAge(Integer.parseInt(age));
                        user.setSex(gender);
                        user.setDesc(desc);

                        //将注册信息添加到数据库中
                        dbAdapter.add(user);
                        startActivity(new Intent(RegisteredActivity.this,LoginActivity.class));
                        finish();

                    }else {
                        Util.toast("两次输入的密码不一致");
                    }
                }else {
                    Util.toast("输入框不能为空");
                }

                break;
        }
    }
}

