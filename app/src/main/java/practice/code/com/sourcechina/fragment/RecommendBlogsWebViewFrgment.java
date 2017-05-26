package practice.code.com.sourcechina.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.thoughtworks.xstream.XStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.Activity.BaseActivity;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.RecommendBlogsWebViewBean;
import practice.code.com.sourcechina.model.HttpUtil.BlogDetilsUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class RecommendBlogsWebViewFrgment extends BaseFragemnt implements ICallBack {


    @Bind(R.id.web_back)
    ImageView webBack;
    @Bind(R.id.head_web_view)
    WebView headWebView;
    @Bind(R.id.wb_ed)
    EditText wbEd;
    private String id;
    ProgressDialog progressDialog;
    private BaseFragemnt lastFragment;

    @Override
    protected void beginProgressDialog() {
        progressDialog = new ProgressDialog(getContext());

        progressDialog.show();
    }

    @Override
    protected int initView() {
        return R.layout.fragment_web_view;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {


        id = getParmes().getString("id");
        Log.d("RecommendBlogsWebViewFr", id);
        BlogDetilsUtil.getInstance().getLoadDetails(id, this);
    }

    @Override
    protected void getLoad(boolean b) {

    }

    @Override
    protected void sendData() {

    }

    @Override
    protected void show() {
        super.show();
        ActivityMain activity = (ActivityMain) APP.activity;
        RadioGroup mainRg = activity.getMainRg();
        RelativeLayout mainBar = activity.getMainBar();
        mainRg.setVisibility(View.VISIBLE);
        mainBar.setVisibility(View.VISIBLE);
//        FragmentBuilder.getInstance().addStack(getClass().getName());

    }

    @Override
    protected void hidden() {
        super.hidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainBar.setVisibility(View.GONE);
        mainRg.setVisibility(View.GONE);


    }

    @Override
    public void success(String mgs) {
        XStream xStream = new XStream();

        progressDialog.dismiss();

        xStream.alias("oschina", RecommendBlogsWebViewBean.class);
        xStream.alias("blog", RecommendBlogsWebViewBean.BlogBean.class);

        final RecommendBlogsWebViewBean blogsWebViewBean = (RecommendBlogsWebViewBean) xStream.fromXML(mgs);
        Log.d("RecommendBlogsWebViewFr", mgs);
        Log.d("RecommendBlogsWebViewFr", blogsWebViewBean.getBlog().getUrl());

        headWebView.loadUrl(blogsWebViewBean.getBlog().getUrl());
        headWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(blogsWebViewBean.getBlog().getUrl());
                return true;
            }
        });
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


    @OnClick(R.id.web_back)
    public void onViewClicked() {
        BaseActivity.getBackManager();
    }
}
