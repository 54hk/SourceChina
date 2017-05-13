package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IWIFINews;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class WIFINewUtil implements IWIFINews {
    private WIFINewUtil(){}
    private static  WIFINewUtil newsUtil = new WIFINewUtil();
    public static WIFINewUtil getInstance(){
        return newsUtil;
    }



    @Override
    public void getNewsList(int pageIndex, ICallBack iCallBack) {
        //        String url, Map<String,String> map
        Map<String,String> map = new HashMap<>();
        map.put("catalog", 4+"");
        map.put("pageIndex",pageIndex + "");
        map.put("pageSize",Urls.PAGESIZE+"");
        map.put("show",Urls.SHOW);
        VolleyUtil.getInstance().doGET(Urls.NEWURL,map,iCallBack);
    }
}
