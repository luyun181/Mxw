package cn.huanyan.mxwvideo.utils;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.huanyan.mxwvideo.cookie.CookieJarImpl;
import cn.huanyan.mxwvideo.cookie.store.PersistentCookieStore;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
    public static OkHttpClient getSelfClient(Context mContext, final String token) {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(mContext);
        CookieJarImpl cookieJarImpl = new CookieJarImpl(persistentCookieStore);
        httpBuilder.cookieJar(cookieJarImpl);
        httpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request authorization = original.newBuilder().header("Authorization", token)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(authorization);
            }
        });
        OkHttpClient client = httpBuilder.readTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS) //设置超时
                .build();
        return client;
    }


}
