package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.TwoSortBean;
import practice.code.com.sourcechina.fragment.WebViewFragment;
import practice.code.com.sourcechina.fragment.kyrj.ThreeSortFragment;
import practice.code.com.sourcechina.fragment.kyrj.TwoSortFragment;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class TwoSortAdapter extends BaseAdapter<TwoSortBean.SoftwareTypeBean> {

    public TwoSortAdapter(Context context,List<TwoSortBean.SoftwareTypeBean> datas) {
        super(context,  R.layout.osort_layout, datas);
    }

    @Override
    public void convert(ViewHolder holder, final TwoSortBean.SoftwareTypeBean softwareTypeBean) {
        holder.setText(R.id.sort_name,softwareTypeBean.getName());
        holder.setOnclickListener(R.id.sort_params, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Bundle bundle = new Bundle();
                bundle.putString("tag",softwareTypeBean.getTag());
                Log.d("TwoSortAdapter", softwareTypeBean.getTag());
                FragmentUtils.getFragment().init(APP.activity).getStart(ThreeSortFragment.class).setParme(bundle).build();*/
                FragmentManager supportFragmentManager = APP.activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                ThreeSortFragment threeSortFragment = new ThreeSortFragment();
                Bundle bundle = new Bundle();
                bundle.putString("tag",softwareTypeBean.getTag());
                threeSortFragment.Parmes(bundle);
                fragmentTransaction.replace(R.id.open_source_frame,threeSortFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}
