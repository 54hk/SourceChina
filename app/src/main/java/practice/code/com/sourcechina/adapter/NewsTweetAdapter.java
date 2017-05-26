package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.NewsTweetBean;
import practice.code.com.sourcechina.entity.TweetDetilsBean;
import practice.code.com.sourcechina.fragment.TweetDetilsFragment;
import practice.code.com.sourcechina.fragment.UserMessageFragment;
import practice.code.com.sourcechina.util.DataUtils;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class NewsTweetAdapter extends BaseAdapter<NewsTweetBean.TweetBean> {


    Map<Integer,Boolean> map;
    private int adapterPosition;
    List<NewsTweetBean.TweetBean> tweetBeen;


    public NewsTweetAdapter(Context context, List<NewsTweetBean.TweetBean> datas) {
        super(context, R.layout.news_tweet_adapter_ly, datas);
        map = new HashMap<>();
        tweetBeen =datas;
    }

    @Override
    public void convert(ViewHolder holder, final NewsTweetBean.TweetBean tweetBean) {

        adapterPosition = holder.getAdapterPosition()-1;


        for (int i = 0; i < tweetBeen.size(); i++) {
            map.put(i,false);
        }





        final CheckBox check = holder.getView(R.id.gret_checkbox);
        check.setText(tweetBean.getLikeCount());
       final String likeCount = tweetBean.getLikeCount();


        Log.d("NewsTweetAdapter", likeCount);


      check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

          private int b;
          private int a;

          @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    a = (int) Integer.parseInt(likeCount) + 1;
                    check.setText(a+"");
                    map.remove(adapterPosition);
                    map.put(adapterPosition,isChecked);

                }else{
                    b = (int) Integer.parseInt(likeCount);
                    check.setText(b+"");
                    map.remove(adapterPosition);
                    map.put(adapterPosition,false);
                }
            }

        });

        check.setChecked(map.get(adapterPosition));



        TextView txt = holder.getView(R.id.great_comment_sum);
        txt.setText(tweetBean.getCommentCount());


        ImageView view = holder.getView(R.id.news_tweet_img);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TODO 跳转到用户信息
                Bundle bundle = new Bundle();
                bundle.putString("uid", tweetBean.getId());
                Log.d("NewsTweetAdapter", tweetBean.getId());
                bundle.putString("nane", tweetBean.getAuthor());

                bundle.putString("img", tweetBean.getPortrait());
                bundle.putString("commentCount", tweetBean.getCommentCount());
                bundle.putString("like", tweetBean.getLikeCount());
                FragmentUtils.getFragment().init(APP.activity).getStart(UserMessageFragment.class).setParme(bundle).build();
            }
        });
        Glide.with(APP.activity).load(tweetBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(view){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                view.setImageDrawable(roundedBitmapDrawable);
            }
        });
        holder.setText(R.id.news_tweet_title, tweetBean.getAuthor());
        holder.setText(R.id.news_tweet_content, tweetBean.getBody());
        ImageView imageView = holder.getView(R.id.news_tweet_gilde);
        if (!tweetBean.getImgSmall().isEmpty()) {
            Glide.with(context).load(tweetBean.getImgSmall()).into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }
        TextView view1 = holder.getView(R.id.news_tweet_author_time);
        view1.setText(DataUtils.getDate(tweetBean.getPubDate()));


        holder.setOnclickListener(R.id.tweet_params_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("nane", tweetBean.getAuthor());
                bundle.putString("body", tweetBean.getBody());
                bundle.putString("data", DataUtils.getDate(tweetBean.getPubDate()));
                bundle.putString("img", tweetBean.getPortrait());
                bundle.putString("commentCount", tweetBean.getCommentCount());
                bundle.putString("like", tweetBean.getLikeCount());

                SharedPreferences sharedPreferences = APP.activity.getSharedPreferences("", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("id", tweetBean.getId());
                edit.commit();

                FragmentUtils.getFragment().init(APP.activity).getStart(TweetDetilsFragment.class).setParme(bundle).build();
            }
        });


    }

    @OnClick({R.id.gret_checkbox, R.id.great_comment_sum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gret_checkbox:
                break;
            case R.id.great_comment_sum:
                break;
        }
    }
}
