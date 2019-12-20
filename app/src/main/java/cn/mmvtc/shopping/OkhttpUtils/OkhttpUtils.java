package cn.mmvtc.shopping.OkhttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpUtils {
        private OkHttpClient okHttpClient;
        public OkhttpUtils(){
            okHttpClient=new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5,TimeUnit.SECONDS)
            .readTimeout(5,TimeUnit.SECONDS)
            .build();
        }
        public  void sendSendRequestWithOkhttp(String url, Callback callback){
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okHttpClient.newCall(request).enqueue(callback);
        }
}
