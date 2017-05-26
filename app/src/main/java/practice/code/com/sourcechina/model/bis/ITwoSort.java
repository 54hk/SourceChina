package practice.code.com.sourcechina.model.bis;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public interface ITwoSort {

    void onSort(String tag,ICallBack iCallBack);
    void onThreeSort(String searchTag ,String pageIndex , ICallBack iCallBack);

}
