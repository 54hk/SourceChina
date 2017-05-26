package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IReview;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class ReviewUtils implements IReview{

    private ReviewUtils(){}
    private static  ReviewUtils newsUtil = new ReviewUtils();
    public static ReviewUtils getInstance(){
        return newsUtil;
    }


    @Override
    public void getReviewMethod(String catalog, String id, String uid, String cotent,  ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("isPostToMyZone","");
        map.put("content","cotent");
        map.put("uid","uid");
        map.put("id",id);
        map.put("catalog",catalog);

        VolleyUtil.getInstance().doPOST(Urls.REVIEWURL,map,iCallBack);

    }
}
