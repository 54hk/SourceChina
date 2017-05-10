package practice.code.com.sourcechina.adapter;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.HeadXMLBean;
import practice.code.com.sourcechina.entity.RecommendBlogXMLBean;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class RecommendBlogsAdapter extends BaseAdapter<RecommendBlogXMLBean.BlogBean> {


    public RecommendBlogsAdapter(Context context ,ArrayList<RecommendBlogXMLBean.BlogBean> bolgAlls) {
        super(context, R.layout.head_rc_adapter, bolgAlls);
    }

    @Override
    public void convert(ViewHolder holder, RecommendBlogXMLBean.BlogBean newsBean) {
        holder.setText(R.id.head_title, newsBean.getTitle());
        holder.setText(R.id.head_body, newsBean.getBody());
        holder.setText(R.id.head_auther_or_time, newsBean.getAuthorname() + "  " + newsBean.getPubDate());
    }
}