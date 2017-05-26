package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IMyTweet;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class MyTweetUtil implements IMyTweet {


    private MyTweetUtil(){}
    private static  MyTweetUtil newsUtil = new MyTweetUtil();
    public static MyTweetUtil getInstance(){
        return newsUtil;
    }


    @Override
    public void openMyTweetMessage(String uid,String pageIndex, ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("pageIndex",pageIndex);
        map.put("pageSize", 5+ "");
        VolleyUtil.getInstance().doGET(Urls.MYTWEETURL,map,iCallBack);
    }
}
