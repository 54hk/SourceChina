package practice.code.com.sourcechina.adapter;


import android.content.Context;
import android.widget.TextView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import butterknife.Bind;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.HeadXMLBean;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class HeadAdapter extends BaseAdapter<HeadXMLBean.NewsBean> {


    public HeadAdapter(Context context , List<HeadXMLBean.NewsBean> datas) {
        super(context, R.layout.head_rc_adapter, datas);
    }

    @Override
    public void convert(ViewHolder holder, HeadXMLBean.NewsBean newsBean) {
        holder.setText(R.id.head_title, newsBean.getTitle());
        holder.setText(R.id.head_body, newsBean.getBody());
        holder.setText(R.id.head_auther_or_time, newsBean.getAuthor() + "  " + newsBean.getPubDate());
    }
}
