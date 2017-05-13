package practice.code.com.sourcechina.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.CompositeVPAdapter;

public class CompositeFragment extends BaseFragemnt {


    TabLayout activityCompositeTab;

    ViewPager activityCompositeVp;


    ArrayList<Fragment> fragments = new ArrayList<>();
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.activity_composite, null);
        activityCompositeTab = (TabLayout) inflate.findViewById(R.id.activity_composite_tab);
        activityCompositeVp = (ViewPager) inflate.findViewById(R.id.activity_composite_vp);
        HeadFragment headFragment = new HeadFragment();
        fragments.add(headFragment);
        RecommendBlogsFragment recommendBlogsFragment  =   new RecommendBlogsFragment();
        fragments.add(recommendBlogsFragment);
        WIFIMessageFragment wifiMessageFragment =  new WIFIMessageFragment();
        fragments.add(wifiMessageFragment);
        CompositeVPAdapter comVPAdapter = new CompositeVPAdapter(getActivity().getSupportFragmentManager(),fragments);

        activityCompositeVp.setAdapter(comVPAdapter);
        activityCompositeTab.setupWithViewPager(activityCompositeVp);
        activityCompositeTab.setTabMode(TabLayout.MODE_FIXED);

        return inflate;
    }

    @Override
    protected int initView() {
        return 0;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {

    }

    @Override
    protected void getLoad(boolean b) {

    }

    @Override
    protected void sendData() {

    }


}
