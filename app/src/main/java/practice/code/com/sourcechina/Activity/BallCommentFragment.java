package practice.code.com.sourcechina.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.fragment.LoginFragment;
import practice.code.com.sourcechina.model.HttpUtil.SendTweetUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.db.UserBean;
import practice.code.com.sourcechina.model.db.UserDBOpenHelper;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class BallCommentFragment extends BaseFragemnt implements ICallBack {


    @Bind(R.id.send_tweet_bt)
    TextView sendTweetBt;
    @Bind(R.id.send_bck)
    ImageView sendBck;
    @Bind(R.id.tanyitan_re)
    RelativeLayout tanyitanRe;
    @Bind(R.id.send_msg)
    EditText sendMsg;
    private String uid;
    private Dao<UserBean, Integer> userDao;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.ball_comment_fm_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        UserDBOpenHelper helper = new UserDBOpenHelper(APP.activity);
        try {
            userDao = helper.getUserDao();
            List<UserBean> userBeen = userDao.queryForAll();
            if (userBeen.size() != 0) {
                uid = userBeen.get(userBeen.size() - 1).getUid();
                Log.e("sssssssssssss", uid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void getLoad(boolean b) {

    }

    @Override
    protected void show() {
        super.show();
        ActivityMain activity = (ActivityMain) APP.activity;
        RadioGroup mainRg = activity.getMainRg();
        RelativeLayout mainBar = activity.getMainBar();
        mainRg.setVisibility(View.VISIBLE);
        mainBar.setVisibility(View.VISIBLE);


    }

    @Override
    protected void onHidden() {
        super.onHidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainBar.setVisibility(View.GONE);
        mainRg.setVisibility(View.GONE);

    }

    @Override
    protected void hidden() {
        super.hidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainBar.setVisibility(View.GONE);
        mainRg.setVisibility(View.GONE);


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

    @OnClick(R.id.send_tweet_bt)
    public void onViewClicked() {
        SharedPreferences loginClass = APP.activity.getSharedPreferences("loginClass", Context.MODE_PRIVATE);
        boolean loginboolean = loginClass.getBoolean("loginboolean", false);
        if(loginboolean){
            String sss = sendMsg.getText().toString();
            if (!sss.isEmpty()) {
                Log.d("BallCommentFragmentuid", sss);
                SendTweetUtil.getInstance().sendMsg(uid, sss, "0", "0", this);
                Toast.makeText(APP.activity, "发送了", Toast.LENGTH_SHORT).show();
                BaseActivity.getBackManager();
            }
        }else{
          BaseActivity.getLoginMethod();
        }

    }

    @Override
    public void success(String mgs) {
        Log.d("BallCommentFragmentmsg", mgs);
    }

    @Override
    public void fail(String mgs) {


    }

    @OnClick(R.id.send_bck)
    public void onbackClicked() {
        BaseActivity.getBackManager();
    }
}
