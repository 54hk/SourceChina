package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.INews;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class NewsUtil implements INews {

    private NewsUtil(){}
    private static  NewsUtil newsUtil = new NewsUtil();
    public static NewsUtil getInstance(){
        return newsUtil;
    }


    @Override
    public void getNewsList(int pageIndex, ICallBack iCallBack) {
//        String url, Map<String,String> map
        Map<String,String> map = new HashMap<>();
        map.put("catalog", Urls.CATALOG+"");
        map.put("pageIndex",pageIndex + "");
        map.put("pageSize",Urls.PAGESIZE+"");
        VolleyUtil.getInstance().doGET(Urls.NEWURL,map,iCallBack);
    }
}
