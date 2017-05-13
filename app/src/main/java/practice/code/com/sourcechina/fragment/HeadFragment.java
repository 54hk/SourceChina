package practice.code.com.sourcechina.fragment;

;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.HeadAdapter;
import practice.code.com.sourcechina.adapter.TestNormalAdapter;
import practice.code.com.sourcechina.entity.HeadXMLBean;
import practice.code.com.sourcechina.model.HttpUtil.NewsUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

public class HeadFragment extends BaseFragemnt implements ICallBack{


    PullToRefreshRecyclerView headRefreshRv;

    HeadAdapter headAdapter;
    List<HeadXMLBean.NewsBean> newsBeenList;
    List<HeadXMLBean.NewsBean> newslist;
    int flag = 1;
    private View inflate;

    private boolean iiboolean;
    private RollPagerView mRollViewPager;


    @Override
    protected int initView() {
        return R.layout.activity_head;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {

        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.roll_layout, null);
        mRollViewPager = (RollPagerView) inflate.findViewById(R.id.head_pv);
        headRefreshRv = (PullToRefreshRecyclerView) inflate1.findViewById(R.id.head_refresh_recyclerview);


    }

    @Override
    protected void getLoad(boolean b) {
        rollviewp();
        iiboolean = b;
        NewsUtil.getInstance().getNewsList(flag,this);

    }

    @Override
    protected void sendData() {


        newsBeenList = new ArrayList<>();
        headAdapter = new HeadAdapter(getActivity(), newsBeenList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        headRefreshRv.setLayoutManager(linearLayoutManager);
        headRefreshRv.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                newsBeenList.clear();
                getLoad(false);
            }

            @Override
            public void onLoadMore() {
                flag++;
                getLoad(true);
            }
        });
        headRefreshRv.addHeaderView(inflate);
        headRefreshRv.setAdapter(headAdapter);

    }


    public void rollviewp() {

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(5000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.CYAN, Color.WHITE));
    }





    @Override
    public void success(String mgs) {

        Log.e("headFragment",mgs);
        XStream xs = new XStream();
        xs.alias("oschina", HeadXMLBean.class);
        xs.alias("news", HeadXMLBean.NewsBean.class);
        HeadXMLBean headXMLBean = (HeadXMLBean) xs.fromXML(mgs);
        newslist = headXMLBean.getNewslist();
        newsBeenList.addAll(newslist);
        headAdapter.notifyDataSetChanged();


        if (iiboolean) {
            headRefreshRv.setLoadMoreComplete();
        } else {
            headRefreshRv.setRefreshComplete();
        }
    }

    @Override
    public void fail(String mgs) {
        Log.e("headFragment",mgs);
    }
}
