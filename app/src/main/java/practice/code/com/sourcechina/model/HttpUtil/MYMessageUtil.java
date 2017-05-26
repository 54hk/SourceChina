package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IMYMessge;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class MYMessageUtil implements IMYMessge {

    private MYMessageUtil(){};
    private static MYMessageUtil myMessageUtil = new MYMessageUtil();

    public  static MYMessageUtil getInstance(){
        return myMessageUtil;
    }


    @Override
    public void getMessage(String uid, String catalog , String pageIndex, ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("uid","uid");
        map.put("catalog","catalog");
        map.put("pageIndex","pageIndex");
        map.put("pageSize",Urls.PAGESIZE  + "");
        VolleyUtil.getInstance().doGET(Urls.IIMESSAGEURL , map, iCallBack);
    }
}
