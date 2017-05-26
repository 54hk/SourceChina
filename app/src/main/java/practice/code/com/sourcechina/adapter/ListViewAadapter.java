package practice.code.com.sourcechina.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.TweetDetilsBean;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class ListViewAadapter extends BaseAdapter {
    List<TweetDetilsBean.TweetBean.UserBean> likeList;
    Context context;
    public ListViewAadapter(Context context , List<TweetDetilsBean.TweetBean.UserBean> likeList){
        this.likeList = likeList;
        this.context =context;
    }

    @Override
    public int getCount() {
        return likeList.size();
    }

    @Override
    public Object getItem(int position) {
        return likeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return likeList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyHolder myHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_ad  , null);
//            myHolder.imageView = (ImageView) convertView.findViewById(R.id.lv_img);
            myHolder.textView = (TextView) convertView.findViewById(R.id.lv_txt);
            convertView.setTag(myHolder);
        }else{
            myHolder = (MyHolder) convertView.getTag();
        }
        myHolder.textView.setText(likeList.get(position).getName());
//       if(myHolder.imageView != null){
//           Glide.with(context).load(likeList.get(position).getPortrait()).into(myHolder.imageView);
//       }


        return convertView;
    }

    class  MyHolder{
        TextView textView;
        ImageView imageView;
    }
}
