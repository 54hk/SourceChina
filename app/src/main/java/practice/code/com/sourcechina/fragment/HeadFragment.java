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

public class HeadFragment extends Fragment implements ICallBack{


    PullToRefreshRecyclerView headRefreshRv;

    HeadAdapter headAdapter;
    List<HeadXMLBean.NewsBean> newsBeenList;
    List<HeadXMLBean.NewsBean> newslist;
    int flag = 1;
    private View inflate;
    private View inflate1;
    ProgressDialog progressDialog;
    private boolean iiboolean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate1 = inflater.inflate(R.layout.activity_head, null);
        headRefreshRv = (PullToRefreshRecyclerView) inflate1.findViewById(R.id.head_refresh_recyclerview);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        rollviewp();
        initHeadAdapter();
        getHeadVolley(false);

        return inflate1;
    }


    public void rollviewp() {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.roll_layout, null);
        RollPagerView mRollViewPager = (RollPagerView) inflate.findViewById(R.id.head_pv);

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(5000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.CYAN, Color.WHITE));
    }

    public void initHeadAdapter() {
        newsBeenList = new ArrayList<>();
        headAdapter = new HeadAdapter(getActivity(), newsBeenList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        headRefreshRv.setLayoutManager(linearLayoutManager);
        headRefreshRv.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                newsBeenList.clear();
                getHeadVolley(false);
            }

            @Override
            public void onLoadMore() {
                flag++;
                getHeadVolley(true);
            }
        });
        headRefreshRv.addHeaderView(inflate);
        headRefreshRv.setAdapter(headAdapter);

    }

    public void getHeadVolley(boolean aboolean) {

        iiboolean = aboolean;
        NewsUtil.getInstance().getNewsList(flag,this);




    }


    @Override
    public void success(String mgs) {
        progressDialog.cancel();
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
