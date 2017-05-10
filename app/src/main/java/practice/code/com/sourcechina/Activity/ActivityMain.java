package practice.code.com.sourcechina.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.HeadFragment;
import practice.code.com.sourcechina.fragment.RecommendBlogsFragment;

public class ActivityMain extends Activity {

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
    private HeadFragment headFragment;
    private Fragment BaseFragment;
    private RecommendBlogsFragment recommendBlogsFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        headFragment = new HeadFragment();
        if (BaseFragment!=null){
            fragmentTransaction.hide(BaseFragment);
        }
        fragmentTransaction.add(R.id.activity_main_fl, headFragment,"1");
        BaseFragment=headFragment;
        fragmentTransaction.commit();
    }

    @OnClick({R.id.sum_bt, R.id.ball_bt, R.id.find_bt, R.id.mime_bt})
    public void onViewClicked(View view) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.sum_bt:
                headFragment = new HeadFragment();
                if (BaseFragment!=null){
                    fragmentTransaction.hide(BaseFragment);
                }
                fragmentTransaction.add(R.id.activity_main_fl, headFragment,"1");
                BaseFragment=headFragment;
                fragmentTransaction.commit();
                break;
            case R.id.ball_bt:
                recommendBlogsFragment = new RecommendBlogsFragment();
                if (BaseFragment!=null){
                    fragmentTransaction.hide(BaseFragment);
                }
                fragmentTransaction.add(R.id.activity_main_fl,recommendBlogsFragment,"2");
                BaseFragment=recommendBlogsFragment;
                fragmentTransaction.commit();
                break;
            case R.id.find_bt:
                break;
            case R.id.mime_bt:
                break;
        }
    }



}
