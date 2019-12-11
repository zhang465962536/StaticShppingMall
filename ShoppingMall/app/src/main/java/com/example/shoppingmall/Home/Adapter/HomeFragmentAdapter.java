package com.example.shoppingmall.Home.Adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.Currency.GoodsInfoActivity;
import com.example.shoppingmall.Currency.MyApplication;
import com.example.shoppingmall.Home.Bean.GoodsBean;
import com.example.shoppingmall.Home.Bean.ResultBeanData;
import com.example.shoppingmall.R;
import com.example.shoppingmall.SAX.Chanel;
import com.example.shoppingmall.SAX.SAXUtil;
import com.example.shoppingmall.Utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.nio.channels.Channel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    //ViewHolder 超过一个 可以不写<viewHolder>
    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;

    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;
    /**
     * 活动类型
     */
    public static final int ACT = 2;
    /**
     * 秒杀类型
     */
    public static final int SECKILL = 3;
    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;
    private static final String GOODS_BEAN = "goodsBean";


    private int currentType = 0; //当前类型默认为0

    private Context mContext;
    /*
    数据
    * */
    private ResultBeanData.ResultBean resultBean;

    /*
    初始化布局
    */
    private LayoutInflater mLayoutInflater;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;

        mLayoutInflater = LayoutInflater.from(mContext);

    }


    /**
     * 相当于ListView BaseAdapter 的 getview() 创建ViewHolder
     *
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        }else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        }else if(viewType == RECOMMEND ){
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
        }else if(viewType == HOT){
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    /**
     * 相当于geiView当中的 绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData();
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
           channelViewHolder.setData();
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
           actViewHolder.setData();
        }else if(getItemViewType(position) == SECKILL){
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
           seckillViewHolder.setData();
        }else if(getItemViewType(position) == RECOMMEND){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData();
        }else if(getItemViewType(position)==HOT){
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData();
        }
    }


    class HotViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;
        private HotGridViewAdapter adapter;
        public HotViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gv_hot = itemView.findViewById(R.id.gv_hot);
            tv_more_hot = itemView.findViewById(R.id.tv_more_hot);
        }


        public void setData() {
            adapter = new HotGridViewAdapter(mContext);
            gv_hot.setAdapter(adapter);

            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position = " + position,Toast.LENGTH_SHORT).show();
                    //热卖商品信息类
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price("95.60");
                    goodsBean.setFigure(MyApplication.getResourcesUri(R.drawable.hot1));
                    goodsBean.setName("正版 Android Studio开发实战：从零基础到App上线(第2版)  Android开发实战教程App开发程序员业余爱好者Android开发秘籍书籍");
                    goodsBean.setProduct_id("3");
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;
        public RecommendViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_recommend = itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = itemView.findViewById(R.id.gv_recommend);


        }

          public void setData(){
            adapter = new RecommendGridViewAdapter(mContext);
            gv_recommend.setAdapter(adapter);

              gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      Toast.makeText(mContext,"position = " + position,Toast.LENGTH_SHORT).show();
                    //推荐商品信息类
                      GoodsBean goodsBean = new GoodsBean();
                      goodsBean.setCover_price("30.00");
                      goodsBean.setFigure(MyApplication.getResourcesUri(R.drawable.recommend1));
                      goodsBean.setName("南极人全棉决明子枕头单人荞麦皮护颈椎枕双人枕芯一对装成人家用");
                      goodsBean.setProduct_id("2");
                      startGoodsInfoActivity(goodsBean);
                  }
              });
        }
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private TextView tv_more_seckill;
        private TextView tv_time_seckill;
        private RecyclerView rv_seckill;
        private SeckillRecyclerViewAdapter adapter;

        // 相差多少事件  单位 ms
        private long dt = 0;
        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt = dt - 1000;
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                String time = formatter.format(new Date(dt));
                tv_time_seckill.setText(time);

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                if(dt <= 0){
                    //把消息移除
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };

        public SeckillViewHolder(Context mContext, View itemView) {
            super(itemView);
            tv_more_seckill = itemView.findViewById(R.id.tv_more_seckill);
            tv_time_seckill = itemView.findViewById(R.id.tv_time_seckill);
            rv_seckill = itemView.findViewById(R.id.rv_seckill);
            this.mContext = mContext;
        }

        public void setData() {
            //1.得到数据了
            //2.设置数据：文本和RecyclerView的数据
            adapter = new SeckillRecyclerViewAdapter(mContext);
            rv_seckill.setAdapter(adapter);

            // RecyclerView 想要适配器有作用 必须设置 布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));

            //设置Item的点击事件
            adapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.onSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext,"秒杀 = " + position,Toast.LENGTH_SHORT).show();
                    //秒杀商品信息类
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price("250.00");
                    goodsBean.setFigure(MyApplication.getResourcesUri(R.drawable.seckill1));
                    goodsBean.setName("Nike耐克外套女羽绒棉服男加厚保暖夹克情侣运动BF防风休闲上衣潮");
                    goodsBean.setProduct_id("1");

                    startGoodsInfoActivity(goodsBean);
                }
            });

            //秒杀倒计时 相差时间 ms
                dt =  585855 - 6666;
            handler.sendEmptyMessageDelayed(0,1000);
    }
    }
    class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = itemView.findViewById(R.id.act_viewpager);
        }

        public void setData() {
            //设置图片之间的间距
            act_viewpager.setPageMargin(20);
            act_viewpager.setOffscreenPageLimit(3);//>=3

            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new ScaleInTransformer());
            //设置 ViewPager适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return 2;
                }

                /**
                 *
                 * @param view  页面
                 * @param object instantiateItem()返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    //判断当前view和object 是否是同一对象
                    return view == object;
                }

                /**
                 *
                 * @param container 本身就算viewpager
                 * @param position  对应页面的位置
                 * @return
                 */
                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(mContext).load(MyApplication.getResourcesUri(R.drawable.act1)).into(imageView);
                    //添加到容器中
                    container.addView(imageView);

                    //对图片设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "position ==" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return imageView;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gv_channel = itemView.findViewById(R.id.gv_channel);

            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position ==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData() {
            //设置 GridView 适配器
            List<Chanel> list = SAXUtil.parseSaxXml(mContext);
            adapter = new ChannelAdapter(mContext,list);
            gv_channel.setAdapter(adapter);
        }


    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }


        public void setData() {
            //设置Banner的数据

            //得到图片集合地址
            List<String> imagesUrl = new ArrayList<>();

          imagesUrl.add(getResourcesUri(R.drawable.banner1));
          imagesUrl.add(getResourcesUri(R.drawable.banner2));
          imagesUrl.add(getResourcesUri(R.drawable.banner3));


            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            banner.setImages(imagesUrl);
            //banner设置方法全部调用完毕时最后调用
            banner.start();

            //设置Item的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position ==" + position, Toast.LENGTH_SHORT).show();
                    //轮播图商品信息类
                    GoodsBean goodsBean = new GoodsBean();
                    //startGoodsInfoActivity();
                }
            });
        }
    }

    //启动商品详情页面
    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        mContext.startActivity(intent);

    }

    /**
     * 得到Item类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    // 总共有多少个Item
    @Override
    public int getItemCount() {
        //开发过程 1-》6
        return 6;
    }

    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = mContext.getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        Log.e("uriPath",uriPath);
        return uriPath;
    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }
}
