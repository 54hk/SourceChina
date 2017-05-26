package practice.code.com.sourcechina.Activity;


import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.fragment.CompositeFragment;
import practice.code.com.sourcechina.fragment.FindFragment;
import practice.code.com.sourcechina.fragment.FindWhatFragment;
import practice.code.com.sourcechina.fragment.MineFragment;
import practice.code.com.sourcechina.fragment.PortraitUpdate.PotrUpdateFragment;
import practice.code.com.sourcechina.fragment.RecommendBlogsFragment;
import practice.code.com.sourcechina.fragment.TweetFragment;

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
    @Bind(R.id.main_rg)
    RadioGroup mainRg;
    private CompositeFragment compositeFragment;
    private RecommendBlogsFragment recommendBlogsFragment;
    private MineFragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        SimpleFragmentBuilder.getInstance().start(CompositeFragment.class);
        FragmentUtils.getFragment().init(this).getStart(CompositeFragment.class).build();
        sumBt.setChecked(true);


    }


    public RelativeLayout getMainBar() {
        return mainBar;
    }

    public void setMainBar(RelativeLayout mainBar) {
        this.mainBar = mainBar;
    }

    public RadioGroup getMainRg() {
        return mainRg;
    }

    public void setMainRg(RadioGroup mainRg) {
        this.mainRg = mainRg;
    }

    @OnClick({R.id.sum_bt, R.id.ball_bt, R.id.find_bt, R.id.mime_bt})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.sum_bt:
                barTxt.setText("综合");
//                FragmentBuilder.getInstance().addBack(true).start(CompositeFragment.class).addBack(true).builde();
//                SimpleFragmentBuilder.getInstance().start(CompositeFragment.class);
                FragmentUtils.getFragment().init(this).getStart(CompositeFragment.class).build();
                break;
            case R.id.ball_bt:
                barTxt.setText("动弹");
//                FragmentBuilder.getInstance().addBack(true).start(TweetFragment.class).addBack(true).builde();
//                SimpleFragmentBuilder.getInstance().start(TweetFragment.class);
                FragmentUtils.getFragment().init(this).getStart(TweetFragment.class).build();
                break;
            case R.id.find_bt:
//                FragmentBuilder.getInstance().start(BallCommentFragment.class);
                FragmentUtils.getFragment().init(this).getStart(BallCommentFragment.class).build();
                break;
            case R.id.mime_bt:
                barTxt.setText("发现");
//                FragmentBuilder.getInstance().addBack(true).start(FindFragment.class).addBack(true).builde();
//                SimpleFragmentBuilder.getInstance().start(FindFragment.class);
                FragmentUtils.getFragment().init(this).getStart(FindFragment.class).build();
                break;
        }
    }

    @OnClick(R.id.mine_true)
    public void onmineClicked() {
        barTxt.setText("我的");
//        FragmentBuilder.getInstance().addBack(true).start(MineFragment.class).builde();
//        SimpleFragmentBuilder.getInstance().start(MineFragment.class);
        FragmentUtils.getFragment().init(this).getStart(MineFragment.class).build();
    }
    long firstTime = 0;
    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
        FragmentManager manager = getSupportFragmentManager();
        FragmentManager.BackStackEntry entryAt = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1);
        String name = entryAt.getName();
        if ("FindFragment".equals(name) || "TweetFragment".equals(name) || "MineFragment".equals(name) || "CompositeFragment".equals(name)) {
            if(System.currentTimeMillis() - firstTime > 2000){
                Toast.makeText(this, "请再次点击退出", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
        } else {
            Log.d("ActivityMain", "manager.getBackStackEntryCount():" + manager.getBackStackEntryCount());
            manager.popBackStackImmediate();
            String name1 = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
           // Log.d("ActivityMainmmmm", name1);
            BaseFragemnt fragmentByTag = (BaseFragemnt) manager.findFragmentByTag(name1);
            FragmentUtils.getFragment().lastFragment = fragmentByTag;
        }

    }




    @OnClick(R.id.bar_image)
    public void onViewClicked() {
        FragmentUtils.getFragment().init(this).getStart( FindWhatFragment.class).build();
    }
}
