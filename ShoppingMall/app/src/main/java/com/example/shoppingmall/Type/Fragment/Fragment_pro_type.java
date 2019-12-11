package com.example.shoppingmall.Type.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.shoppingmall.R;

import java.util.ArrayList;
import androidx.fragment.app.Fragment;


public class Fragment_pro_type extends Fragment {
    private ArrayList<Type> list;
    private ImageView hint_img;
    private GridView listView;
    private Pro_type_adapter adapter;
    private Type type;
    private ProgressBar progressBar;
    private String typename;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pro_type, null);
        progressBar=(ProgressBar) view.findViewById(R.id.progressBar);
        hint_img=(ImageView) view.findViewById(R.id.hint_img);
        listView = (GridView) view.findViewById(R.id.listView);
        typename=getArguments().getString("typename");
        ((TextView)view.findViewById(R.id.toptype)).setText(typename);
        GetTypeList();
        adapter=new Pro_type_adapter(getActivity(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {


            }
        });

        return view;
    }


    private void GetTypeList() {
        list=new ArrayList<Type>();
         if(typename.equals("潮流女装")){
        /*实现分类右边列表*/
        type=new Type(1,"T恤","");
        list.add(type);
        type=new Type(2,"衬衫","");
        list.add(type);
        type=new Type(3,"卫衣","");
        list.add(type);
        type=new Type(4,"风衣","");
        list.add(type);
        type=new Type(5,"毛衣","");
        list.add(type);
        type=new Type(7,"棉衣","");
        list.add(type);
        type=new Type(8,"羽绒服","");
        list.add(type);
        type=new Type(9,"皮衣","");
        list.add(type);
        type=new Type(10,"","");
        list.add(type);
        type=new Type(11,"连衣裙","");
        list.add(type);
        type=new Type(12,"半身裙","");
        list.add(type);
        type=new Type(13,"牛仔裤","");
        list.add(type);
        type=new Type(14,"运动裤","");
        list.add(type);
        type=new Type(15,"","");
        list.add(type);
        type=new Type(16,"","");
        list.add(type);
        type=new Type(17,"运动鞋","");
        list.add(type);
        type=new Type(18,"帆布鞋","");
        list.add(type);
        type=new Type(19,"高跟鞋","");
        list.add(type);}

        else if(typename.equals("食物生鲜")){
            /*实现分类右边列表*/
            type=new Type(1,"饼干","");
            list.add(type);
            type=new Type(2,"面包","");
            list.add(type);
            type=new Type(3,"糖果","");
            list.add(type);
            type=new Type(4,"巧克力","");
            list.add(type);
            type=new Type(5,"其他零食","");
            list.add(type);
            type=new Type(6,"","");
            list.add(type);
            type=new Type(7,"茶","");
            list.add(type);
            type=new Type(8,"酒","");
            list.add(type);
            type=new Type(9,"饮料","");
            list.add(type);
            type=new Type(10,"方便速食","");
            list.add(type);
            type=new Type(11,"坚果炒货","");
            list.add(type);
            type=new Type(12,"干果蜜饯","");
            list.add(type);
            type=new Type(13,"干货","");
            list.add(type);
            type=new Type(14,"","");
            list.add(type);
            type=new Type(15,"","");
            list.add(type);
            type=new Type(16,"豆制品","");
            list.add(type);
            type=new Type(17,"乳制品","");
            list.add(type);
            type=new Type(18,"肉制品","");
            list.add(type);}

            else
        {
            /*实现分类右边列表*/
            type=new Type(1,"T恤","");
            list.add(type);
            type=new Type(2,"衬衫","");
            list.add(type);
            type=new Type(3,"卫衣","");
            list.add(type);
            type=new Type(4,"风衣","");
            list.add(type);
            type=new Type(5,"毛衣","");
            list.add(type);
            type=new Type(7,"棉衣","");
            list.add(type);
            type=new Type(8,"羽绒服","");
            list.add(type);
            type=new Type(9,"皮衣","");
            list.add(type);
            type=new Type(10,"","");
            list.add(type);
            type=new Type(11,"连衣裙","");
            list.add(type);
            type=new Type(12,"半身裙","");
            list.add(type);
            type=new Type(13,"牛仔裤","");
            list.add(type);
            type=new Type(14,"运动裤","");
            list.add(type);
            type=new Type(15,"","");
            list.add(type);
            type=new Type(16,"","");
            list.add(type);
            type=new Type(17,"运动鞋","");
            list.add(type);
            type=new Type(18,"帆布鞋","");
            list.add(type);
            type=new Type(19,"高跟鞋","");
            list.add(type);}

        /*for(int i=1;i<10;i++){
            type=new Type(i, typename+i, "");
            list.add(type);
        }*/
        progressBar.setVisibility(View.GONE);
    }
}
