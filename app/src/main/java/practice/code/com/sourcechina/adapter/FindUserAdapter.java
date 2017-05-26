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
import practice.code.com.sourcechina.entity.FindUserBean;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class FindUserAdapter extends BaseAdapter<FindUserBean.UserBean> {
    public FindUserAdapter(Context context,  List<FindUserBean.UserBean> datas) {
        super(context,  R.layout.fan_or_guan_lyout , datas);
    }

    @Override
    public void convert(ViewHolder holder, FindUserBean.UserBean userBean) {
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


        holder.setText(R.id.fian_guan_title,userBean.getName());
        holder.setText(R.id.fian_guan_context,userBean.getFrom());

    }
}
