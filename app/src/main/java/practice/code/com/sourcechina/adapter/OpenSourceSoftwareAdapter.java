package practice.code.com.sourcechina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import practice.code.com.sourcechina.entity.SortBean;
import practice.code.com.sourcechina.fragment.BaseFragemnt;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class OpenSourceSoftwareAdapter extends FragmentPagerAdapter {
//
    String[] arr = new String[]{"分类","推荐","最新","热门","国产"};
    ArrayList<BaseFragemnt> baseFragemnts;
    public OpenSourceSoftwareAdapter(FragmentManager fm, ArrayList<BaseFragemnt> baseFragemnts) {
        super(fm);
        this.baseFragemnts = baseFragemnts;
    }

    @Override
    public Fragment getItem(int position) {
        return baseFragemnts.get(position);
    }

    @Override
    public int getCount() {
        return baseFragemnts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arr[position];
    }
}
