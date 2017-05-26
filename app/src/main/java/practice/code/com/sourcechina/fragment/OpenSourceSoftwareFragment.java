package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import practice.code.com.sourcechina.adapter.OpenSourceSoftwareAdapter;
import practice.code.com.sourcechina.fragment.kyrj.ContainerVP;
import practice.code.com.sourcechina.fragment.kyrj.ORecommendFrgment;
import practice.code.com.sourcechina.fragment.kyrj.SortFragment;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class OpenSourceSoftwareFragment extends BaseFragemnt {
    @Bind(R.id.web_back)
    ImageView webBack;
    @Bind(R.id.open_source_tab)
    TabLayout openSourceTab;
    @Bind(R.id.open_source_vp)
    ViewPager openSourceVp;
    private OpenSourceSoftwareAdapter dapter;
    ArrayList<BaseFragemnt> baseFragemnts;
    String[] arr = new String[]{"recommend", "time", "view", "list_cn"};

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.open_source_software_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        baseFragemnts = new ArrayList<>();
        ContainerVP containerVP=new ContainerVP();
        baseFragemnts.add(containerVP);


        for (int i = 0; i < arr.length; i++) {
            ORecommendFrgment oRecommendFrgment = new ORecommendFrgment();
            oRecommendFrgment.setSerchTag(arr[i]);
            baseFragemnts.add(oRecommendFrgment);
        }


        dapter = new OpenSourceSoftwareAdapter(APP.activity.getSupportFragmentManager(), baseFragemnts);
        openSourceVp.setAdapter(dapter);
        openSourceTab.setupWithViewPager(openSourceVp);
        openSourceTab.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void getLoad(boolean b) {

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


    @OnClick(R.id.web_back)
    public void onViewClicked() {
        BaseActivity.getBackManager();
    }
}
