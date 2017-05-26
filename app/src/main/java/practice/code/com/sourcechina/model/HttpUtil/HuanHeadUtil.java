package practice.code.com.sourcechina.model.HttpUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.IHuanHead;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class HuanHeadUtil implements IHuanHead {

    private HuanHeadUtil(){};
    private static HuanHeadUtil myMessageUtil = new HuanHeadUtil();

    public  static HuanHeadUtil getInstance(){
        return myMessageUtil;
    }



    @Override
    public void huanHead(String uid,  File file  , ICallBack iCallBack) {



        VolleyUtil.getInstance().doPOSTImg( Urls.HUANHEAD,file ,uid,iCallBack );

    }
}
