package practice.code.com.sourcechina.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.HeadAdapter;
import practice.code.com.sourcechina.adapter.RecommendBlogsAdapter;
import practice.code.com.sourcechina.adapter.TestNormalAdapter;
import practice.code.com.sourcechina.entity.HeadXMLBean;
import practice.code.com.sourcechina.entity.RecommendBlogXMLBean;
import practice.code.com.sourcechina.model.HttpUtil.BlogUtil;
import practice.code.com.sourcechina.model.HttpUtil.NewsUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

public class RecommendBlogsFragment extends Fragment implements ICallBack{


    PullToRefreshRecyclerView headRefreshRv;

    RecommendBlogsAdapter rbAdapter;
   ArrayList<RecommendBlogXMLBean.BlogBean> bolgAlls = new ArrayList<RecommendBlogXMLBean.BlogBean>();

    int flag = 1;
    ProgressDialog progressDialog;
    private List<RecommendBlogXMLBean.BlogBean> blogslist;
    private boolean iiboolean;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_recommend_blogs, null);
        headRefreshRv = (PullToRefreshRecyclerView) inflate.findViewById(R.id.rb_refresh_recyclerview);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        initHeadAdapter();
        getHeadVolley(false);

        return inflate;
    }




    public void initHeadAdapter() {
        bolgAlls = new ArrayList<>();
        rbAdapter = new RecommendBlogsAdapter(getActivity(),bolgAlls );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        headRefreshRv.setLayoutManager(linearLayoutManager);
        headRefreshRv.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                bolgAlls.clear();
                getHeadVolley(false);
            }

            @Override
            public void onLoadMore() {
                flag++;
                getHeadVolley(true);
            }
        });
        headRefreshRv.setAdapter(rbAdapter);

    }

    public void getHeadVolley(final boolean isboolean) {
        BlogUtil.getInstance().getNewsList(flag,this);
        iiboolean = isboolean;

    }

    @Override
    public void success(String mgs) {
        Log.e("recomment",mgs);
        progressDialog.dismiss();
        XStream xs = new XStream();
        xs.alias("oschina", RecommendBlogXMLBean.class);
        xs.alias("blog", RecommendBlogXMLBean.BlogBean.class);
        RecommendBlogXMLBean recommendBlogXMLBean = (RecommendBlogXMLBean) xs.fromXML(mgs);
        blogslist = recommendBlogXMLBean.getBlogs();
        bolgAlls.addAll(blogslist);
        rbAdapter.notifyDataSetChanged();
        if (iiboolean) {
            headRefreshRv.setLoadMoreComplete();
        } else {
            headRefreshRv.setRefreshComplete();
        }
    }

    @Override
    public void fail(String mgs) {
        Log.e("recomment",mgs);
    }
}
