package practice.code.com.sourcechina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class MessgeCenterdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    String[] rr = new String[]{"@我","评论"};
    public MessgeCenterdapter(FragmentManager fm , ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
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
        return rr[position];
    }
}
