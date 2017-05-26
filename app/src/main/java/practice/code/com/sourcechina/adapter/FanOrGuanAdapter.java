package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import butterknife.Bind;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.FanOrGuanBean;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class FanOrGuanAdapter extends BaseAdapter<FanOrGuanBean.FriendBean> {



    public FanOrGuanAdapter(Context context, List<FanOrGuanBean.FriendBean> datas) {
        super(context, R.layout.fan_or_guan_lyout, datas);
    }

    @Override
    public void convert(ViewHolder holder, FanOrGuanBean.FriendBean friendsBean) {
        ImageView view = holder.getView(R.id.fan_guan_img);

        Glide.with(APP.activity).load(friendsBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(view){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                view.setImageDrawable(roundedBitmapDrawable);
            }
        });

        holder.setText(R.id.fian_guan_title,friendsBean.getName());
        holder.setText(R.id.fian_guan_context,friendsBean.getExpertise());
        holder.setText(R.id.fian_guan_sha,friendsBean.getFrom());

    }
}
