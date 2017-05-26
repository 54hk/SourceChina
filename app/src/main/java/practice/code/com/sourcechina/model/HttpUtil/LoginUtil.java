package practice.code.com.sourcechina.model.HttpUtil;

import android.util.Log;

import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.bis.ILogin;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class LoginUtil implements ILogin {

    private LoginUtil(){}
    private static LoginUtil loginUtil = new LoginUtil();
    public static LoginUtil getInstance(){
        return loginUtil;
    }


    @Override
    public void goLogin(String usename, String password, ICallBack callBack) {
        //        String url, Map<String, String> map, final ICallBack callBack)
        Map<String,String> map = new HashMap<>();
        map.put("username",usename);
        map.put("pwd",password);
        map.put("keep_login", Urls.KEEP_LOGIN+"");
        Log.e("LoginFragment",usename+password);
//        GetCookie.getInstance().doPOST(Urls.LOGINURL,map,callBack);
//        GetCookie.getInstance().doPOST(Urls.LOGINURL,map,callBack);

    }
}
