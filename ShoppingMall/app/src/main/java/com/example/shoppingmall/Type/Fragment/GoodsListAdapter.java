package com.example.shoppingmall.Type.Fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingmall.R;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


public class GoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private Context context;
    private List<Goods> goodsList;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;

    public GoodsListAdapter(Context context, List<Goods> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_goods_list_item,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ViewHolder){
            Goods goods = goodsList.get(position);
            final ViewHolder newHolder = (ViewHolder)holder;
            newHolder.tv1.setText(goods.getGname());
            newHolder.tv2.setText(goods.getPrice());
            Log.d("=========",goods.getGname());
            //newHolder.iv1.setImageBitmap(image.getBitmap());
            holder.itemView.setTag(goods);
        }

    }

    @Override
    public int getItemCount() {
        Log.d("---------",String.valueOf(goodsList.size()));
        return goodsList.size();
    }

    @Override
    public void onClick(View view) {
        if(onRecyclerViewItemClickListener!=null){
            onRecyclerViewItemClickListener.onItemClick(view,view.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        this.onRecyclerViewItemClickListener=listener;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv1;
        private TextView tv2;
        private ImageView iv1;
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.goods_name);
            tv2=itemView.findViewById(R.id.goods_price);
            iv1=itemView.findViewById(R.id.goods_image);
        }
    }


}
