package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.FindWhatAdapter;
import practice.code.com.sourcechina.entity.FindWhatBean;
import practice.code.com.sourcechina.fragment.findwhat.FindWhatTabFragment;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class FindWhatFragment extends BaseFragemnt {
    @Bind(R.id.searchFinish)
    TextView searchFinish;
    @Bind(R.id.search)
    ImageView search;
    @Bind(R.id.soft)
    EditText soft;
    @Bind(R.id.clear)
    ImageView clear;
    @Bind(R.id.aaaa)
    RelativeLayout aaaa;
    @Bind(R.id.searchTable)
    TabLayout searchTable;
    @Bind(R.id.searchViewpaer)
    ViewPager searchViewpaer;
    String[] arr = new String[]{"软件" ,"博客" ,"资讯","问答","找人"};
    String[] catalog = new String[]{"software","post","blog","news"};
    ArrayList<Fragment> fragments;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.search;
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

    @OnClick({R.id.search, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                getMessge();
                break;
            case R.id.clear:
                break;
        }
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
    protected void hidden() {
        super.hidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainBar.setVisibility(View.GONE);
        mainRg.setVisibility(View.GONE);
    }

    public void getMessge(){
        fragments = new ArrayList<>();

        for(int i = 0; i<arr.length - 1;i ++){
        FindWhatTabFragment findWhatTabFragment = new FindWhatTabFragment();
        findWhatTabFragment.setCatalog(catalog[i]);
        findWhatTabFragment.setContent(soft.getText().toString());
        fragments.add(findWhatTabFragment);
        }
        FindUserFragment findUserFragment  =new FindUserFragment();
        findUserFragment.setContent(soft.getText().toString());
        fragments.add(findUserFragment);

        FindWhatAdapter adapter  = new FindWhatAdapter(getChildFragmentManager(),fragments, arr);
        searchViewpaer.setAdapter(adapter);
        searchTable.setupWithViewPager(searchViewpaer);
        searchTable.setTabMode(TabLayout.MODE_FIXED);

    }

}
