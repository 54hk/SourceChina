package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import butterknife.Bind;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.ORecommendBean;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class ORecommendFAdapter extends BaseAdapter<ORecommendBean.SoftwareBean> {


    public ORecommendFAdapter(Context context, List<ORecommendBean.SoftwareBean> datas) {
        super(context, R.layout.orecommend_layout, datas);
    }

    @Override
    public void convert(ViewHolder holder, ORecommendBean.SoftwareBean softwareBean) {

       holder.setText(R.id.orement_title,softwareBean.getName());
        holder.setText(R.id.orement_body,softwareBean.getDescription());
        Log.d("ORecommendFAdapter", softwareBean.getId());

    }
}
