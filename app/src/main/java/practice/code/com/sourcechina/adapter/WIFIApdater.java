package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.WIFIMessageBean;
import practice.code.com.sourcechina.fragment.WebViewFragment;
import practice.code.com.sourcechina.util.DataUtils;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class WIFIApdater extends BaseAdapter<WIFIMessageBean.NewsBean> {
    public WIFIApdater(Context context,  List<WIFIMessageBean.NewsBean> datas) {
        super(context, R.layout.head_rc_adapter, datas);
    }

    @Override
    public void convert(ViewHolder holder, final WIFIMessageBean.NewsBean wifiMessageBean) {
        holder.setText(R.id.head_title, wifiMessageBean.getTitle());
        holder.setText(R.id.head_body, wifiMessageBean.getBody());
        holder.setText(R.id.head_auther_or_time, wifiMessageBean.getAuthorid() + "  " + DataUtils.getDate( wifiMessageBean.getPubDate()));
        ImageView view = holder.getView(R.id.head_boolean_yz);
        ImageView view1 = holder.getView(R.id.head_boolean_t);
        holder.setText(R.id.head_pinlun,wifiMessageBean.getCommentCount());
        view.setVisibility(View.GONE);
        view1.setVisibility(View.GONE);

        holder.setOnclickListener(R.id.head_item_listener, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Intent intent  =  new Intent(context, WebViewAtivity.class);
//                intent.putExtra("id",newsBean.getId());
//                context.startActivity(intent);

                Bundle bundle=new Bundle();
                bundle.putString("id",wifiMessageBean.getId());
                FragmentUtils.getFragment().init(APP.activity).getStart(WebViewFragment.class).setParme(bundle).build();
//                FragmentBuilder.getInstance().start(WebViewFragment.class).addBack(true).setParme(bundle).builde();
//                SimpleFragmentBuilder.getInstance().start(WebViewFragment.class).setParme(bundle);
            }
        });

    }
}
