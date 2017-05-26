package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.androidkun.PullToRefreshRecyclerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.FanOrGuanAdapter;
import practice.code.com.sourcechina.entity.FanOrGuanBean;

import practice.code.com.sourcechina.model.HttpUtil.FanOrGuanUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class FanFragment extends BaseFragemnt implements ICallBack{

    @Bind(R.id.head_refresh_recyclerview)
    PullToRefreshRecyclerView headRefreshRecyclerview;
    @Bind(R.id.ssssss)
    LinearLayout activityHead;
    List<FanOrGuanBean.FriendBean> lsit;
    private FanOrGuanAdapter adapert;
    private String uid;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.fan_fragment_lay;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void onHidden() {
        super.onHidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        mainBar.setVisibility(View.GONE);

    }

    @Override
    protected void hidden() {
        super.hidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainRg.setVisibility(View.GONE);

        mainBar.setVisibility(View.GONE);

    }

    @Override
    protected void show() {
        super.show();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        mainBar.setVisibility(View.VISIBLE);
        RadioGroup mainRg = activity.getMainRg();
        mainRg.setVisibility(View.VISIBLE);

    }


    @Override
    protected void initLoad(View inflate1) {
        lsit = new ArrayList<>();
        adapert = new FanOrGuanAdapter(APP.activity,lsit);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(APP.activity);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        headRefreshRecyclerview.setLayoutManager(linearLayoutManager);
        headRefreshRecyclerview.setAdapter(adapert);

        uid = getParmes().getString("uid");

    }

    @Override
    protected void getLoad(boolean b) {
        FanOrGuanUtil.getInstance().getFanOrGuan(uid , "0" , 0 + "", this);
    }

    @Override
    protected void sendData() {

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

    @Override
    public void success(String mgs) {

        XStream xStream = new XStream();
        xStream.alias("oschina",FanOrGuanBean.class);
        xStream.alias("friend",FanOrGuanBean.FriendBean.class);
        xStream.alias("notice",FanOrGuanBean.NoticeBean.class);
        FanOrGuanBean fanOrGuanBean = (FanOrGuanBean) xStream.fromXML(mgs);
        List<FanOrGuanBean.FriendBean> friends = fanOrGuanBean.getFriends();
        lsit.addAll(friends);
        adapert.notifyDataSetChanged();



        Log.d("FanFragment", mgs);
    }

    @Override
    public void fail(String mgs) {

    }
}
