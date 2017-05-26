package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IGreat;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class IGreatUtil implements IGreat {
    private IGreatUtil(){};
    private static IGreatUtil myMessageUtil = new IGreatUtil();

    public  static IGreatUtil getInstance(){
        return myMessageUtil;
    }


    @Override
    public void getGreat(String catalog, String id, String pageIndex, ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("catalog" , catalog);
        map.put("id" , id);
        map.put("pageIndex" , pageIndex);
        map.put("pageSize" , String.valueOf(Urls.PAGESIZE));
        VolleyUtil.getInstance().doGET(Urls.COMMENTLIST,map,iCallBack);
    }
}
