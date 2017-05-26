package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.IAction;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class ActionUtil implements IAction {

    private ActionUtil(){}
    private static  ActionUtil newsUtil = new ActionUtil();
    public static ActionUtil getInstance(){
        return newsUtil;
    }




    @Override
    public void getActonMethod(String uid, String pageIndex, ICallBack iCallBack) {

        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("pageIndex",pageIndex);
        map.put("pageSize",Urls.PAGESIZE + "");

        VolleyUtil.getInstance().doGET(Urls.XIANXIAACTION,map,iCallBack);

    }
}
