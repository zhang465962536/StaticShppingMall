package com.example.shoppingmall.Type.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.shoppingmall.R;

import java.util.ArrayList;



public class Pro_type_adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Type> list;
    private Context context;
    private Type type;
    ArrayList<Goods> goodsArrayList;
    GoodsListAdapter goodsListAdapter;

    public Pro_type_adapter(Context context, ArrayList<Type> list) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list != null && list.size() > 0)
            return list.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyView view;
        if (convertView == null) {
            view = new MyView();
            convertView = mInflater.inflate(R.layout.list_pro_type_item, null);
            view.icon = (ImageView) convertView.findViewById(R.id.typeicon);
            view.name = (TextView) convertView.findViewById(R.id.typename);
            convertView.setTag(view);
        } else {
            view = (MyView) convertView.getTag();
        }

        goodsArrayList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            type = list.get(position);
            view.name.setText(type.getTypename());
            if (type.getTypename().contains("衬衫")) {
                view.icon.setBackgroundResource(R.drawable.dx);
            } else if (type.getTypename().contains("毛衣")) {
                view.icon.setBackgroundResource(R.drawable.my);
            } else if (type.getTypename().contains("卫衣")) {
                view.icon.setBackgroundResource(R.drawable.wy);
            } else if (type.getTypename().contains("T恤")) {
                view.icon.setBackgroundResource(R.drawable.t);

                //ImageView image2= getResources().getDrawable(R.drawable.t);
                //ImageView image1 = convertView.findViewById(R.id.typeicon);

                view.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
            } else if (type.getTypename().contains("风衣")) {
                view.icon.setBackgroundResource(R.drawable.dfy);
            } else if (type.getTypename().contains("棉衣")) {
                view.icon.setBackgroundResource(R.drawable.ny);
            } else if (type.getTypename().contains("皮衣")) {
                view.icon.setBackgroundResource(R.drawable.py);
            } else if (type.getTypename().contains("羽绒服")) {
                view.icon.setBackgroundResource(R.drawable.yrf);
            } else if (type.getTypename().contains("连衣裙")) {
                view.icon.setBackgroundResource(R.drawable.qyl);
            } else if (type.getTypename().contains("半身裙")) {
                view.icon.setBackgroundResource(R.drawable.bs);
            } else if (type.getTypename().contains("牛仔裤")) {
                view.icon.setBackgroundResource(R.drawable.nzk);
            } else if (type.getTypename().contains("运动裤")) {
                view.icon.setBackgroundResource(R.drawable.ydk);
            } else if (type.getTypename().contains("运动鞋")) {
                view.icon.setBackgroundResource(R.drawable.ydx);
            } else if (type.getTypename().contains("帆布鞋")) {
                view.icon.setBackgroundResource(R.drawable.fb);
            } else if (type.getTypename().contains("高跟鞋")) {
                    view.icon.setBackgroundResource(R.drawable.gg);


                } else if (type.getTypename().contains("干果蜜饯")) {
                    view.icon.setBackgroundResource(R.drawable.ggy);
                } else if (type.getTypename().contains("饼干")) {
                    view.icon.setBackgroundResource(R.drawable.hc);
                view.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                } else if (type.getTypename().contains("面包")) {
                    view.icon.setBackgroundResource(R.drawable.mb);
                } else if (type.getTypename().contains("糖果")) {
                    view.icon.setBackgroundResource(R.drawable.tg);
                } else if (type.getTypename().contains("巧克力")) {
                    view.icon.setBackgroundResource(R.drawable.qkl);
                } else if (type.getTypename().contains("其他零食")) {
                    view.icon.setBackgroundResource(R.drawable.qt);
                    view.icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
                } else if (type.getTypename().contains("酒")) {
                    view.icon.setBackgroundResource(R.drawable.j);
                } else if (type.getTypename().contains("饮料")) {
                    view.icon.setBackgroundResource(R.drawable.wl);
                } else if (type.getTypename().contains("茶")) {
                    view.icon.setBackgroundResource(R.drawable.cc);
                } else if (type.getTypename().contains("方便速食")) {
                    view.icon.setBackgroundResource(R.drawable.ff);
                } else if (type.getTypename().contains("坚果炒货")) {
                    view.icon.setBackgroundResource(R.drawable.jg);
                } else if (type.getTypename().contains("干货")) {
                    view.icon.setBackgroundResource(R.drawable.gh);
                } else if (type.getTypename().contains("豆制品")) {
                    view.icon.setBackgroundResource(R.drawable.dz);
                } else if (type.getTypename().contains("乳制品")) {
                    view.icon.setBackgroundResource(R.drawable.rz);
                } else if (type.getTypename().contains("肉制品")) {
                    view.icon.setBackgroundResource(R.drawable.rzp);
                } else {
                //view.icon.setBackgroundResource(R.drawable.huazhuang);
                }
            }
        return convertView;
    }




    private class MyView {
        private ImageView icon;
        private TextView name;
    }


}
