package practice.code.com.sourcechina.Activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.CompositeVPAdapter;
import practice.code.com.sourcechina.adapter.HeadAdapter;
import practice.code.com.sourcechina.fragment.HeadFragment;
import practice.code.com.sourcechina.fragment.Head_message_Fragment;
import practice.code.com.sourcechina.fragment.RecommendBlogsFragment;

public class CompositeActivity extends AppCompatActivity {

    @Bind(R.id.activity_composite_tab)
    TabLayout activityCompositeTab;
    @Bind(R.id.activity_composite_vp)
    ViewPager activityCompositeVp;
    @Bind(R.id.activity_composite)
    LinearLayout activityComposite;

    ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composite);
        ButterKnife.bind(this);


        HeadFragment headFragment = new HeadFragment();
        fragments.add(headFragment);
        RecommendBlogsFragment recommendBlogsFragment  =   new RecommendBlogsFragment();
        fragments.add(recommendBlogsFragment);
        CompositeVPAdapter comVPAdapter = new CompositeVPAdapter(getSupportFragmentManager(),fragments);

        activityCompositeVp.setAdapter(comVPAdapter);
        activityCompositeTab.setupWithViewPager(activityCompositeVp);
        activityCompositeTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        
    }
}
