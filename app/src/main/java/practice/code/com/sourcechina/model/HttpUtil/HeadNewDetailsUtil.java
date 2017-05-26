package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.INewDetails;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class HeadNewDetailsUtil implements INewDetails {

    private HeadNewDetailsUtil(){}
    private static  HeadNewDetailsUtil newsUtil = new HeadNewDetailsUtil();
    public static HeadNewDetailsUtil getInstance(){
        return newsUtil;
    }


    @Override
    public void getLoadDetails(String id, ICallBack iCallBack) {

        Map<String,String> map = new HashMap<>();
        map.put("id",id);

       VolleyUtil.getInstance().doGET(Urls.NEWDETAILS,map,iCallBack);

    }

    @Override
    public void getTweetDetails(String id, String pageIndex, ICallBack iCallBack) {

    }


}
