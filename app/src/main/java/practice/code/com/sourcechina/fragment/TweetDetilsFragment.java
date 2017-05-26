package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thoughtworks.xstream.XStream;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.ListViewAadapter;
import practice.code.com.sourcechina.adapter.TweetDetilsAdapter;
import practice.code.com.sourcechina.entity.HeadXMLBean;
import practice.code.com.sourcechina.entity.TweetDetilsBean;
import practice.code.com.sourcechina.model.HttpUtil.TweetDetailsUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class TweetDetilsFragment extends BaseFragemnt{
    @Bind(R.id.tweet_detils_img)
    ImageView tweetDetilsImg;
    TextView tweetDetilsTitle;
    @Bind(R.id.tweet_detils_content)
    TextView tweetDetilsContent;
    @Bind(R.id.tweet_detils_time)
    TextView tweetDetilsTime;
    TweetDetilsAdapter tweetDetilsAdapter;
    @Bind(R.id.tweet_detils_tab)
    TabLayout tweetDetilsTab;
    @Bind(R.id.tweet_detils_vp)
    ViewPager tweetDetilsVp;
    private String nane;
    private String body;
    private String data;
    private String img;
    private ArrayList<Fragment> viweList;
    private String uid;

    ListView listView = null;
    private String commentCount;
    private String like;
    ArrayList<String> getStrings= new ArrayList<>();
    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.tweet_detils_lyout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        nane = getParmes().getString("nane");
        body = getParmes().getString("body");
        data = getParmes().getString("data");
        img = getParmes().getString("img");
        uid = getParmes().getString("uid");

        commentCount = getParmes().getString("commentCount");
        like = getParmes().getString("like");
        getStrings.add("赞"+"("+like+")");
        getStrings.add("评论"+"("+commentCount+")");

        Log.d("TweetDetilsFragment", img);

        tweetDetilsTitle = (TextView) inflate1.findViewById(R.id.tweet_detils_title);

        tweetDetilsTitle.setText(nane);
        tweetDetilsContent.setText(body);
        tweetDetilsTime.setText(data);

       viweList = new ArrayList<>();


           GreatDetilsFragment greatDetilsFragment =new GreatDetilsFragment();
           viweList.add(greatDetilsFragment);
           GreaCommentFragment  greaCommentFragment = new GreaCommentFragment();
           viweList.add(greaCommentFragment);

        tweetDetilsAdapter  = new TweetDetilsAdapter(getChildFragmentManager(),viweList,getStrings);
        tweetDetilsVp.setAdapter(tweetDetilsAdapter);
        tweetDetilsVp.setOffscreenPageLimit(2);

        tweetDetilsTab.setTabMode(TabLayout.MODE_FIXED);
        tweetDetilsTab.setupWithViewPager(tweetDetilsVp);

    }

    @Override
    protected void getLoad(boolean b) {

    }

    @Override
    protected void sendData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(getContext()).load(img).into(tweetDetilsImg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
