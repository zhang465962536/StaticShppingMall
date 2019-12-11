package com.example.shoppingmall.Shoppingcart.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.example.shoppingmall.Currency.MyApplication;
import com.example.shoppingmall.Home.Bean.GoodsBean;
import com.example.shoppingmall.Utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    private static CartStorage instance;
    private Context mContext;
    //在内存中缓存的数据 使用 SparseArray性能 优于 HashMap
    private SparseArray<GoodsBean> sparseArray;

    private CartStorage(Context context){
        mContext = context;
        //把之前存储的数据读取
        sparseArray = new SparseArray<>(100);

        listToSparseArray();
    }

    /*
    从本地读取的数据加入到 SparseArray
     */
    private void listToSparseArray() {
        List<GoodsBean> goodsBeanList = getAllData();
        //把List数据转换成SparseArray
        for(int i =0;i <  goodsBeanList.size(); i ++){
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }

    }

    /*
    获取本地所有的数据
     */
    public List<GoodsBean> getAllData(){
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        //【1】从本地获取
        String json = CacheUtils.getString(mContext,JSON_CART);
        //【2】使用Gson转换成列表
        if(!TextUtils.isEmpty(json)){  //json不为空 则开始执行转换

            //把String转换成List
            goodsBeanList = new Gson().fromJson(json,new TypeToken<List<GoodsBean>>(){}.getType());

        }
        return goodsBeanList;
    }

    /*
    得到购物车实例
     */
    public static CartStorage getInstance(){

        if(instance == null){
            instance = new CartStorage(MyApplication.getContext());
        }
        return instance;
    }

    /*
    添加商品数据
     */
    public void addData(GoodsBean goodsBean){
        //【1】添加到内存中 sparseArray
        //如果当前商品数据已经存在在购物车，商品number+1
        GoodsBean tempData = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if(tempData != null){
            //内存中已经有该商品数据
            tempData.setNumber(tempData.getNumber() + 1);
        }else {
            tempData = goodsBean;
            tempData.setNumber(1);
        }

        //同步到内存中
        sparseArray.put(Integer.parseInt(tempData.getProduct_id()),tempData);

        //【2】同步到本地
        saveLocal();
    }

    /*
    删除商品数据
     */
    public void deleteData(GoodsBean goodsBean){
        //【1】在内存中删除
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));

        //【2】把内存数据保存在本地
        saveLocal();
    }

    /*
 删除商品数据
  */
    public void updateData(GoodsBean goodsBean){
        //【1】在内存中更新
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        //【2】同步到本地
        saveLocal();
    }

    /*
    保持数据到本地
     */
    private void saveLocal() {

        //【1】sparseArray转换成List
        List<GoodsBean> goodsBeanList = sparseToList();
        //【2】使用Gson把列表转换成String类型
        String json = new Gson().toJson(goodsBeanList);
        //【3】把String数据保存
        CacheUtils.saveString(mContext,JSON_CART,json);

    }

    private List<GoodsBean> sparseToList() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for(int i = 0; i < sparseArray.size(); i ++){
             GoodsBean goodsBean = sparseArray.valueAt(i);
             goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }
}
