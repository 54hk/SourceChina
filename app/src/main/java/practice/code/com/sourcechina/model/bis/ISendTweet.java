package practice.code.com.sourcechina.model.bis;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public interface ISendTweet {

    void sendMsg(String uid,String msg,String img,String amr,ICallBack iCallBack);

}
