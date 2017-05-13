package practice.code.com.sourcechina.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.WIFIApdater;
import practice.code.com.sourcechina.entity.RecommendBlogXMLBean;
import practice.code.com.sourcechina.entity.WIFIMessageBean;
import practice.code.com.sourcechina.model.HttpUtil.WIFINewUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class WIFIMessageFragment extends BaseFragemnt implements ICallBack{


    PullToRefreshRecyclerView wifiPv;

    WIFIApdater rbAdapter;
    int flag = 1;

    private boolean iiboolean;
    private List<WIFIMessageBean.NewsBean> newslist;

    private List<WIFIMessageBean.NewsBean> Allnewslist;


    @Override
    protected int initView() {
        return R.layout.wifi_mgs_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate) {
        wifiPv = (PullToRefreshRecyclerView) inflate.findViewById(R.id.wifi_pv);

    }

    @Override
    protected void getLoad(boolean b) {
        WIFINewUtil.getInstance().getNewsList(flag,this);
        iiboolean = b;

    }

    @Override
    protected void sendData() {
        Allnewslist = new ArrayList<>();
        rbAdapter = new WIFIApdater(getActivity(),Allnewslist );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        wifiPv.setLayoutManager(linearLayoutManager);
        wifiPv.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                Allnewslist.clear();
                getLoad(false);
            }

            @Override
            public void onLoadMore() {
                flag++;
                getLoad(true);
            }
        });
        wifiPv.setAdapter(rbAdapter);

    }





    @Override
    public void success(String mgs) {

        XStream xs = new XStream();
        xs.alias("oschina", WIFIMessageBean.class);
        xs.alias("news", WIFIMessageBean.NewsBean.class);
        xs.alias("notice", WIFIMessageBean.NoticeBean.class);
        WIFIMessageBean oMessageBean = (WIFIMessageBean) xs.fromXML(mgs);
        newslist = oMessageBean.getNewslist();
        Allnewslist.addAll(newslist);
        rbAdapter.notifyDataSetChanged();

        rbAdapter.notifyDataSetChanged();
        if (iiboolean) {
            wifiPv.setLoadMoreComplete();
        } else {
            wifiPv.setRefreshComplete();
        }
    }

    @Override
    public void fail(String mgs) {

    }
}
