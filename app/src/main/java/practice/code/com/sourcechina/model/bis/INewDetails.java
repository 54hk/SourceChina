package practice.code.com.sourcechina.model.bis;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public interface INewDetails {

    void getLoadDetails(String id , ICallBack iCallBack);
    void getTweetDetails(String id ,String pageIndex, ICallBack iCallBack);

}
