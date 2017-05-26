package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.MyTweetBean;
import practice.code.com.sourcechina.model.HttpUtil.DeleteTweetUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.db.UserBean;
import practice.code.com.sourcechina.model.db.UserDBOpenHelper;
import practice.code.com.sourcechina.util.DataUtils;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class MyTweetadapter extends BaseAdapter<MyTweetBean.TweetBean> {
    String uid = null;
//    String deleteUid = null;
    public MyTweetadapter(Context context , String uid ,List<MyTweetBean.TweetBean> datas) {
        super(context, R.layout.news_tweet_adapter_ly , datas);
/*
        try {
            UserDBOpenHelper helper = new UserDBOpenHelper(APP.activity);
            Dao<UserBean, Integer> dao =  helper.getUserDao();
            List<UserBean> userBeen = dao.queryForAll();
            if(userBeen.size()==0)
            deleteUid = userBeen.get(userBeen.size()-1).getUid();


        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        this.uid  = uid;
    }

    @Override
    public void convert(ViewHolder holder, final MyTweetBean.TweetBean tweetBean) {

       if(uid == null){
            RelativeLayout view = holder.getView(R.id.have_some_re);
            view.setVisibility(View.GONE);
            ImageView frame_img = holder.getView(R.id.frame_tweet_img);
            frame_img.setImageResource(R.mipmap.ic_tip_fail);

        }else{

           FrameLayout frame = holder.getView(R.id.tweet_fragment);
           frame.setVisibility(View.GONE);

           ImageView view = holder.getView(R.id.news_tweet_img);
           Glide.with(context).load(tweetBean.getPortrait()).into(view);
           holder.setText(R.id.news_tweet_title,tweetBean.getAuthor());
           holder.setText(R.id.news_tweet_content,tweetBean.getBody());
           ImageView imageView  =  holder.getView(R.id.news_tweet_gilde);
           if(!tweetBean.getImgSmall().isEmpty()){
               Glide.with(context).load(tweetBean.getImgSmall()).into(imageView);
           }else{
               imageView.setVisibility(View.GONE);
           }
           TextView view1 = holder.getView(R.id.news_tweet_author_time);
           view1.setText(DataUtils.getDate(tweetBean.getPubDate()));


           Log.d("MyTweetadapter", tweetBean.getId());

           RelativeLayout relative = holder.getView(R.id.tweet_params_item);
           relative.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {

                   AlertDialog.Builder builder  = new AlertDialog.Builder(APP.activity);
                   builder.setTitle("是否删除").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                       }
                   }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(final DialogInterface dialog, int which) {
                           DeleteTweetUtil.getInstance().deleteTweetMethod(uid, tweetBean.getId(), new ICallBack() {
                               @Override
                               public void success(String mgs) {
                                   Log.d("MyTweetadapter", mgs);
                                   vibrate();
                                   Toast.makeText(context, "已删除...", Toast.LENGTH_SHORT).show();
                                   dialog.dismiss();
                               }

                               @Override
                               public void fail(String mgs) {
                                   Log.d("MyTweetadapter", mgs);
                               }

                           });
                           MyTweetadapter.this.notifyDataSetChanged();
                       }
                   }).show();


                   return true;
               }
           });

       }

    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) APP.activity.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

}
