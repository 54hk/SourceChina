package practice.code.com.sourcechina.model.bis;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public interface INewsTweet {

    void getNewsMessage(String pageIndex,String uid,ICallBack iCallBack);
    void getUserMessage(String uid,ICallBack iCallBack);
}
