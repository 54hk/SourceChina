package practice.code.com.sourcechina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class TweetDetilsAdapter extends FragmentPagerAdapter {


    ArrayList<Fragment> views;
    ArrayList<String> strings;

    public TweetDetilsAdapter(FragmentManager fm ,ArrayList<Fragment> views , ArrayList<String> strings) {
        super(fm);
        this.views = views;
        this.strings = strings;
    }


    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
