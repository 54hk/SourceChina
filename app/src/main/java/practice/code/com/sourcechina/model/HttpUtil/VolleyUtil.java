package practice.code.com.sourcechina.model.HttpUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;


import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.entity.LoginEntity;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IHttp;
import practice.code.com.sourcechina.util.ClearCookiejar;


/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class VolleyUtil implements IHttp {

    private String cookie = null;
    private OkHttpClient okHttpClient;
    //    private OkHttpClient.Builder okHttpBuild;
//    private OkHttpClient client;




    private VolleyUtil(){
        getshared();
        Log.d("VolleyUtil", cookie);
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.MINUTES)
                .build();




//        okHttpBuild = new OkHttpClient.Builder();
//        client = okHttpBuild.build();

    }
    private static VolleyUtil volleyUtil = new VolleyUtil();
    public static VolleyUtil getInstance(){
        return volleyUtil;
    }

    public void getshared(){
        SharedPreferences preferences = APP.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        cookie = preferences.getString("cookie", "");
    }

    @Override
    public void doGET(String url, Map<String,String> map, final ICallBack iCallBack) {


        StringBuffer sb=new StringBuffer();
        sb.append("?");
        if (map!=null){
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                String value = map.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url=url+sb.toString().substring(0,sb.length()-1);
            Log.e("url",url);
        }
        Request request=new Request.Builder().addHeader("Cookie",cookie).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.fail(e.toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.success(string);
                    }
                });
            }
        });











    }
    @Override
    public void doPOST(String url, Map<String, String> map, final ICallBack iCallBack) {



        FormBody.Builder builder=new FormBody.Builder();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String value = map.get(key);
            builder.add(key,value);
        }
        Request request=new Request.Builder().addHeader("Cookie",cookie).url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.fail(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.success(string);
                    }
                });
            }
        });


    }

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    @Override
    public void doPOSTImg(String url, File file  ,String uid, final ICallBack iCallBack) {


        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        builder.addFormDataPart("portrait", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
        builder.addFormDataPart("uid" ,uid);
        MultipartBody requestBody = builder.build();

        Request request = new Request.Builder().addHeader("Cookie", cookie).url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.fail(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.success(string);
                    }
                });
            }
        });

    }



    }
