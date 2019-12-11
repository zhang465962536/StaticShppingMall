package com.example.shoppingmall.Currency;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.DrawableRes;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    public static Context getContext() {
        return mContext;
    }

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        //初始化OkhttpUtils
        initOkhttpClient();
    }

    public static String getResourcesUri(@DrawableRes int id) {
        Resources resources = mContext.getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        Log.e("uriPath",uriPath);
        return uriPath;
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)  //连接超时
                .readTimeout(10000L, TimeUnit.MILLISECONDS)     //读取超时
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }


}
