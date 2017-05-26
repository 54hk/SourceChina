package practice.code.com.sourcechina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class FindWhatAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    String[] arr;
    public FindWhatAdapter(FragmentManager fm , ArrayList<Fragment> list , String[] arr) {
        super(fm);
        this.list = list;
        this.arr = arr;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arr[position];
    }
}
