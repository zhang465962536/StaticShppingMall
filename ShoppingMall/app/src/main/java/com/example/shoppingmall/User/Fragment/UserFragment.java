package com.example.shoppingmall.User.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingmall.Base.BaseFragment;
import com.example.shoppingmall.R;

//用户 fragment
public class UserFragment extends BaseFragment {

    @Override
    public View initView() {
        Log.e("TAG", "用户页面的fragment的UI被初始化了" );
       View view = View.inflate(getContext(), R.layout.person,null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "用户页面的fragment的数据被初始化了" );
    }
}
