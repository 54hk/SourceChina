package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.FanOrGuanBean;
import practice.code.com.sourcechina.entity.TweetDetilsBean;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class GreatAdapter extends  BaseAdapter<TweetDetilsBean.TweetBean.UserBean> {


    public GreatAdapter(Context context,  List<TweetDetilsBean.TweetBean.UserBean> datas) {
        super(context, R.layout.fan_or_guan_lyout, datas);
    }


    @Override
    public void convert(ViewHolder holder, TweetDetilsBean.TweetBean.UserBean userBean) {
        ImageView view = holder.getView(R.id.fan_guan_img);
        Glide.with(APP.activity).load(userBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(view){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                view.setImageDrawable(roundedBitmapDrawable);
            }
        });


        Glide.with(APP.activity).load(userBean.getPortrait()).into(view);
        holder.setText(R.id.fian_guan_title,userBean.getName());


    }
}
