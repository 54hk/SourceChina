package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.entity.FindWhatBean;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IFindWhat;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class FindWhatUtil implements IFindWhat {


    private FindWhatUtil(){}
    private static  FindWhatUtil newsUtil = new FindWhatUtil();
    public static FindWhatUtil getInstance(){
        return newsUtil;
    }

    @Override
    public void getFindWhat(String catalog, String content,String pageIndex , ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("catalog",catalog);
        map.put("content",content);
        map.put("pageIndex",pageIndex);
        map.put("pageSize",Urls.PAGESIZE + "");


        VolleyUtil.getInstance().doGET(Urls.FINDWHATURL,map,iCallBack);
    }

    @Override
    public void getFindUser(String content, ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();

        map.put("content",content);
        VolleyUtil.getInstance().doGET(Urls.FINDUSERURL,map,iCallBack);
    }

}
