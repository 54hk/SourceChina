package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IOpenSS;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class OSSUtil implements IOpenSS {


    private OSSUtil(){}
    private static  OSSUtil newsUtil = new OSSUtil();
    public static OSSUtil getInstance(){
        return newsUtil;
    }


    @Override
    public void sort(ICallBack iCallBack) {

        Map<String,String> map = new HashMap<>();
        map.put("type","0");
        VolleyUtil.getInstance().doGET(Urls.SORTURL,map,iCallBack);
    }
}
