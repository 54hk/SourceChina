package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.ThreeSortBean;
import practice.code.com.sourcechina.fragment.kyrj.ThreeSortFragment;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class ThreeSortAdapter extends BaseAdapter<ThreeSortBean.SoftwareBean> {


    public ThreeSortAdapter(Context context,  List<ThreeSortBean.SoftwareBean> datas) {
        super(context,  R.layout.orecommend_layout, datas);
    }

    @Override
    public void convert(ViewHolder holder, ThreeSortBean.SoftwareBean softwareBean) {
        holder.setText(R.id.orement_title,softwareBean.getName());
        holder.setText(R.id.orement_body , softwareBean .getDescription());
        Log.d("ThreeSortAdapter", softwareBean.getId());
    }
}
