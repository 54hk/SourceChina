package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.INewDetails;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class TweetDetailsUtil implements INewDetails {

    private TweetDetailsUtil(){}
    private static  TweetDetailsUtil newsUtil = new TweetDetailsUtil();
    public static TweetDetailsUtil getInstance(){
        return newsUtil;
    }


    @Override
    public void getLoadDetails(String id, ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("id",id);

        VolleyUtil.getInstance().doGET(Urls.TWEETURL,map,iCallBack);
    }

    @Override
    public void getTweetDetails(String id,String pageIndex, ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put(pageIndex,pageIndex);
        map.put("pageSize",String.valueOf(Urls.PAGESIZE));


        VolleyUtil.getInstance().doGET(Urls.TWEETDETAILURL,map,iCallBack);
    }
}
