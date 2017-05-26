package practice.code.com.sourcechina.model.bis;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public interface IAction {
//    http://www.oschina.net/action/api/event_list?pageIndex=1&uid=-1&pageSize=10
    void getActonMethod(String uid,String pageIndex ,ICallBack iCallBack);

}
