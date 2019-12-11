package com.example.shoppingmall.Currency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.Home.Bean.GoodsBean;
import com.example.shoppingmall.R;
import com.example.shoppingmall.Shoppingcart.Utils.CartStorage;
import com.example.shoppingmall.Utils.Constants;

import java.io.Serializable;

public class GoodsInfoActivity extends Activity implements View.OnClickListener {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;

    private TextView tvMoreShare;
    private TextView tvMoreSearch;
    private TextView tvMoreHome;
    private Button btnMore;

    private GoodsBean goodsBean;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        //初始化控件
        findViews();

        //接收数据
         goodsBean = (GoodsBean) getIntent().getSerializableExtra("goodsBean");
        if(goodsBean != null){
            Toast.makeText(this,"goodsBean  =  " + goodsBean.toString(),Toast.LENGTH_SHORT).show();
            setDataForView(goodsBean);
        }
    }

    //设置数据
    private void setDataForView(GoodsBean goodsBean) {
        //设置图片
        Glide.with(this).load(goodsBean.getFigure()).into(ivGoodInfoImage);
        //设置文本
        tvGoodInfoName.setText(goodsBean.getName());
        //设置价格
        tvGoodInfoPrice.setText("￥"+goodsBean.getCover_price());

        //根据产品号添加详情页面
        //setWebViewData(goodsBean.getProduct_id());
    }


    private void setWebViewData(String product_id) {
        if(product_id != null){
            wbGoodInfoMore.loadUrl("https://uland.taobao.com/sem/tbsearch?refpid=mm_26632258_3504122_32538762&keyword=%E5%A5%B3%E8%A3%85&clk1=d35c493e3e6ebf6128dcb71be57f8914&upsid=d35c493e3e6ebf6128dcb71be57f8914");

            WebSettings webSettings = wbGoodInfoMore.getSettings();
            webSettings.setUseWideViewPort(true); //支持双击放大 变小
            webSettings.setJavaScriptEnabled(true); //WebView内容支持javaScripte
            //优先使用缓存
            wbGoodInfoMore.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

            wbGoodInfoMore.setWebViewClient(new WebViewClient(){

                //返回值是true 的时候控制去WebView打开，为false调用系统或者第三方浏览器
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }
            });
        }
    }

    private void findViews() {
        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        tvGoodInfoStore = (TextView)findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
        wbGoodInfoMore = (WebView)findViewById( R.id.wb_good_info_more );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );

        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );

        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        tvMoreShare = (TextView)findViewById( R.id.tv_more_share );
        tvMoreSearch = (TextView)findViewById( R.id.tv_more_search );
        tvMoreHome = (TextView)findViewById( R.id.tv_more_home );
        btnMore = (Button)findViewById( R.id.btn_more );

        btnMore.setOnClickListener( this );


        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );
        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            finish();
        } else if ( v == ibGoodInfoMore ) {
            Toast.makeText(this,"更多",Toast.LENGTH_SHORT).show();
        } else if ( v == btnGoodInfoAddcart ) {
            CartStorage.getInstance().addData(goodsBean);
            Log.e("TAG",goodsBean.toString());
            Toast.makeText(this,"添加到购物车成功了",Toast.LENGTH_SHORT).show();
        }else if (v == tvGoodInfoCallcenter) {
            Toast.makeText(this, "客户中心", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCollection) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCart) {
            Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
        } else if (v == tvMoreShare) {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == tvMoreSearch) {
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        } else if (v == tvMoreShare) {
            Toast.makeText(this, "主页面", Toast.LENGTH_SHORT).show();
        }
    }

}
