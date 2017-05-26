package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.SortBean;
import practice.code.com.sourcechina.fragment.kyrj.ScanFragment;
import practice.code.com.sourcechina.fragment.kyrj.SortFragment;
import practice.code.com.sourcechina.fragment.kyrj.TwoSortFragment;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class OSortFragmentAdapter extends BaseAdapter<SortBean.SoftwareTypeBean> {
    public OSortFragmentAdapter(Context context,  List<SortBean.SoftwareTypeBean> datas) {
        super(context, R.layout.osort_layout, datas);
    }

    @Override
    public void convert(ViewHolder holder, final SortBean.SoftwareTypeBean softwareTypeBean) {

        holder.setText(R.id.sort_name,softwareTypeBean.getName());

        holder.setOnclickListener(R.id.sort_params, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Bundle bundle = new Bundle();
                bundle.putString("tag",softwareTypeBean.getTag());


                FragmentUtils.getFragment().init(APP.activity).getStart(TwoSortFragment.class).setParme(bundle).build();*/
                FragmentManager supportFragmentManager = APP.activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                TwoSortFragment twoSortFragment = new TwoSortFragment();
                Bundle bundle = new Bundle();
                bundle.putString("tag",softwareTypeBean.getTag());
                twoSortFragment.Parmes(bundle);
                fragmentTransaction.replace(R.id.open_source_frame,twoSortFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }
}
