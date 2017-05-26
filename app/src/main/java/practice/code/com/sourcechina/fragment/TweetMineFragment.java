package practice.code.com.sourcechina.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.j256.ormlite.dao.Dao;
import com.thoughtworks.xstream.XStream;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.MyTweetadapter;
import practice.code.com.sourcechina.entity.MyTweetBean;
import practice.code.com.sourcechina.model.HttpUtil.MyTweetUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.db.UserBean;
import practice.code.com.sourcechina.model.db.UserDBOpenHelper;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class TweetMineFragment extends BaseFragemnt implements ICallBack {
    @Bind(R.id.head_refresh_recyclerview)
    PullToRefreshRecyclerView headRefreshRv;

    int pageIndex = 0;
    private String uid;
    private boolean iiboolean;
    ArrayList<MyTweetBean.TweetBean> list;
    private MyTweetadapter adapter;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.fan_fragment_lay;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {

        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        headRefreshRv.setLayoutManager(linearLayoutManager);



        Dao<UserBean, Integer> dao = null;
        try {
            UserDBOpenHelper helper = new UserDBOpenHelper(APP.activity);
            dao = helper.getUserDao();
            List<UserBean> userBeen = dao.queryForAll();
            if (userBeen.size() != 0) {
                uid = userBeen.get(userBeen.size() - 1).getUid();
                Log.e("sssssssssssss",uid);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter = new MyTweetadapter(APP.activity ,uid ,list);
        headRefreshRv.setAdapter(adapter);
    }

    @Override
    protected void getLoad(boolean b) {
        iiboolean = b;
        MyTweetUtil.getInstance().openMyTweetMessage( uid ,pageIndex + "",this);
    }

    @Override
    protected void sendData() {

    }
    @Override
    protected void refish() {
        super.refish();
        headRefreshRv.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                pageIndex= 0;
                adapter.notifyDataSetChanged();
                getLoad(false);
            }

            @Override
            public void onLoadMore() {

                pageIndex++;
                adapter.notifyDataSetChanged();
                getLoad(true);
            }
        });
    }


    @Override
    protected void show() {
        super.show();
        ActivityMain activity = (ActivityMain) APP.activity;
        RadioGroup mainRg = activity.getMainRg();
        RelativeLayout mainBar = activity.getMainBar();
        mainRg.setVisibility(View.VISIBLE);
        mainBar.setVisibility(View.VISIBLE);
//        FragmentBuilder.getInstance().addStack(getClass().getName());

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
    protected void onHidden() {
        super.onHidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainBar.setVisibility(View.GONE);
        mainRg.setVisibility(View.GONE);

    }

    @Override
    public void success(String mgs) {


        XStream xs = new XStream();
        xs.alias("oschina", MyTweetBean.class);
        xs.alias("tweet", MyTweetBean.TweetBean.class);
        Log.d("MyTweetFragment", mgs);
        MyTweetBean myTweetBean = (MyTweetBean) xs.fromXML(mgs);



        List<MyTweetBean.TweetBean> tweets = myTweetBean.getTweets();

        list.addAll(tweets);
        adapter.notifyDataSetChanged();
        if (iiboolean) {
            headRefreshRv.setLoadMoreComplete();
        } else {
            headRefreshRv.setRefreshComplete();
        }

        Intent intent = new Intent();
        intent.setAction("login.wancheng");
        intent.putExtra("name", "login");
        APP.activity.sendBroadcast(intent);

    }

    @Override
    public void fail(String mgs) {

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
}
