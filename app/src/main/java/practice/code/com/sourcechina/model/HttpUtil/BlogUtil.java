package practice.code.com.sourcechina.model.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.IBlogs;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class BlogUtil implements IBlogs {

    private BlogUtil(){}
    private static  BlogUtil blogUtil = new BlogUtil();
    public static BlogUtil getInstance(){
        return blogUtil;
    }

    @Override
    public void getNewsList(String latest,int pageIndex, ICallBack iCallBack) {
//        String url, Map<String,String> map
        Map<String,String> map = new HashMap<>();
        map.put("type", latest);
        map.put("pageIndex",pageIndex + "");
        map.put("pageSize",Urls.PAGESIZE+"");
        VolleyUtil.getInstance().doGET(Urls.BLOGURL,map,iCallBack);
    }
}
