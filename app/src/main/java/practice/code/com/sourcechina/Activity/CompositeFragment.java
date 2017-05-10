package practice.code.com.sourcechina.Activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CompositeFragment extends Fragment {


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
        CompositeVPAdapter comVPAdapter = new CompositeVPAdapter(getActivity().getSupportFragmentManager(),fragments);

        activityCompositeVp.setAdapter(comVPAdapter);
        activityCompositeTab.setupWithViewPager(activityCompositeVp);
        activityCompositeTab.setTabMode(TabLayout.MODE_SCROLLABLE);

        return inflate;
    }


}
