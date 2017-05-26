package practice.code.com.sourcechina.fragment.kyrj;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import practice.code.com.sourcechina.adapter.OSortFragmentAdapter;
import practice.code.com.sourcechina.adapter.OpenSourceSoftwareAdapter;
import practice.code.com.sourcechina.entity.SortBean;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.model.HttpUtil.OSSUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class SortFragment extends BaseFragemnt implements ICallBack {

    ArrayList<SortBean.SoftwareTypeBean> softwareTypelist;
    @Bind(R.id.sort_pull_recycyle)
    PullToRefreshRecyclerView sortPullRecycyle;
    private OSortFragmentAdapter adapter;
boolean isBoolean = false;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.sort_fragment_layot;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        softwareTypelist = new ArrayList<>();
        adapter = new OSortFragmentAdapter(APP.activity,softwareTypelist);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(APP.activity);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        sortPullRecycyle.setLayoutManager(linearLayoutManager);
        sortPullRecycyle.setAdapter(adapter);

        sortPullRecycyle.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                isBoolean = false;

            }

            @Override
            public void onLoadMore() {
                isBoolean  = true;

            }
        });

    }

    @Override
    protected void getLoad(boolean b) {



        if(isBoolean){
            sortPullRecycyle.setLoadMoreComplete();

        }else{
            sortPullRecycyle.setRefreshComplete();
        }
    }

    @Override
    protected void sendData() {
        OSSUtil.getInstance().sort(this);
    }

    @Override
    public void success(String mgs) {
        XStream xs = new XStream();
        xs.alias("oschina", SortBean.class);
        xs.alias("softwareType", SortBean.SoftwareTypeBean.class);
        SortBean sortBean = (SortBean) xs.fromXML(mgs);
        List<SortBean.SoftwareTypeBean> softwareTypes = sortBean.getSoftwareTypes();
        softwareTypelist.addAll(softwareTypes);
        adapter.notifyDataSetChanged();
        Log.d("OpenSourceSoftwareFragm", mgs);

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
