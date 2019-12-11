package com.example.shoppingmall.Home.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.Currency.MyApplication;
import com.example.shoppingmall.Home.Bean.ResultBeanData;
import com.example.shoppingmall.R;
import com.example.shoppingmall.Utils.Constants;

import java.util.List;

//秒杀部分 适配器
public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    public SeckillRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //【1】根据位置得到对应的位置

        //【2】绑定数据
        Glide.with(mContext).load(MyApplication.getResourcesUri(R.drawable.seckill1)).into(holder.iv_figure);
        holder.tv_cover_price.setText("250.00");
        holder.tv_origin_price.setText("888.00");
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //通过接口回调
                    //Toast.makeText(mContext,"秒杀 = " + getLayoutPosition(),Toast.LENGTH_SHORT).show();
                    if(onSeckillRecyclerView != null){
                        onSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /*
    监听器
     */
    public interface onSeckillRecyclerView{
        //当某条被点击的时候 回调
        public void onItemClick(int position);
    }

    //外界通过该方法设置item 监听
    public void setOnSeckillRecyclerView(SeckillRecyclerViewAdapter.onSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }

    private onSeckillRecyclerView onSeckillRecyclerView;




}
