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

public class RecommendGridViewAdapter extends BaseAdapter {

    private Context mContext;
    public RecommendGridViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 6;
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
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_recommend = convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        //ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = datas.get(position);
        Glide.with(mContext).load(MyApplication.getResourcesUri(R.drawable.recommend1)).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText("南极人全棉决明子枕头单人荞麦皮护颈椎枕双人枕芯一对装成人家用");
        viewHolder.tv_price.setText("￥30.00");

        return convertView;
    }

    static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
