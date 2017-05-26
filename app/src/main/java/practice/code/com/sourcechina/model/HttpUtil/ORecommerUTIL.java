package practice.code.com.sourcechina.model.HttpUtil;

import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IORecomer;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class ORecommerUtil implements IORecomer {

    private ORecommerUtil(){}
    private static ORecommerUtil loginUtil = new ORecommerUtil();
    public static ORecommerUtil getInstance(){
        return loginUtil;
    }

    @Override
    public void onRecommenr(String searchTag,String pageIndex,ICallBack iCallBack) {

        Map<String,String> map = new HashMap<>();
        map.put("searchTag",searchTag);
        map.put("pageIndex",pageIndex );
        map.put("pageSize",Urls.PAGESIZE + "");


        VolleyUtil.getInstance().doGET(Urls.RECOMMEDURL,map,iCallBack);
    }
}
