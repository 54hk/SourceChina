package practice.code.com.sourcechina.model.bis;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public interface IBlogs {

    void getNewsList(String latest,int pageIndex,ICallBack iCallBack);
}
