package practice.code.com.sourcechina.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.RecommendBlogsAdapter;
import practice.code.com.sourcechina.entity.RecommendBlogXMLBean;
import practice.code.com.sourcechina.model.HttpUtil.BlogUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class RecommendFragment extends BaseFragemnt implements ICallBack{

    PullToRefreshRecyclerView headRefreshRv;

    RecommendBlogsAdapter rbAdapter;
    ArrayList<RecommendBlogXMLBean.BlogBean> bolgAlls = new ArrayList<RecommendBlogXMLBean.BlogBean>();
    int flag = 1;

    private List<RecommendBlogXMLBean.BlogBean> blogslist;
    private boolean iiboolean;



    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return  R.layout.activity_recommend_blogs;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        headRefreshRv = (PullToRefreshRecyclerView) inflate1.findViewById(R.id.rb_refresh_recyclerview);
    }

    @Override
    protected void getLoad(boolean b) {
        BlogUtil.getInstance().getNewsList("recommend",flag,this);
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

    }
}
