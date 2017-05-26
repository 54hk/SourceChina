package practice.code.com.sourcechina.model.bis;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public interface IHttp {

    void doGET(String url, Map<String,String> map , ICallBack iCallBack);

    void doPOST(String url ,  Map<String,String> map , ICallBack iCallBack);

    void doPOSTImg(String url , File file , String uid, ICallBack iCallBack);

}
