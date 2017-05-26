package practice.code.com.sourcechina.fragment.messagecenter;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.Activity.BaseActivity;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.MessgeCenterdapter;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class MessageCenterFragment extends BaseFragemnt implements ICallBack {
    @Bind(R.id.message_center_tl)
    TabLayout messageCenterTl;
    @Bind(R.id.messge_center_vp)
    ViewPager messgeCenterVp;
    ArrayList<Fragment> fragments;
    @Bind(R.id.web_back)
    ImageView webBack;
    private MessgeCenterdapter adapter;


    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.message_center_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void show() {
        super.show();
        ActivityMain activity = (ActivityMain) APP.activity;
        RadioGroup mainRg = activity.getMainRg();
        RelativeLayout mainBar = activity.getMainBar();
        mainRg.setVisibility(View.VISIBLE);
        mainBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onHidden() {
        super.onHidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainBar.setVisibility(View.GONE);
        mainRg.setVisibility(View.GONE);

    }

    @Override
    protected void hidden() {
        super.hidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainBar.setVisibility(View.GONE);
        mainRg.setVisibility(View.GONE);
    }


    @Override
    protected void initLoad(View inflate1) {
        fragments = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MyAFragment myAFragment = new MyAFragment();
            fragments.add(myAFragment);
        }
        adapter = new MessgeCenterdapter(APP.activity.getSupportFragmentManager(), fragments);
        messgeCenterVp.setAdapter(adapter);
        messageCenterTl.setTabMode(TabLayout.MODE_FIXED);
        messageCenterTl.setupWithViewPager(messgeCenterVp);
//
    }

    @Override
    protected void getLoad(boolean b) {

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
        Log.d("MessageCenterFragment", mgs);
    }

    @Override
    public void fail(String mgs) {

    }

    @OnClick(R.id.web_back)
    public void onBackClicked() {
        BaseActivity.getBackManager();
    }
}
