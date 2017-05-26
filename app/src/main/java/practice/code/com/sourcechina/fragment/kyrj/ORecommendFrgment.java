package practice.code.com.sourcechina.fragment.kyrj;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.ORecommendFAdapter;
import practice.code.com.sourcechina.adapter.OSortFragmentAdapter;
import practice.code.com.sourcechina.entity.ORecommendBean;
import practice.code.com.sourcechina.entity.SortBean;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.model.HttpUtil.ORecommerUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class ORecommendFrgment  extends BaseFragemnt implements ICallBack{

    ArrayList<ORecommendBean.SoftwareBean> softwareTypelist;
    @Bind(R.id.sort_pull_recycyle)
    PullToRefreshRecyclerView sortPullRecycyle;
    ORecommendFAdapter fAdapter;
    int pageIndex = 1;
    boolean iiboolean;
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


        fAdapter = new ORecommendFAdapter(APP.activity,softwareTypelist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(APP.activity);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        sortPullRecycyle.setLayoutManager(linearLayoutManager);
        sortPullRecycyle.setAdapter(fAdapter);

    }

    @Override
    protected void refish() {
        super.refish();
        sortPullRecycyle.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                softwareTypelist.clear();
                getLoad(false);
            }

            @Override
            public void onLoadMore() {
                pageIndex++;
                getLoad(true);
            }
        });
    }

    @Override
    protected void getLoad(boolean b) {
        iiboolean  = b;
        ORecommerUtil.getInstance().onRecommenr(getSearchTag(),pageIndex + "",this);
    }

    @Override
    protected void sendData() {



    }

    @Override
    public void success(String mgs) {
        XStream xs = new XStream();
        xs.alias("oschina", ORecommendBean.class);
        xs.alias("software", ORecommendBean.SoftwareBean.class);
        ORecommendBean oRecommendBean = (ORecommendBean) xs.fromXML(mgs);

        List<ORecommendBean.SoftwareBean> softwares = oRecommendBean.getSoftwares();
        softwareTypelist.addAll(softwares);
        fAdapter.notifyDataSetChanged();
        Log.d("ORecommedFragment", mgs);
        if (iiboolean) {
            sortPullRecycyle.setLoadMoreComplete();
        } else {
            sortPullRecycyle.setRefreshComplete();
        }

    }

    @Override
    public void fail(String mgs) {

    }

    public String getSearchTag() {

        return searchTag;
    }
    String searchTag;
    public void setSerchTag(String searchTag){
        this.searchTag  =  searchTag;
    }
}
