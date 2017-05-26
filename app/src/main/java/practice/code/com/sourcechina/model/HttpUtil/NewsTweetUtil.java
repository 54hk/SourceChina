package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.INewsTweet;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class NewsTweetUtil implements INewsTweet{

    private NewsTweetUtil(){}
    private static  NewsTweetUtil newsUtil = new NewsTweetUtil();
    public static NewsTweetUtil getInstance(){
        return newsUtil;
    }


    @Override
    public void getNewsMessage(String pageIndex,String uid, ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("pageIndex",pageIndex);
        map.put("pageSize", Urls.PAGESIZE + "");
        VolleyUtil.getInstance().doGET(Urls.NEWSTWEET,map,iCallBack);
    }

    @Override
    public void getUserMessage(String uid, ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        VolleyUtil.getInstance().doGET(Urls.USERNAMEURL,map,iCallBack);
    }
}
