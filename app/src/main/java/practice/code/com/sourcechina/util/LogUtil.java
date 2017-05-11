package practice.code.com.sourcechina.util;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class LogUtil  {

    private static  boolean aBoolean = true;

    public static void e(String msg){
        if(aBoolean){
            LogUtil.e(msg);
        }
    }


}
