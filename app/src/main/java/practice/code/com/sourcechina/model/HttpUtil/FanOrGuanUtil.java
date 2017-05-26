package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IFanOrGuan;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class FanOrGuanUtil implements IFanOrGuan {

    private FanOrGuanUtil(){};
    private static FanOrGuanUtil fanOrGuanUtil = new FanOrGuanUtil();
    public static FanOrGuanUtil getInstance(){
        return  fanOrGuanUtil;
    }

    @Override
    public void getFanOrGuan(String uid, String relation, String pageIndex, ICallBack iCallBack) {


        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("relation",relation);
        map.put("pageIndex",pageIndex);
        map.put("pageSize",Urls.PAGESIZE + "");
        VolleyUtil.getInstance().doGET(Urls.FANORGUANURL ,map , iCallBack);
    }
}
