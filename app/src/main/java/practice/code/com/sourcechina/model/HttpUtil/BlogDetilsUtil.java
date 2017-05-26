package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.INewDetails;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class BlogDetilsUtil implements INewDetails {
    private BlogDetilsUtil(){}
    private static  BlogDetilsUtil newsUtil = new BlogDetilsUtil();
    public static BlogDetilsUtil getInstance(){
        return newsUtil;
    }


    @Override
    public void getLoadDetails(String id, ICallBack iCallBack) {

        Map<String,String> map = new HashMap<>();
        map.put("id",id);

        VolleyUtil.getInstance().doGET(Urls.BLOGSDETILS,map,iCallBack);

    }

    @Override
    public void getTweetDetails(String id, String pageIndex, ICallBack iCallBack) {

    }


}
