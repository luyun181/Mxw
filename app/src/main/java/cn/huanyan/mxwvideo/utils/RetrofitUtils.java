package cn.huanyan.mxwvideo.utils;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.huanyan.mxwvideo.cookie.CookieJarImpl;
import cn.huanyan.mxwvideo.cookie.store.PersistentCookieStore;
import cn.huanyan.mxwvideo.interf.ApiService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/11/22.
 */

public class RetrofitUtils {
    private String BASE_URL = "http://mxw.ihuanyan.cn";
    private static final int DEFAULT_TIMEOUT = 5;
    private static RetrofitUtils mRe;
    private Retrofit retrofit;
    private ApiService apiService;

    private RetrofitUtils(Context mContext) {
        OkHttpClient.Builder httpBuilder=new OkHttpClient.Builder();
        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(mContext);
        CookieJarImpl cookieJarImpl = new CookieJarImpl(persistentCookieStore);
        httpBuilder.cookieJar(cookieJarImpl);

        OkHttpClient client=httpBuilder.readTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS) //设置超时
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public synchronized static RetrofitUtils getInstance(Context mContext) {
        if (mRe == null) {
            mRe = new RetrofitUtils(mContext);
        }
        return mRe;
    }
    public <S> S create(Class<S> service) {
        return retrofit.create(service);
    }

    public static void desRetrofit(){
        if (mRe!=null){
            mRe = null;
        }
    }
}
