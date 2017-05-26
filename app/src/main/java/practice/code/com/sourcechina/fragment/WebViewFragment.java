package practice.code.com.sourcechina.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.thoughtworks.xstream.XStream;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.Activity.BaseActivity;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.Activity.SimpleFragmentBuilder;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.entity.WebViewHeadBean;
import practice.code.com.sourcechina.model.HttpUtil.HeadNewDetailsUtil;
import practice.code.com.sourcechina.model.HttpUtil.ReviewUtils;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.db.UserBean;
import practice.code.com.sourcechina.model.db.UserDBOpenHelper;

public class WebViewFragment extends BaseFragemnt implements ICallBack {


    @Bind(R.id.head_web_view)
    WebView headWebView;
    @Bind(R.id.web_back)
    ImageView webBack;
    @Bind(R.id.wb_ed)
    EditText wbEd;
    private String id;
    private String uid;


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
        Log.e("ijhjhjd", "-----------------------" + id);

    }

    @Override
    protected void getLoad(boolean b) {




        HeadNewDetailsUtil.getInstance().getLoadDetails(id, this);


    }


    @Override
    protected void sendData() {


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
    protected void onHidden() {
        super.onHidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainBar.setVisibility(View.GONE);
        mainRg.setVisibility(View.GONE);

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
    protected void beginProgressDialog() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SimpleFragmentBuilder.getInstance().setLastFragment(this);
    }

    @OnClick(R.id.wb_ed)
    public void onViewClicked() {
        getPopwindow();
    }

    private void getPopwindow() {
        View contview = LayoutInflater.from(APP.activity).inflate(R.layout.popwindowitem, null);
        final EditText editText = (EditText) contview.findViewById(R.id.edit);
        Button button = (Button) contview.findViewById(R.id.sendbutton);

        PopupWindow popupWindow = new PopupWindow(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contview);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(contview, Gravity.CENTER, 0, 0);

        SharedPreferences loginClass = APP.activity.getSharedPreferences("loginClass", Context.MODE_PRIVATE);
        boolean loginboolean = loginClass.getBoolean("loginboolean", false);

        if (loginboolean){

            try {
                UserDBOpenHelper helper = new UserDBOpenHelper(APP.activity);
                Dao<UserBean, Integer> dao = helper.getUserDao();
                List<UserBean> userBeen = dao.queryForAll();
                uid = userBeen.get(userBeen.size() - 1).getUid();
                Log.d("WebViewFragmentuid", uid);


            } catch (SQLException e) {
                e.printStackTrace();
            }



            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    final String edStr = editText.getText().toString();
                    bundle.putString("id", id);
                    bundle.putString("catalog", "1");
                    bundle.putString("content", edStr);
//                FragmentUtils.getFragment().init(APP.activity).getStart(ReviewFragment.class).setParme(bundle).build();

                    ReviewUtils.getInstance().getReviewMethod("1", id, uid, edStr, new ICallBack() {
                        @Override
                        public void success(String mgs) {
                            Log.d("WebViewFragments", mgs);
                            Toast.makeText(APP.context, "" + mgs, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void fail(String mgs) {
                            Log.d("WebViewFragments", mgs);
                        }
                    });

                }
            });
        }else{
            BaseActivity.getLoginMethod();
        }





    }

    @OnClick(R.id.web_back)
    public void onbackClicked() {
        BaseActivity.getBackManager();
    }
}
