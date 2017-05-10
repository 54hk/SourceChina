package practice.code.com.sourcechina.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class CompositeVPAdapter extends FragmentPagerAdapter {

    String[] arr = new String[]{"开源咨询","推荐博客"};
    ArrayList<Fragment> fragments;
    public CompositeVPAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return arr[position];
    }
}
