package com.example.shoppingmall.Base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class  BaseFragment extends Fragment {

    protected Context mContext;

    /***
     * 当该类被系统创建的时候被回调
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    //当视图被创建的时候 交给子类去做
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();

    }

    //抽象类 由孩子实现 不同的效果
    public abstract View initView();

    //当Activity 被创建的时候 回调该方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //当子类需要联网请求数据的时候 可以重写该方法，在该方法中联网请求
    public  void initData(){

    }
}
