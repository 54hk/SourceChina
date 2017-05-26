package practice.code.com.sourcechina.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import butterknife.Bind;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.Activity.SimpleFragmentBuilder;
import practice.code.com.sourcechina.Activity.WebViewAtivity;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.HeadXMLBean;
import practice.code.com.sourcechina.fragment.WebViewFragment;
import practice.code.com.sourcechina.util.DataUtils;
import practice.code.com.sourcechina.util.FragmentBuilder;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class HeadAdapter extends BaseAdapter<HeadXMLBean.NewsBean> {


    public HeadAdapter(Context context , List<HeadXMLBean.NewsBean> datas) {
        super(context, R.layout.head_rc_adapter, datas);
    }

    @Override
    public void convert(ViewHolder holder, final HeadXMLBean.NewsBean newsBean) {
        holder.setText(R.id.head_title, newsBean.getTitle());
        holder.setText(R.id.head_body, newsBean.getBody());
        holder.setText(R.id.head_auther_or_time,
                "@" +newsBean.getAuthor() + "  " + DataUtils.getDate(newsBean.getPubDate()));
        ImageView view = holder.getView(R.id.head_boolean_yz);
        ImageView view1 = holder.getView(R.id.head_boolean_t);
        view.setImageResource(R.mipmap.ic_label_today);
        holder.setText(R.id.head_pinlun,newsBean.getCommentCount());
        boolean jinTian = DataUtils.getJinTian(newsBean.getPubDate());
        if(jinTian){
            view1.setVisibility(View.GONE);
           view.setVisibility(View.VISIBLE);
        }else{
            view1.setVisibility(View.GONE);
            view.setVisibility(View.GONE);

        }
        Log.d("HeadAdapter", newsBean.getId());


        holder.setOnclickListener(R.id.head_item_listener, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Intent intent  =  new Intent(context, WebViewAtivity.class);
//                intent.putExtra("id",newsBean.getId());
//                context.startActivity(intent);

                Bundle bundle=new Bundle();
                bundle.putString("id",newsBean.getId());


                FragmentUtils.getFragment().init(APP.activity).getStart(WebViewFragment.class).setParme(bundle).build();
            }
        });


    }

}
