package practice.code.com.sourcechina.fragment.kyrj;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
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
import practice.code.com.sourcechina.adapter.ThreeSortAdapter;
import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.entity.HeadXMLBean;
import practice.code.com.sourcechina.entity.ThreeSortBean;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.model.HttpUtil.TwoSortUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class ThreeSortFragment extends BaseFragemnt implements ICallBack {

    @Bind(R.id.head_refresh_recyclerview)
    PullToRefreshRecyclerView sortPullRecycyle;
    @Bind(R.id.activity_head)
    RelativeLayout activityHead;
    private String tag;
    int flag =0;
    private ThreeSortAdapter adapter;
    private List<ThreeSortBean.SoftwareBean> list;
    boolean iiboolean;
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
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(APP.activity);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        sortPullRecycyle.setLayoutManager(linearLayoutManager);
        adapter = new ThreeSortAdapter(APP.activity , list);
        sortPullRecycyle.setAdapter(adapter);

    }

    @Override
    protected void refish() {
        super.refish();
        sortPullRecycyle.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                flag = 0;
                getLoad(false);
            }

            @Override
            public void onLoadMore() {

                flag++;
                getLoad(true);
            }
        });

    }


    @Override
    protected void getLoad(boolean b) {
        iiboolean  =  b;
        tag = bundle.getString("tag");
        TwoSortUtil.getInstance().onThreeSort(tag , flag + "" ,this);
    }

    @Override
    protected void sendData() {

    }

    @Override
    public void success(String mgs) {

        XStream xs = new XStream();
        xs.alias("oschina", ThreeSortBean.class);
        xs.alias("software",ThreeSortBean .SoftwareBean .class);
        ThreeSortBean threeSortBean = (ThreeSortBean) xs.fromXML(mgs);
        List<ThreeSortBean.SoftwareBean> softwares = threeSortBean.getSoftwares();
        list.addAll(softwares);
        adapter.notifyDataSetChanged();
        if (iiboolean) {
            sortPullRecycyle.setLoadMoreComplete();
        } else {
            sortPullRecycyle.setRefreshComplete();
        }
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

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
