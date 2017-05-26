package practice.code.com.sourcechina.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.NewsTweetAdapter;
import practice.code.com.sourcechina.entity.NewsTweetBean;
import practice.code.com.sourcechina.model.HttpUtil.NewsTweetUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class HotTweetFragment extends BaseFragemnt implements ICallBack{

    PullToRefreshRecyclerView headRefreshRecyclerview;
    NewsTweetAdapter newsTweetAdapter;
    List<NewsTweetBean.TweetBean> datas;
    int pageIndex = 0;
    boolean isBoolen = false;


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
    protected void initLoad(View inflate1) {

        datas = new ArrayList<>();
        headRefreshRecyclerview = (PullToRefreshRecyclerView) inflate1.findViewById(R.id.head_refresh_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        headRefreshRecyclerview.setLayoutManager(linearLayoutManager);

        headRefreshRecyclerview.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                datas.clear();
                getLoad(false);
            }

            @Override
            public void onLoadMore() {
                getLoad(true);
                pageIndex ++ ;
            }
        });
        newsTweetAdapter = new NewsTweetAdapter(getContext(),datas);
        headRefreshRecyclerview.setAdapter(newsTweetAdapter);
    }

    @Override
    protected void getLoad(boolean b) {
        isBoolen =b;
        NewsTweetUtil.getInstance().getNewsMessage(pageIndex+"","-1",this);
    }

    @Override
    protected void sendData() {

    }

    @Override
    public void success(String mgs) {
        Log.e("sss", mgs);
        XStream xs = new XStream();
        xs.alias("oschina", NewsTweetBean.class);
        xs.alias("tweet", NewsTweetBean.TweetBean.class);
        NewsTweetBean newsTweetBean = (NewsTweetBean) xs.fromXML(mgs);
        Log.i("sss",newsTweetBean.toString()+"=================");


        List<NewsTweetBean.TweetBean> tweets = newsTweetBean.getTweets();
        datas.addAll(tweets);

        if (isBoolen) {
            headRefreshRecyclerview.setLoadMoreComplete();
        } else {
            headRefreshRecyclerview.setRefreshComplete();
        }


        newsTweetAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String mgs) {

    }
}
