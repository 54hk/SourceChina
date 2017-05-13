package practice.code.com.sourcechina.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import practice.code.com.sourcechina.adapter.RecommendBlogsAdapter;
import practice.code.com.sourcechina.adapter.TestNormalAdapter;
import practice.code.com.sourcechina.entity.HeadXMLBean;
import practice.code.com.sourcechina.entity.RecommendBlogXMLBean;
import practice.code.com.sourcechina.model.HttpUtil.BlogUtil;
import practice.code.com.sourcechina.model.HttpUtil.NewsUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

public class RecommendBlogsFragment extends BaseFragemnt implements ICallBack{


    PullToRefreshRecyclerView headRefreshRv;

    RecommendBlogsAdapter rbAdapter;
   ArrayList<RecommendBlogXMLBean.BlogBean> bolgAlls = new ArrayList<RecommendBlogXMLBean.BlogBean>();
    int flag = 1;

    private List<RecommendBlogXMLBean.BlogBean> blogslist;
    private boolean iiboolean;



    @Override
    protected int initView() {
        return R.layout.activity_recommend_blogs;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate) {
        headRefreshRv = (PullToRefreshRecyclerView) inflate.findViewById(R.id.rb_refresh_recyclerview);


    }

    @Override
    protected void getLoad(boolean b) {
        BlogUtil.getInstance().getNewsList(flag,this);
        iiboolean = b;
    }

    @Override
    protected void sendData() {
        bolgAlls = new ArrayList<>();
        rbAdapter = new RecommendBlogsAdapter(getActivity(),bolgAlls );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        headRefreshRv.setLayoutManager(linearLayoutManager);
        headRefreshRv.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                bolgAlls.clear();
                getLoad(false);
            }

            @Override
            public void onLoadMore() {
                flag++;
                getLoad(true);
            }
        });
        headRefreshRv.setAdapter(rbAdapter);

    }





    @Override
    public void success(String mgs) {
        Log.e("recomment",mgs);

        XStream xs = new XStream();
        xs.alias("oschina", RecommendBlogXMLBean.class);
        xs.alias("blog", RecommendBlogXMLBean.BlogBean.class);
        RecommendBlogXMLBean recommendBlogXMLBean = (RecommendBlogXMLBean) xs.fromXML(mgs);
        blogslist = recommendBlogXMLBean.getBlogs();
        bolgAlls.addAll(blogslist);
        rbAdapter.notifyDataSetChanged();
        if (iiboolean) {
            headRefreshRv.setLoadMoreComplete();
        } else {
            headRefreshRv.setRefreshComplete();
        }
    }

    @Override
    public void fail(String mgs) {
        Log.e("recomment",mgs);
    }
}
