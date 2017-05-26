package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.Activity.BaseActivity;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.ActionAdapter;
import practice.code.com.sourcechina.entity.ActionBean;
import practice.code.com.sourcechina.model.HttpUtil.ActionUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class ActionFragment extends BaseFragemnt implements ICallBack {

    @Bind(R.id.action_recyele)
    PullToRefreshRecyclerView actionRecyele;


    ActionAdapter headAdapter;
    List<ActionBean.EventBean> newsBeenList;
    int flag = 1;

    ViewPager vp;
    TextView tvDesc;
    LinearLayout llPointContent;
    LinearLayout llContent;
    @Bind(R.id.web_back)
    ImageView webBack;
    private boolean iiboolean;

    int[] drawable = {R.mipmap.bg_topic_1, R.mipmap.bg_topic_2, R.mipmap.bg_topic_3, R.mipmap.bg_topic_4};
    ArrayList<View> views;
    String[] sarr = {"我国机场屡遭无人机侵扰 玩家:反正没人管就飞",
            "萨德阴影冲击北京“韩国城”:感觉一切都变了", "云南通报问题大米:白色颗粒物并非人为掺假",
            "顺丰接盘百度外卖？知情人士：百度仍将控股"};
    private Timer timer;
    private TimerTask task;


    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.xian_action;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {


        View vpLayout = LayoutInflater.from(APP.context).inflate(R.layout.action_hear_vp_layout, null);
        vp = (ViewPager) vpLayout.findViewById(R.id.vp);
        tvDesc = (TextView) vpLayout.findViewById(R.id.tv_desc);
        llPointContent = (LinearLayout) vpLayout.findViewById(R.id.ll_point_content);
        llContent = (LinearLayout) vpLayout.findViewById(R.id.ll_content);

        newsBeenList = new ArrayList<>();
        headAdapter = new ActionAdapter(getActivity(), newsBeenList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        actionRecyele.setLayoutManager(linearLayoutManager);
        actionRecyele.setAdapter(headAdapter);
        actionRecyele.addHeaderView(vpLayout);
    }

    @Override
    protected void getLoad(boolean b) {
        iiboolean = b;
        getvp();
        ActionUtil.getInstance().getActonMethod(String.valueOf(-1), String.valueOf(1), this);
    }

    public void getvp() {
        views = new ArrayList<>();
        for (int i = 0; i < drawable.length; i++) {
            ImageView imageView = new ImageView(APP.context);
            imageView.setImageResource(drawable[i]);
            views.add(imageView);
            tvDesc.setText(sarr[i]);
            View view = new View(APP.context);
            view.setBackgroundResource(R.drawable.point_select);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
            if (i != 0) {
                lp.leftMargin = 10;
            }
            llPointContent.addView(view, lp);
            view.setEnabled(false);
        }
        MyPagerAdapter adapter = new MyPagerAdapter();
        vp.setAdapter(adapter);


        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < drawable.length; i++) {
                    int newPosition = position % drawable.length;
                    if (i == newPosition) {
                        llPointContent.getChildAt(i).setEnabled(true);
                        tvDesc.setText(sarr[newPosition]);
                    } else
                        llPointContent.getChildAt(i).setEnabled(false);

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @OnClick(R.id.web_back)
    public void onViewClicked() {
        BaseActivity.getBackManager();
    }


    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
//            super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int i = position % drawable.length;
            container.addView(views.get(i));
            return views.get(i);
        }
    }


    @Override
    protected void sendData() {

    }


    @Override
    protected void refish() {
        super.refish();


        actionRecyele.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                newsBeenList.clear();
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
    public void success(String mgs) {
        Log.d("ActionFragment", mgs);
        XStream xs = new XStream();
        xs.alias("oschina", ActionBean.class);
        xs.alias("event", ActionBean.EventBean.class);
        xs.alias("notice", ActionBean.NoticeBean.class);
        ActionBean actionBean = (ActionBean) xs.fromXML(mgs);
        List<ActionBean.EventBean> events = actionBean.getEvents();
        newsBeenList.addAll(events);
        headAdapter.notifyDataSetChanged();


        if (iiboolean) {
            actionRecyele.setLoadMoreComplete();
        } else {
            actionRecyele.setRefreshComplete();
        }
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
    protected void show() {
        super.show();
        ActivityMain activity = (ActivityMain) APP.activity;
        RadioGroup mainRg = activity.getMainRg();
        RelativeLayout mainBar = activity.getMainBar();
        mainRg.setVisibility(View.VISIBLE);
        mainBar.setVisibility(View.VISIBLE);
//        FragmentBuilder.getInstance().addStack(getClass().getName());

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
