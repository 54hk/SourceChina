package practice.code.com.sourcechina.model.bis;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public interface IFindWhat {

    void getFindWhat(String catalog , String content ,String pageIndex , ICallBack iCallBack);
    void getFindUser( String content ,ICallBack iCallBack);
}
