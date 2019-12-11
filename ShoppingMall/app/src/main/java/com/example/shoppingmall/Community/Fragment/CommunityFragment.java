package com.example.shoppingmall.Community.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingmall.Base.BaseFragment;
import com.example.shoppingmall.R;

//发现 fragment
public class CommunityFragment extends BaseFragment {

    @Override
    public View initView() {
       View view = View.inflate(getActivity(), R.layout.fragment_community,null);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "发现页面的fragment的数据被初始化了" );

    }
}
