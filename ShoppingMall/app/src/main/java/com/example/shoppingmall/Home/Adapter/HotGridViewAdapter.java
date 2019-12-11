package com.example.shoppingmall.Home.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.Currency.MyApplication;
import com.example.shoppingmall.Home.Bean.ResultBeanData;
import com.example.shoppingmall.R;
import com.example.shoppingmall.Utils.Constants;

import java.util.List;

public class HotGridViewAdapter extends BaseAdapter {

    private Context mContext;

    public HotGridViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_hot = convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Glide.with(mContext).load(MyApplication.getResourcesUri(R.drawable.hot1)).into(viewHolder.iv_hot);
        viewHolder.tv_price.setText("￥95.6");
        viewHolder.tv_name.setText("正版 Android Studio开发实战：从零基础到App上线(第2版)  Android开发实战教程App开发程序员业余爱好者Android开发秘籍书籍");

        return convertView;
    }

    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
