package practice.code.com.sourcechina.fragment.messagecenter;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.model.HttpUtil.MYMessageUtil;
import practice.code.com.sourcechina.model.HttpUtil.NewsTweetUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.db.UserBean;
import practice.code.com.sourcechina.model.db.UserDBOpenHelper;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class MyAFragment extends BaseFragemnt implements ICallBack{

    private String uid;
    private List<UserBean> userBeen;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.maragment_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {



        try {
            UserDBOpenHelper helper = new UserDBOpenHelper(APP.activity);
            Dao<UserBean, Integer> dao = null;
            dao = helper.getUserDao();
            userBeen = dao.queryForAll();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        uid = userBeen.get(userBeen.size() - 1).getUid();

    }

    @Override
    protected void getLoad(boolean b) {
        if(uid != null){
            MYMessageUtil.getInstance().getMessage(uid , String.valueOf(3), String.valueOf(1),this);
//            NewsTweetUtil.getInstance().getUserMessage(uid,this);
        }else{
            Log.d("MyAFragment", uid);
        }
    }

    @Override
    protected void sendData() {

    }

    @Override
    public void success(String mgs) {

        Toast.makeText(APP.context, ""+mgs, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail(String mgs) {

    }
}
