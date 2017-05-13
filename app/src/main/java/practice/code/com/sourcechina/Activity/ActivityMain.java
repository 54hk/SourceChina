package practice.code.com.sourcechina.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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
import practice.code.com.sourcechina.fragment.HeadFragment;
import practice.code.com.sourcechina.fragment.MineFragment;
import practice.code.com.sourcechina.fragment.RecommendBlogsFragment;
import practice.code.com.sourcechina.util.FragmentBuilder;
import practice.code.com.sourcechina.utils.FragmentBulder;

public class ActivityMain extends BaseActivity {

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
    @Bind(R.id.mine_true)
    RadioButton mineTrue;
    private CompositeFragment compositeFragment;
    private RecommendBlogsFragment recommendBlogsFragment;
    private MineFragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FragmentBulder.getInstance().create(CompositeFragment.class);
        sumBt.setChecked(true);

    }








    @OnClick({R.id.sum_bt, R.id.ball_bt, R.id.find_bt, R.id.mime_bt})
    public void onViewClicked(View view) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        hideFragment(transaction);
        switch (view.getId()) {
            case R.id.sum_bt:
                barTxt.setText("综合");
                FragmentBulder.getInstance().create(CompositeFragment.class);
                break;
            case R.id.ball_bt:
                barTxt.setText("动弹");
FragmentBulder.getInstance().create(RecommendBlogsFragment.class);
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

    @OnClick(R.id.mine_true)
    public void onmineClicked() {
FragmentBulder.getInstance().create(MineFragment.class);
    }
}
