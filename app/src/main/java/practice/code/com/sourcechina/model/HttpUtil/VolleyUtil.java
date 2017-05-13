package practice.code.com.sourcechina.model.HttpUtil;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;
import java.util.Set;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IHttp;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class VolleyUtil implements IHttp {

    private VolleyUtil(){}
    private static VolleyUtil volleyUtil = new VolleyUtil();
    public static VolleyUtil getInstance(){
        return volleyUtil;
    }

    @Override
    public void doGET(String url, Map<String,String> map, final ICallBack iCallBack) {
        RequestQueue requestQueue = Volley.newRequestQueue(APP.activity);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");
        if(map != null){
            Set<String> strings = map.keySet();
            for(String key :strings){
                String value = map.get(key);
                stringBuffer.append(key).append("=").append(value).append("&");
            }
            url = url + stringBuffer.toString().substring(0,stringBuffer.length()-1);
        }
        
        
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("okhttp",response);
                iCallBack.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("okhttp",error.toString());
                iCallBack.fail(error.toString());
            }
        });
        requestQueue.add(stringRequest);

    }

    @Override
    public void doPOST(String url ,  Map<String,String> map, ICallBack iCallBack) {

    }
}
