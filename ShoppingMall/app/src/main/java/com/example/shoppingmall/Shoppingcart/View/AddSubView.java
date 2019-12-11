package com.example.shoppingmall.Shoppingcart.View;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.shoppingmall.R;

//加减按钮 自定义控件
public class AddSubView extends LinearLayout implements View.OnClickListener {

    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView tv_value;
    private int value = 1;
    private int maxValue = 10;
    private int minValue = 1;
    private Context mContext;
    //在布局中使用
    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        //把布局文件实例化 并在加载在此类中
        View.inflate(context, R.layout.add_sub_view,this);
        iv_sub = findViewById(R.id.iv_sub);
        iv_add = findViewById(R.id.iv_add);
        tv_value = findViewById(R.id.tv_value);

        int value = getValue();
        setValue(value);

        //设置点击事件
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add:
                addNumber();
                break;

            case R.id.iv_sub:
                subNumber();
                break;
        }
    }

    /*
    减少数量
     */
    private void subNumber() {
        if(value > minValue){
            value --;
        }else {
            Toast.makeText(mContext,"最少买一件啊",Toast.LENGTH_SHORT).show();
        }
        setValue(value);

        if(onNumberChangeListener != null){
            onNumberChangeListener.onNumberChange(value);
        }
    }

    /*
    增加数量
     */
    private void addNumber() {
        if(value < maxValue){
            value ++;
        }else {
            Toast.makeText(mContext,"商品没有库存了",Toast.LENGTH_SHORT).show();
        }
        setValue(value);

        if(onNumberChangeListener != null){
            onNumberChangeListener.onNumberChange(value);
        }
    }

    public int getValue() {
        String valueStr = tv_value.getText().toString().trim();
        if(!TextUtils.isEmpty(valueStr)){
            value = Integer.parseInt(valueStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value + "");
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    //写个接口 获取布局里面的value值
    /*
    当数量发送变化的时候回调
     */
    public interface OnNumberChangeListener{
        public void onNumberChange(int value);
    }

    private OnNumberChangeListener onNumberChangeListener;

    //由外界实例化传回来
    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }
}
