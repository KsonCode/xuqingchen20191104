package com.bwie.xuqingchen20191104.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 单例模式：网络工具类
 */
public class OkhttpUtils {

    private Handler handler = new Handler();

    private static  OkhttpUtils mInstance;
    private OkHttpClient okHttpClient;
    private OkhttpUtils(){
        //创建日志拦截器对象
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设置拦截器的请求级别，4个级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)//日志拦截拦截器,应用拦截器
                .addNetworkInterceptor(httpLoggingInterceptor)//网络拦截器
                .build();

    }

    /**
     * 双重检验锁
     * @return
     */
    public static OkhttpUtils getInstance(){

        if (mInstance==null){//第一重
            synchronized (OkhttpUtils.class){
                if (mInstance==null){
                    mInstance = new OkhttpUtils();
                }
            }
        }

        return mInstance;
    }

    /**
     * get方式
     */
    public void doGet(String url,NetCallback netCallback){

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("userId","2018")
                .addHeader("sessionId","20191103")
                .build();

        callData(request,netCallback);


    }

    /**
     * 核心请求方法
     */
    private void callData(Request request, final NetCallback netCallback) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                if (netCallback!=null){
                    netCallback.failure("网络异常");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (200==response.code()){
                    final String result = response.body().string();


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (netCallback!=null){
                                netCallback.success(result);
                            }
                        }
                    });

                }



            }
        });
    }

    /**
     * post方式请求
     * @param url
     * @param params
     * @param netCallback
     */
    public void doPost(String url, HashMap<String,String> params,NetCallback netCallback){

        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String, String> p : params.entrySet()) {

            String key = p.getKey();
            String value = p.getValue();

            builder.add(key,value);
        }

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("userId","2018")
                .addHeader("sessionId","20191103")
                .build();

        callData(request, netCallback);
    }
}
