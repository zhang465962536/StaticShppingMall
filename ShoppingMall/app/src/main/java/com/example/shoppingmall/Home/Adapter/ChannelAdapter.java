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
import com.example.shoppingmall.SAX.Chanel;
import com.example.shoppingmall.Utils.Constants;

import java.util.List;
//频道的适配器
public class ChannelAdapter extends BaseAdapter {

    private Context mContext;
    private List<Chanel> list;

    public ChannelAdapter(Context mContext, List<Chanel> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 10;
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
        ViewHoler viewHoler ;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_channel,null);
            viewHoler = new ViewHoler();
            viewHoler.iv_icon = convertView.findViewById(R.id.iv_channel);
            viewHoler.tv_title = convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHoler);
        }else {
            viewHoler = (ViewHoler) convertView.getTag();
        }

        Chanel chanel = list.get(position);
        Glide.with(mContext).load(chanel.getImage()).into(viewHoler.iv_icon);
        viewHoler.tv_title.setText(chanel.getText());
        return convertView;
    }

    static class ViewHoler{
        ImageView iv_icon;
        TextView tv_title;
    }
}
