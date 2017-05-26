package practice.code.com.sourcechina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class TweetVPAdapter extends FragmentPagerAdapter{

    String[] arr = new String[]{"最新动弹","热门动弹","我的动弹"};

    ArrayList<Fragment> list;
    public TweetVPAdapter(FragmentManager fm,ArrayList<Fragment> list) {
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
        return arr[position];
    }
}