package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.thoughtworks.xstream.XStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.HeadXMLBean;
import practice.code.com.sourcechina.entity.WebViewHeadBean;
import practice.code.com.sourcechina.entity.WebViewTweetBean;
import practice.code.com.sourcechina.model.HttpUtil.TweetDetailsUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/18 0018.
 * 没用
 */

public class WebViewTweetFragment extends BaseFragemnt implements ICallBack {
    @Bind(R.id.web_back)
    ImageView webBack;
    @Bind(R.id.head_web_view)
    WebView headWebView;
    private String id;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.tweet_webview_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        id = getParmes().getString("id");
        Log.e("ijhjhjd", "-----------------------" + id);
    }

    @Override
    protected void getLoad(boolean b) {

    }

    @Override
    protected void sendData() {

        TweetDetailsUtil.getInstance().getLoadDetails(id,this);

    }

    @Override
    public void success(String mgs) {

        Log.e("headFragment",mgs);
        XStream xs = new XStream();
        xs.alias("oschina", WebViewTweetBean.class);
        xs.alias("tweet", WebViewTweetBean.TweetBean.class);
        xs.alias("notice", WebViewTweetBean.NoticeBean.class);
        WebViewTweetBean webViewTweetBean = (WebViewTweetBean) xs.fromXML(mgs);

    }

    @Override
    public void fail(String mgs) {

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
