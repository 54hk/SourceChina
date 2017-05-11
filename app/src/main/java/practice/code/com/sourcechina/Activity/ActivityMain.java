package practice.code.com.sourcechina.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.CompositeFragment;
import practice.code.com.sourcechina.fragment.RecommendBlogsFragment;

public class ActivityMain extends AllActivityParent {

    @Bind(R.id.activity_main_fl)
    FrameLayout activityMainFl;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.sum_bt)
    RadioButton sumBt;
    @Bind(R.id.ball_bt)
    RadioButton ballBt;
    @Bind(R.id.find_bt)
    ImageView findBt;
    @Bind(R.id.mime_bt)
    RadioButton mimeBt;
    @Bind(R.id.bar_txt)
    TextView barTxt;
    @Bind(R.id.bar_image)
    ImageView barImage;
    @Bind(R.id.main_bar)
    RelativeLayout mainBar;
    private CompositeFragment compositeFragment;
    private RecommendBlogsFragment recommendBlogsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment1();

        sumBt.setChecked(true);

    }


    //显示第一个fragment
    private void initFragment1() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (compositeFragment == null) {
            compositeFragment = new CompositeFragment();
            transaction.add(R.id.activity_main_fl, compositeFragment);
        }
        hideFragment(transaction);
        //隐藏所有fragment
        //显示需要显示的fragment
        transaction.show(compositeFragment);
        transaction.commit();
    }


    //显示第2个fragment
    private void initFragment2() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        if (recommendBlogsFragment == null) {
            recommendBlogsFragment = new RecommendBlogsFragment();
            transaction.add(R.id.activity_main_fl, recommendBlogsFragment);
        }
        hideFragment(transaction);
        //隐藏所有fragment
        //显示需要显示的fragment
        transaction.show(recommendBlogsFragment);
        transaction.commit();
    }


    private void hideFragment(FragmentTransaction transaction) {
        if (compositeFragment != null) {
            transaction.hide(compositeFragment);
        }
        if (recommendBlogsFragment != null) {
            transaction.hide(recommendBlogsFragment);
        }

    }

    @OnClick({R.id.sum_bt, R.id.ball_bt, R.id.find_bt, R.id.mime_bt})
    public void onViewClicked(View view) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        hideFragment(transaction);
        switch (view.getId()) {
            case R.id.sum_bt:
                barTxt.setText("综合");
                initFragment1();
                break;
            case R.id.ball_bt:
                barTxt.setText("动弹");
                initFragment2();
                break;
            case R.id.find_bt:
                barTxt.setText("发现");
                break;
            case R.id.mime_bt:
                break;
        }
    }


    @OnClick(R.id.find_bt)
    public void onViewClicked() {
        Intent intent = new Intent(ActivityMain.this, BallCommentActivity.class);
        startActivity(intent);
    }
}
