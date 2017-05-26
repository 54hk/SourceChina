package practice.code.com.sourcechina.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.NewsTweetAdapter;
import practice.code.com.sourcechina.entity.NewsTweetBean;
import practice.code.com.sourcechina.entity.RecommendBlogXMLBean;
import practice.code.com.sourcechina.model.HttpUtil.NewsTweetUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class NewsTweetFragment extends BaseFragemnt implements ICallBack{
    PullToRefreshRecyclerView headRefreshRecyclerview;
    NewsTweetAdapter newsTweetAdapter;
    List<NewsTweetBean.TweetBean> datas;
    int pageIndex = 1;
    boolean isBoolen = false;
    ProgressDialog progressDialog;
    int pd = 0;

    @Override
    protected void beginProgressDialog() {
        progressDialog = ProgressDialog.show(APP.activity,null,"Loading...");
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
        Log.e("TAG3333", "aaaaaaaaaaaaaa");

        datas = new ArrayList<>();
        headRefreshRecyclerview = (PullToRefreshRecyclerView) inflate1.findViewById(R.id.head_refresh_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        headRefreshRecyclerview.setLayoutManager(linearLayoutManager);

        headRefreshRecyclerview.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                datas.clear();
                pd = 1;
                getLoad(false);
            }

            @Override
            public void onLoadMore() {
                getLoad(true);
                pd = 1;
                pageIndex ++ ;
            }
        });
        newsTweetAdapter = new NewsTweetAdapter(getContext(),datas);
        headRefreshRecyclerview.setAdapter(newsTweetAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        pd = 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pd = 0;
    }

    @Override
    protected void getLoad(boolean b) {
               isBoolen =b;
                NewsTweetUtil.getInstance().getNewsMessage(pageIndex+"","0",this);
    }

    @Override
    protected void sendData() {

    }

    @Override
    public void success(String mgs) {
        Log.e("TAG2222", mgs);
        XStream xs = new XStream();
        xs.alias("oschina", NewsTweetBean.class);
        xs.alias("tweet", NewsTweetBean.TweetBean.class);
        NewsTweetBean newsTweetBean = (NewsTweetBean) xs.fromXML(mgs);
        Log.i("TAG2222",newsTweetBean.toString()+"=================");


        List<NewsTweetBean.TweetBean> tweets = newsTweetBean.getTweets();
        datas.addAll(tweets);

        if (isBoolen) {
            headRefreshRecyclerview.setLoadMoreComplete();
        } else {
            headRefreshRecyclerview.setRefreshComplete();
        }
        if(pd == 0){
            progressDialog.dismiss();
        }

        newsTweetAdapter.notifyDataSetChanged();

    }

    @Override
    public void fail(String mgs) {
        Log.d("NewsTweetFragment", mgs);
    }
}
