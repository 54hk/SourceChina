package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.HeadXMLBean;
import practice.code.com.sourcechina.entity.RecommendBlogXMLBean;
import practice.code.com.sourcechina.fragment.RecommendBlogsWebViewFrgment;
import practice.code.com.sourcechina.fragment.WebViewFragment;
import practice.code.com.sourcechina.util.DataUtils;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class RecommendBlogsAdapter extends BaseAdapter<RecommendBlogXMLBean.BlogBean> {


    public RecommendBlogsAdapter(Context context ,List<RecommendBlogXMLBean.BlogBean> bolgAlls) {
        super(context, R.layout.head_rc_adapter, bolgAlls);
    }

    @Override
    public void convert(ViewHolder holder, final RecommendBlogXMLBean.BlogBean newsBean) {
        holder.setText(R.id.head_title, newsBean.getTitle());
        holder.setText(R.id.head_body, newsBean.getBody());
        holder.setText(R.id.head_auther_or_time,"@"+ newsBean.getAuthorname() + "  " + DataUtils.getDate(newsBean.getPubDate()));
        ImageView view = holder.getView(R.id.head_boolean_yz);
        ImageView view1 = holder.getView(R.id.head_boolean_t);
        view.setImageResource(R.mipmap.ic_label_originate);
        view1.setImageResource(R.mipmap.ic_label_recommend);
        holder.setText(R.id.head_pinlun,newsBean.getCommentCount());
        LinearLayout view2 = holder.getView(R.id.head_item_listener);

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("id",newsBean.getId());
                FragmentUtils.getFragment().init(APP.activity).getStart(RecommendBlogsWebViewFrgment.class).setParme(bundle).build();
            }
        });


    }
}