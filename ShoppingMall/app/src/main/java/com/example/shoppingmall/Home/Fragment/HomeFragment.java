package com.example.shoppingmall.Home.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.shoppingmall.Base.BaseFragment;
import com.example.shoppingmall.Home.Adapter.HomeFragmentAdapter;
import com.example.shoppingmall.Home.Bean.ResultBeanData;
import com.example.shoppingmall.R;
import com.example.shoppingmall.Utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Request;

//主页面 fragment
public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private HomeFragmentAdapter adapter;
    //返回的数据
    ResultBeanData.ResultBean resultBean;


    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);

        //设置点击事件
        initListener();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "主页面的fragment的数据被初始化了");
        //联网请求主页的数据
        //getDataFromNet();

        adapter = new HomeFragmentAdapter(mContext,resultBean);
        rvHome.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        //设置跨度大小的监听
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //如果当前位置recyclerView 小于3 隐藏图片
                if(position <= 3){
                    ib_top.setVisibility(View.GONE);
                }else { //否则显示
                    ib_top.setVisibility(View.VISIBLE);
                }
                // return 1; 表示得到跨度的大小  只能返回1
                return 1;
            }
        });
        //设置布局管理者
        rvHome.setLayoutManager(manager);
    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    /**
                     * 当请求失败时候 回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "首页请求失败====" + e.getMessage());
                    }

                    /**
                     * 当联网请求成功 回调
                     * @param response 请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "首页请求成功====" + response);
                        //解析数据
                        processData(response);
                        
                    }

                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);

        resultBean = resultBeanData.getResult();
        if(resultBean != null){
            ///有数据  设置适配器
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rvHome.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            //设置跨度大小的监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    //如果当前位置recyclerView 小于3 隐藏图片
                    if(position <= 3){
                        ib_top.setVisibility(View.GONE);
                    }else { //否则显示
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    // return 1; 表示得到跨度的大小  只能返回1
                    return 1;
                }
            });
            //设置布局管理者
            rvHome.setLayoutManager(manager);
        }else {
            //没有数据

        }

        Log.e(TAG,"解析成功===" + resultBean.getHot_info().get(0).getName());
    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });

        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
