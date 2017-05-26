package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.ISendTweet;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class SendTweetUtil implements ISendTweet {

    private SendTweetUtil(){}
    private static  SendTweetUtil newsUtil = new SendTweetUtil();
    public static SendTweetUtil getInstance(){
        return newsUtil;
    }


    @Override
    public void sendMsg(String uid, String msg, String img, String amr, ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("msg",msg);

        VolleyUtil.getInstance().doPOST(Urls.SENDMSGURL,map,iCallBack);
    }
}
