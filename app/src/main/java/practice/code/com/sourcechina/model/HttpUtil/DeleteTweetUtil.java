package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IDeleteTweet;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class DeleteTweetUtil implements IDeleteTweet{

    private DeleteTweetUtil(){}
    private static  DeleteTweetUtil newsUtil = new DeleteTweetUtil();
    public static DeleteTweetUtil getInstance(){
        return newsUtil;
    }

    @Override
    public void deleteTweetMethod(String uid, String tweetid, ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("tweetid",tweetid);



        VolleyUtil.getInstance().doPOST(Urls.DELETETWEETURL,map,iCallBack);
    }
}
