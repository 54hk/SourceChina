package practice.code.com.sourcechina.fragment.findwhat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import practice.code.com.sourcechina.adapter.FindWhatTbAdapter;
import practice.code.com.sourcechina.entity.FindWhatBean;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.model.HttpUtil.FindWhatUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class FindWhatTabFragment extends BaseFragemnt implements ICallBack {

    public String catalog;
    @Bind(R.id.find_what_pull)
    PullToRefreshRecyclerView findWhatPull;
    private String content;
    List<FindWhatBean.ResultBean> resList;
    private FindWhatTbAdapter adapter;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.find_what_tab_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        resList = new ArrayList<>();
        adapter = new FindWhatTbAdapter(APP.activity,resList);
        findWhatPull.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        findWhatPull.setLayoutManager(linearLayoutManager);


    }

    @Override
    protected void getLoad(boolean b) {
        FindWhatUtil.getInstance().getFindWhat(getCatalog(), getContent(), 0 + "", this);
        Log.d("FindWhatTabFragment", getCatalog());
        Log.d("FindWhatTabFragment", getContent());
    }

    @Override
    protected void sendData() {

    }

    @Override
    public void success(String mgs) {
        Log.d("FindWhatTabFragment", mgs);
        XStream xStream = new XStream();
        xStream.alias("oschina", FindWhatBean.class);
        xStream.alias("result", FindWhatBean.ResultBean.class);
        FindWhatBean findWhatBean = (FindWhatBean) xStream.fromXML(mgs);
        List<FindWhatBean.ResultBean> results = findWhatBean.getResults();
        resList.addAll(results);
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
