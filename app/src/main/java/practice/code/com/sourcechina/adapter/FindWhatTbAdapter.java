package practice.code.com.sourcechina.adapter;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.FindWhatBean;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class FindWhatTbAdapter extends BaseAdapter<FindWhatBean.ResultBean> {

    public FindWhatTbAdapter(Context context , List<FindWhatBean.ResultBean> datas) {
        super(context,  R.layout.find_what_tab_adapter , datas);
    }

    @Override
    public void convert(ViewHolder holder, FindWhatBean.ResultBean resultBean) {

        holder.setText(R.id.find_title,resultBean.getTitle());
        holder.setText(R.id.find_content,resultBean.getDescription());

    }
}
