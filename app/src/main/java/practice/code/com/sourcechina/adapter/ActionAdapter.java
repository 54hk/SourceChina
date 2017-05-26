package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.ActionBean;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class ActionAdapter extends BaseAdapter<ActionBean.EventBean> {
    public ActionAdapter(Context context, List<ActionBean.EventBean> datas) {
        super(context, R.layout.xian_aciton_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, ActionBean.EventBean eventBean) {

        ImageView view = holder.getView(R.id.findeventImae);
        Glide.with(context).load(eventBean.getCover()).into(view);
        holder.setText(R.id.eventTitle,eventBean.getSpot());
        if (eventBean.getStatus().equals("3")){
            holder.setText(R.id.eventStatus,"截止报名");
        }else {
            holder.setText(R.id.eventStatus,"正在报名").setTextColor(R.id.eventStatus, Color.parseColor("#24CF5F"));
        }
        holder.setText(R.id.eventTime,eventBean.getStartTime());
    }



}
