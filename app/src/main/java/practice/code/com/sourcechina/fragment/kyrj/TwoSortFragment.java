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
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.TwoSortAdapter;
import practice.code.com.sourcechina.entity.TwoSortBean;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.model.HttpUtil.TwoSortUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class TwoSortFragment extends BaseFragemnt implements ICallBack {

    @Bind(R.id.sort_pull_recycyle)
    PullToRefreshRecyclerView sortPullRecycyle;
    private String tag;
ArrayList<TwoSortBean.SoftwareTypeBean> typeBeen;
    private TwoSortAdapter adapter;

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
        typeBeen = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(APP.activity);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        sortPullRecycyle.setLayoutManager(linearLayoutManager);
        adapter = new TwoSortAdapter(APP.activity,typeBeen);
        sortPullRecycyle.setAdapter(adapter);
    }

    @Override
    protected void getLoad(boolean b) {

    }

    @Override
    protected void sendData() {
        tag = bundle.getString("tag");
        Log.d("TwoFragment", tag);
        TwoSortUtil.getInstance().onSort(tag, this);
    }

    @Override
    public void success(String mgs) {
        Log.d("TwoFragment", mgs);
        XStream xs = new XStream();
        xs.alias("oschina", TwoSortBean.class);
        xs.alias("softwareType", TwoSortBean.SoftwareTypeBean.class);
        TwoSortBean sortBean = (TwoSortBean) xs.fromXML(mgs);
        List<TwoSortBean.SoftwareTypeBean> softwareTypes = sortBean.getSoftwareTypes();
        typeBeen.addAll(softwareTypes);
        adapter.notifyDataSetChanged();

        Log.d("TwoFragmentsize", "sortBean.getSoftwareTypes().size():" + sortBean.getSoftwareTypes().size());
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
