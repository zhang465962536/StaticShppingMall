package com.example.shoppingmall.Shoppingcart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.Home.Bean.GoodsBean;
import com.example.shoppingmall.R;
import com.example.shoppingmall.Shoppingcart.Utils.CartStorage;
import com.example.shoppingmall.Shoppingcart.View.AddSubView;
import com.example.shoppingmall.Utils.Constants;

import java.util.List;

//购物车适配器
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private Context mContext;
    private List<GoodsBean> datas;
    private TextView tvShopcartTotal;
    private  CheckBox checkboxAll;
    //完成状态下的删除CheckBox
    private final CheckBox cbAll;
    public ShoppingCartAdapter(Context mContext, List<GoodsBean> goodsBeanList, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext = mContext;
        datas = goodsBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll = cbAll;
        showTotalPrice();

        //设置点击事件
        setListener();
        //校验是否全选
        checkAll();
    }

    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //【1】 根据位置获得相应的Bean对象
                GoodsBean goodsBean = datas.get(position);
                //【2】 设置取反状态
                goodsBean.setSelected(!goodsBean.isSelected());
                //【3】刷新状态
                notifyItemChanged(position);
                //【4】校验是否是全选
                checkAll();
                //【5】重新计算总价格
                showTotalPrice();
            }
        });

        //设置CheckBox的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //【1】得到 全选 按钮的状态
                boolean isCheck = checkboxAll.isChecked();
                //【2】根据状态设置全选和非全选
                checkAll_None(isCheck);
                //【3】计算总价格
                showTotalPrice();
            }
        });

        //设置CheckBox的点击事件
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //【1】得到 全选 按钮的状态
                boolean isCheck = cbAll.isChecked();
                //【2】根据状态设置全选和非全选
                checkAll_None(isCheck);
            }
        });
    }

    //设置全选和非全选
    public void checkAll_None(boolean isCheck) {
        if(datas != null && datas.size() > 0){
            for(int i = 0; i < datas.size() ; i ++){
                GoodsBean goodsBean = datas.get(i);
                goodsBean.setSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }

    public void checkAll() {
        if(datas != null && datas.size() > 0){
            int number = 0; //记录选中个数
            for(int i = 0; i < datas.size() ; i ++){
                GoodsBean goodsBean = datas.get(i);
                if(!goodsBean.isSelected()){
                    //没有全选
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                }else {
                    //选中的
                    number ++;
                }
            }

            if(number == datas.size()){
                //全选
                checkboxAll.setChecked(true);
                cbAll.setChecked(true);
            }
        }else {   //datas没有数据的时候
            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText(" "+getTotalPrice());
    }

    //计算总价格
    private double getTotalPrice() {
        double totalPrice = 0.0;
        if(datas != null && datas.size() > 0){

            for(int i = 0; i < datas.size(); i ++){
                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isSelected()){ //只计算勾选中的商品

                    totalPrice = totalPrice + Double.valueOf(goodsBean.getNumber()) * Double.valueOf(goodsBean.getCover_price());

                }
            }
        }
        return totalPrice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //【1】根据位置得到想要的Bean对象
        final GoodsBean goodsBean = datas.get(position);
        //【2】设置数据
        holder.cb_gov.setChecked(goodsBean.isSelected());
        Glide.with(mContext).load(goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("￥" + goodsBean.getCover_price());
        holder.ddSubView.setValue(goodsBean.getNumber());

        //监听商品数量的变化
        holder.ddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int value) {
                //【1】内存中更新
                goodsBean.setNumber(value);
                //【2】本地数据更新
                CartStorage.getInstance().updateData(goodsBean);
                //【3】刷新适配器
                notifyItemChanged(position);
                //【4】再次计算总价格
                showTotalPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void deleteData() {
        if(datas != null && datas.size() >0){
            for (int i=0;i<datas.size();i++){
                //删除选中的
                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isSelected()){
                    //内存-把移除
                    datas.remove(goodsBean);
                    //保持到本地
                    CartStorage.getInstance().deleteData(goodsBean);
                    //刷新
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView ddSubView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            ddSubView = (AddSubView) itemView.findViewById(R.id.ddSubView);

            //设置Item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });

        }
    }

    /*
    点击Item的监听者 当点击某一条的时候被回调
     */
    public interface OnItemClickListener{
        public void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    //设置Item的监听
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
