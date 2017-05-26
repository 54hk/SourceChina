package practice.code.com.sourcechina.adapter;

import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import practice.code.com.sourcechina.R;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public class TestNormalAdapter  extends StaticPagerAdapter {
    private int[] imgs = {
            R.mipmap.kkkk,
            R.mipmap.llll,
            R.mipmap.hhh,
            R.mipmap.xxx,
    };


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());

        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType. CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }


    @Override
    public int getCount() {
        return imgs.length;
    }
}

