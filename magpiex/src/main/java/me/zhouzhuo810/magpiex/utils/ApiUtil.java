package me.zhouzhuo810.magpiex.utils;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import me.jessyan.progressmanager.ProgressManager;
import me.zhouzhuo810.magpiex.utils.intercepter.CopyNoticeInterceptor;
import me.zhouzhuo810.magpiex.utils.intercepter.ShareNoticeInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtil {
    
    private ApiUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    
    /**
     * 创建API
     *
     * @param clazz     interface接口
     * @param baseUrl   服务器地址
     * @param timeOut   超时时间
     * @param timeUnit  超时时间单位
     * @param enableLog 是否打印日志
     * @return T 返回接口
     */
    public static <T> T createApi(Class<T> clazz, String baseUrl, int timeOut, TimeUnit timeUnit, boolean enableLog, boolean forceNoCache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(timeOut, timeUnit)
            .writeTimeout(timeOut, timeUnit)
            .readTimeout(timeOut, timeUnit);
        if (forceNoCache) {
            builder.cache(null);
            builder.addNetworkInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        //缓存0s,即不缓存
                        Response response = chain.proceed(request);
                        return response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=0")
                            .build();
                    }
                }
            );
        }
        if (enableLog) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        //注册上传或下载监听器
        OkHttpClient client = ProgressManager.getInstance().with(builder).build();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build();
        return (T) retrofit.create(clazz);
    }
    
    
    /**
     * 创建API
     *
     * @param clazz           interface接口
     * @param baseUrl         服务器地址
     * @param timeOut         超时时间
     * @param timeUnit        超时时间单位
     * @param enableLog       是否打印日志
     * @param forceNoCache    是否打印日志
     * @param logoId          通知图标
     * @param noticeChannelId 通知渠道id
     * @param noticeTitle     通知标题
     * @return T 返回接口
     */
    public static <T> T createApiWithCopyNotice(Class<T> clazz, String baseUrl, int timeOut, TimeUnit timeUnit,
                                                boolean enableLog, boolean forceNoCache,
                                                int logoId, String noticeChannelId,
                                                String noticeTitle) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(timeOut, timeUnit)
            .writeTimeout(timeOut, timeUnit)
            .readTimeout(timeOut, timeUnit);
        if (forceNoCache) {
            builder.cache(null);
            builder.addNetworkInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        //缓存0s,即不缓存
                        Response response = chain.proceed(request);
                        return response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=0")
                            .build();
                    }
                }
            );
        }
        if (enableLog) {
            builder.addInterceptor(new CopyNoticeInterceptor(logoId, noticeChannelId, noticeTitle));
        }
        //注册上传或下载监听器
        OkHttpClient client = ProgressManager.getInstance().with(builder).build();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build();
        return (T) retrofit.create(clazz);
    }
    
    
    /**
     * 创建API
     *
     * @param clazz           interface接口
     * @param baseUrl         服务器地址
     * @param timeOut         超时时间
     * @param timeUnit        超时时间单位
     * @param enableLog       是否打印日志
     * @param forceNoCache    是否打印日志
     * @param logoId          通知图标
     * @param noticeChannelId 通知渠道id
     * @param noticeTitle     通知标题
     * @return T 返回接口
     */
    public static <T> T createApiWithShareNotice(Class<T> clazz, String baseUrl, int timeOut, TimeUnit timeUnit,
                                                 boolean enableLog, boolean forceNoCache,
                                                 int logoId, String noticeChannelId,
                                                 String noticeTitle) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(timeOut, timeUnit)
            .writeTimeout(timeOut, timeUnit)
            .readTimeout(timeOut, timeUnit);
        if (forceNoCache) {
            builder.cache(null);
            builder.addNetworkInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        //缓存0s,即不缓存
                        Response response = chain.proceed(request);
                        return response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=0")
                            .build();
                    }
                }
            );
        }
        if (enableLog) {
            builder.addInterceptor(new ShareNoticeInterceptor(logoId, noticeChannelId, noticeTitle));
        }
        //注册上传或下载监听器
        OkHttpClient client = ProgressManager.getInstance().with(builder).build();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build();
        return (T) retrofit.create(clazz);
    }
    
    
}
