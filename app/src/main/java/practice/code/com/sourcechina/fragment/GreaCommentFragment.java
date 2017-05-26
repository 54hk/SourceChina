package practice.code.com.sourcechina.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.GreatAdapter;
import practice.code.com.sourcechina.adapter.GreatCommentAdapter;
import practice.code.com.sourcechina.entity.CommentListBean;
import practice.code.com.sourcechina.entity.TweetDetilsBean;
import practice.code.com.sourcechina.model.HttpUtil.IGreatUtil;
import practice.code.com.sourcechina.model.HttpUtil.MYMessageUtil;
import practice.code.com.sourcechina.model.HttpUtil.TweetDetailsUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class GreaCommentFragment  extends BaseFragemnt implements ICallBack {

    @Bind(R.id.great_recycle)
    PullToRefreshRecyclerView greatRecycle;
    private String id;
    List<CommentListBean.CommentBean> likeLists;
    private GreatCommentAdapter adapter;

    int pageIndix = 0;
    boolean isBoolean = false;
    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.tweet_detils_vp;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {

        SharedPreferences sharedPreferences = APP.activity.getSharedPreferences("", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");

        likeLists = new ArrayList<>();
        adapter = new GreatCommentAdapter(APP.context,likeLists);
        greatRecycle.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(APP.context);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        greatRecycle.setLayoutManager(linearLayoutManager);
        greatRecycle.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndix = 0;
                getLoad(false);
            }

            @Override
            public void onLoadMore() {
                pageIndix++;
                getLoad(true);
            }
        });

    }

    @Override
    protected void getLoad(boolean b) {
        isBoolean = b;
//        e(String uid, String catalog , String pageIndex, ICallBack iCallBack) {
        IGreatUtil.getInstance().getGreat(String.valueOf(3),id,String.valueOf(pageIndix),this);

    }

    @Override
    protected void sendData() {

    }

    @Override
    public void success(String mgs) {
        XStream xs = new XStream();
        xs.alias("oschina", CommentListBean.class);
        xs.alias("comment", CommentListBean.CommentBean.class);
        xs.alias("notice",CommentListBean.NoticeBean.class);

        CommentListBean commentBean = (CommentListBean) xs.fromXML(mgs);
        List<CommentListBean.CommentBean> comments = commentBean.getComments();

        if(isBoolean){
            greatRecycle.setLoadMoreComplete();

        }else{
            greatRecycle.setRefreshComplete();
        }

        likeLists.addAll(comments);
        adapter.notifyDataSetChanged();


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
