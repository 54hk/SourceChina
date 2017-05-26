package practice.code.com.sourcechina.fragment;

import android.util.Log;
import android.view.View;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.model.HttpUtil.LoginUtil;
import practice.code.com.sourcechina.model.HttpUtil.ReviewUtils;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.db.UserBean;
import practice.code.com.sourcechina.model.db.UserDBOpenHelper;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class ReviewFragment  extends BaseFragemnt implements ICallBack{

    private String id;
    private String catalog;
    private String content;
    private String uid;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.review_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {

        id = getParmes().getString("id");
        catalog = getParmes().getString("catalog");
        content = getParmes().getString("content");
        try {
            UserDBOpenHelper helper = new UserDBOpenHelper(APP.activity);
            Dao<UserBean, Integer> dao = helper.getUserDao();
            List<UserBean> userBeen = dao.queryForAll();
            uid = userBeen.get(userBeen.size() - 1).getUid();


        } catch (SQLException e) {
            e.printStackTrace();
        }


         Log.d("ReviewFragment",id + ","+catalog + ","+ content+ "," + uid);
    }
        @Override
    protected void getLoad(boolean b) {
        ReviewUtils.getInstance().getReviewMethod(catalog,id,uid,content,this);

    }

    @Override
    protected void sendData() {

    }

    @Override
    public void success(String mgs) {
        Log.d("ReviewFragment", mgs);
    }

    @Override
    public void fail(String mgs) {
        Log.d("ReviewFragment", mgs);
    }
}
