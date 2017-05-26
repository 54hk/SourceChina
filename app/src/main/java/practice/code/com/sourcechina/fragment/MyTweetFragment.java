package practice.code.com.sourcechina.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.j256.ormlite.dao.Dao;
import com.thoughtworks.xstream.XStream;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.MyTweetadapter;
import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.entity.MyTweetBean;
import practice.code.com.sourcechina.model.HttpUtil.VolleyUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.db.UserBean;
import practice.code.com.sourcechina.model.db.UserDBOpenHelper;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class MyTweetFragment extends BaseFragemnt implements ICallBack {
    @Bind(R.id.head_refresh_recyclerview)
    PullToRefreshRecyclerView headRefreshRv;
    @Bind(R.id.activity_head)
    RelativeLayout activityHead;
    int pageIndex = 0;
    @Bind(R.id.my_no_img)
    ImageView myNoImg;
    private String uid;
    private boolean iiboolean;
    ArrayList<MyTweetBean.TweetBean> list;
    private MyTweetadapter adapter;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getStringExtra("name").equals("login")){
                getAdapterUid();
                getIntentShare();
            }
        }
    };

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.activity_head;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        APP.activity.unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void initLoad(View inflate1) {

        IntentFilter intentf = new IntentFilter();
        intentf.addAction("mytweet.bian");
        APP.activity.registerReceiver(broadcastReceiver,intentf);


        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        headRefreshRv.setLayoutManager(linearLayoutManager);

        getAdapterUid();

    }

    public void getAdapterUid(){
        Dao<UserBean, Integer> dao = null;
        try {
            UserDBOpenHelper helper = new UserDBOpenHelper(APP.activity);
            dao = helper.getUserDao();
            List<UserBean> userBeen = dao.queryForAll();
            if (userBeen.size() != 0) {
                uid = userBeen.get(userBeen.size() - 1).getUid();
                Log.e("sssssssssssss", uid);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter = new MyTweetadapter(APP.activity, uid, list);
        headRefreshRv.setAdapter(adapter);
    }

    @Override
    protected void getLoad(boolean b) {
        iiboolean = b;
        getIntentShare();

    }

    public void getIntentShare(){
        headRefreshRv.setVisibility(View.VISIBLE);
        myNoImg.setVisibility(View.GONE);
        SharedPreferences loginClass = APP.activity.getSharedPreferences("loginClass", Context.MODE_PRIVATE);
        boolean loginboolean = loginClass.getBoolean("loginboolean", false);
        if (loginboolean) {
            Map<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("pageIndex", String.valueOf(pageIndex));
            map.put("pageSize", "5");
//        MyTweetUtil.getInstance().openMyTweetMessage( uid ,pageIndex + "",this);
            VolleyUtil.getInstance().doGET(Urls.MYTWEETURL, map, this);
        } else {
//            TODO  未登录..
            headRefreshRv.setVisibility(View.GONE);
            if (iiboolean) {
                headRefreshRv.setLoadMoreComplete();
            } else {
                headRefreshRv.setRefreshComplete();
            }
            myNoImg.setVisibility(View.VISIBLE);

        }
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
                pageIndex = 0;
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
    public void success(String mgs) {


        XStream xs = new XStream();
        xs.alias("oschina", MyTweetBean.class);
        xs.alias("tweet", MyTweetBean.TweetBean.class);
        Log.d("MyTweetFragment", mgs);
        MyTweetBean myTweetBean = (MyTweetBean) xs.fromXML(mgs);

        SharedPreferences preferences = APP.activity.getSharedPreferences("tweetsize", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("tweets", myTweetBean.getPagesize());
        Log.d("MyTweetFragment", myTweetBean.getPagesize());
        Log.d("MyTweetFragment", myTweetBean.getTweetCount());
        edit.commit();





        List<MyTweetBean.TweetBean> tweets = myTweetBean.getTweets();

        list.addAll(tweets);
        adapter.notifyDataSetChanged();
        if (iiboolean) {
            headRefreshRv.setLoadMoreComplete();
        } else {
            headRefreshRv.setRefreshComplete();
            Intent intent = new Intent();
            intent.setAction("login.wancheng");
            intent.putExtra("name", "login");
            APP.activity.sendBroadcast(intent);
        }
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
