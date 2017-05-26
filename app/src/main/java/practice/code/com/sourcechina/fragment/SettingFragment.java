package practice.code.com.sourcechina.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.model.db.UserBean;
import practice.code.com.sourcechina.model.db.UserDBOpenHelper;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class SettingFragment extends BaseFragemnt {
    @Bind(R.id.zhuxiano)
    Button zhuxiano;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.setting_fragment_lyout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {

    }

    @Override
    protected void getLoad(boolean b) {

    }

    @Override
    protected void sendData() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.zhuxiano)
    public void onViewClicked() {
        UserDBOpenHelper helper = new UserDBOpenHelper(APP.activity);
        try {
            Dao<UserBean, Integer> dao =  helper.getUserDao();
            List<UserBean> userBeen = dao.queryForAll();
            for(int i  = 0; i<userBeen.size();i++){
                dao.delete(userBeen.get(i));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        SharedPreferences loginClass = APP.activity.getSharedPreferences("loginClass", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = loginClass.edit();
        SharedPreferences.Editor editor = edit.putBoolean("loginboolean", false);
        editor.commit();

        Intent intent = new Intent();
        intent.setAction("login.wancheng");
        intent.putExtra("name","kong");
        APP.activity.sendBroadcast(intent);



        FragmentUtils.getFragment().init(APP.activity).getStart(MineFragment.class).build();

    }
}
