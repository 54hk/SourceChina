package practice.code.com.sourcechina.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.CompositeVPAdapter;

public class CompositeFragment extends BaseFragemnt implements View.OnLongClickListener{


    ArrayList<Fragment> fragments = new ArrayList<>();

    TabLayout activityCompositeTab;
    ViewPager activityCompositeVP;
    @Bind(R.id.activity_composite)
    LinearLayout activityComposite;
    private CompositeVPAdapter comVPAdapter;
    @Bind(R.id.zhuan_img)
    ImageView zhuanImg;

    boolean isboolean;
    @Bind(R.id.yan_frame)
    LinearLayout yanFrame;
    @Bind(R.id.have_grid)
    GridLayout haveGrid;
    int mMargin = 5;

    ArrayList<String> strings = new ArrayList<>();
    ArrayList<String>  remove  = new ArrayList<>();
    String[] remove_txt = new String[]{"最新翻译","移动开发","开源硬件",
            "云计算","企业开发","数据库",
            "开源访谈","最新软件","高手问答 "};
    @Bind(R.id.remove_grid)
    GridLayout removeGrid;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.activity_composite;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate) {
        activityCompositeTab = (TabLayout) inflate.findViewById(R.id.activity_composite_tab);
        activityCompositeVP = (ViewPager) inflate.findViewById(R.id.activity_composite_vp);
        strings.add("开源中国");
        strings.add("推荐博客");
        strings.add("技术问答");
        strings.add("每日一博");

        for(int o = 0 ; o <remove_txt.length ; o++){
            remove.add(remove_txt[o]);
        }

        addTxtView();
        remove();

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
    public void addTxtView() {
        for (int i = 0; i < strings.size(); i++) {

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.width = getResources().getDisplayMetrics().widthPixels / 4 - mMargin * 2;
            layoutParams.setMargins(mMargin, mMargin, mMargin, mMargin);
            TextView textView = new TextView(getContext());
            textView.setBackgroundResource(R.drawable.txt_shp_top_2_boween);
            textView.setLayoutParams(layoutParams);
            textView.setText(strings.get(i));
            textView.setPadding(5, 5, 5, 5);
            textView.setGravity(Gravity.CENTER);
            textView.setOnLongClickListener(this);
            haveGrid.addView(textView, 0);
        }


        for (int i = 0; i < haveGrid.getChildCount(); i++) {
            final TextView childView = (TextView) haveGrid.getChildAt(i);

            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove.clear();
                    remove.add(childView.getText().toString());
                    haveGrid.removeView(childView);
                    remove();

                }
            });

        }

    }


    public void remove() {
        TextView textView = null;
        for (int i = 0; i < remove.size(); i++) {

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.width = getResources().getDisplayMetrics().widthPixels / 4 - mMargin * 2;
            layoutParams.setMargins(mMargin, mMargin, mMargin, mMargin);
            textView = new TextView(getContext());
            textView.setBackgroundResource(R.drawable.txt_shp_top_2_boween);
            textView.setLayoutParams(layoutParams);
            textView.setText(remove.get(i));
            textView.setPadding(5, 5, 5, 5);
            textView.setGravity(Gravity.CENTER);
            textView.setOnLongClickListener(this);

            removeGrid.addView(textView, 0);
        }


        for (int i = 0; i < removeGrid.getChildCount(); i++) {
            final TextView childView = (TextView) removeGrid.getChildAt(i);

            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strings.clear();
                    strings.add(childView.getText().toString());
                    removeGrid.removeView(childView);
                    addTxtView();

                }
            });

        }
    }
    @OnClick(R.id.zhuan_img)
    public void onViewClicked() {

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.one);
        animation.setStartTime(1000);
        zhuanImg.startAnimation(animation);

// true
        if (!isboolean) {
            yanFrame.setVisibility(View.VISIBLE);
            activityCompositeTab.setVisibility(View.INVISIBLE);
            Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.to_from);
            animation1.restrictDuration(1000);
            yanFrame.startAnimation(animation1);
            isboolean = true;
            ActivityMain activity = (ActivityMain) APP.activity;
            RadioGroup mainRg = activity.getMainRg();
            mainRg.setVisibility(View.GONE);


        } else {
            yanFrame.setVisibility(View.GONE);
            activityCompositeTab.setVisibility(View.VISIBLE);
            Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.from_to);
            animation1.restrictDuration(1000);
            yanFrame.startAnimation(animation1);
            isboolean = false;
            ActivityMain activity = (ActivityMain) APP.activity;
            RadioGroup mainRg = activity.getMainRg();
            mainRg.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    @Override
    protected void getLoad(boolean b) {


    }

    @Override
    protected void sendData() {
        HeadFragment headFragment = new HeadFragment();
        fragments.add(headFragment);
        RecommendBlogsFragment recommendBlogsFragment = new RecommendBlogsFragment();
        fragments.add(recommendBlogsFragment);
        WIFIMessageFragment wifiMessageFragment = new WIFIMessageFragment();
        fragments.add(wifiMessageFragment);
        RecommendFragment recommendFragment = new RecommendFragment();
        fragments.add(recommendFragment);

        comVPAdapter = new CompositeVPAdapter( APP.fragmentManager, fragments);

        activityCompositeVP.setAdapter(comVPAdapter);
        activityCompositeVP.setOffscreenPageLimit(4);
        activityCompositeTab.setupWithViewPager(activityCompositeVP);
        activityCompositeTab.setTabMode(TabLayout.MODE_FIXED);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
