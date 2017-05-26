package practice.code.com.sourcechina.Activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.thoughtworks.xstream.XStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.WebViewHeadBean;
import practice.code.com.sourcechina.model.HttpUtil.HeadNewDetailsUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class WebViewAtivity extends BaseActivity implements ICallBack {

    @Bind(R.id.head_web_view)
    WebView headWebView;
    @Bind(R.id.web_back)
    ImageView webBack;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_web_view);
        ButterKnife.bind(this);

        id = getIntent().getStringExtra("id");


    }

    @Override
    protected void onResume() {
        super.onResume();
        HeadNewDetailsUtil.getInstance().getLoadDetails(id, this);
    }

    @Override
    public void success(String mgs) {

        XStream xStream = new XStream();

        xStream.alias("oschina", WebViewHeadBean.class);
        xStream.alias("news", WebViewHeadBean.NewsBean.class);
        xStream.alias("relative", WebViewHeadBean.NewsBean.RelativeBean.class);

        final WebViewHeadBean headBean = (WebViewHeadBean) xStream.fromXML(mgs);
        headWebView.loadUrl(headBean.getNews().getUrl());
        headWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(headBean.getNews().getUrl());
                return true;
            }
        });

    }

    @Override
    public void fail(String mgs) {

    }


    @OnClick(R.id.web_back)
    public void onViewClicked() {
        finish();
    }
}
