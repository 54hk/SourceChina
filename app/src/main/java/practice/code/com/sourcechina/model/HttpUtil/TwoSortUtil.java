package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.ITwoSort;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class TwoSortUtil implements ITwoSort {

    private TwoSortUtil(){}
    private static  TwoSortUtil newsUtil = new TwoSortUtil();
    public static TwoSortUtil getInstance(){
        return newsUtil;
    }

    @Override
    public void onSort(String tag,ICallBack iCallBack) {

        Map<String,String> map = new HashMap<>();
        map.put("tag",tag);
        VolleyUtil.getInstance().doGET(Urls.TWOSORTURL,map,iCallBack);

    }



    @Override
    public void onThreeSort(String searchTag,String pageIndex , ICallBack iCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("searchTag",searchTag);
        map.put("pageIndex",pageIndex);
        map.put("pageSize",Urls.PAGESIZE + "");
        VolleyUtil.getInstance().doGET(Urls.THREESORTURL,map,iCallBack);

    }
}
