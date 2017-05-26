package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.TweetVPAdapter;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class TweetFragment extends BaseFragemnt {

    TabLayout activityCompositeTab;
    ViewPager activityCompositeVp;
    ArrayList<Fragment> fragments = new ArrayList<>();
    @Bind(R.id.zhuan_img)
    ImageView zhuanImg;
    @Bind(R.id.activity_composite)
    LinearLayout activityComposite;
    private TweetVPAdapter tweetVPAdapter;
    private NewsTweetFragment tweetFragment;
    private HotTweetFragment hotTweetFragment;
    private MyTweetFragment myTweetFragment;

    @Override
    protected void beginProgressDialog() {

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
    protected int initView() {
        return R.layout.activity_composite;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {

        activityCompositeTab = (TabLayout) inflate1.findViewById(R.id.activity_composite_tab);
        activityCompositeVp = (ViewPager) inflate1.findViewById(R.id.activity_composite_vp);
        zhuanImg.setVisibility(View.GONE);

    }

    @Override
    protected void getLoad(boolean b) {


    }

    @Override
    protected void sendData() {


        tweetFragment = new NewsTweetFragment();
        fragments.add(tweetFragment);
        hotTweetFragment = new HotTweetFragment();
        fragments.add(hotTweetFragment);
        myTweetFragment = new MyTweetFragment();
        fragments.add(myTweetFragment);

        tweetVPAdapter = new TweetVPAdapter(getChildFragmentManager(), fragments);
        activityCompositeVp.setAdapter(tweetVPAdapter);
        activityCompositeVp.setOffscreenPageLimit(2);
        activityCompositeTab.setupWithViewPager(activityCompositeVp);
        activityCompositeTab.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
