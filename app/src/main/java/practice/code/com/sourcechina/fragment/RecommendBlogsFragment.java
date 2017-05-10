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

public class RecommendBlogsFragment extends Fragment {


    PullToRefreshRecyclerView headRefreshRv;

    RecommendBlogsAdapter rbAdapter;
   ArrayList<RecommendBlogXMLBean.BlogBean> bolgAlls = new ArrayList<RecommendBlogXMLBean.BlogBean>();
    private List<RecommendBlogXMLBean.BlogBean> blogs;
    int flag = 1;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_recommend_blogs, null);
        headRefreshRv = (PullToRefreshRecyclerView) inflate.findViewById(R.id.rb_refresh_recyclerview);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();

        return inflate;
    }


    @Override
    public void onResume() {
        super.onResume();
        getHeadVolley(false);
        initHeadAdapter();
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
        String url = "http://www.oschina.net/action/api/blog_list?type=latest&pageIndex="+ flag +"&pageSize=20";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest( url, new Response.Listener<String>() {


            private RecommendBlogXMLBean recommendBlogXMLBean;

            @Override
            public void onResponse(String response) {

                if(flag == 1){
                    progressDialog.dismiss();
                }

                XStream xs = new XStream();
                xs.alias("oschina", RecommendBlogXMLBean.class);
                xs.alias("blog", RecommendBlogXMLBean.BlogBean.class);
                recommendBlogXMLBean = (RecommendBlogXMLBean) xs.fromXML(response);
                //TODO  有助于 自定义 action/api/blog_list 参数
                blogs = recommendBlogXMLBean.getBlogs();
                bolgAlls.addAll(blogs);
                rbAdapter.notifyDataSetChanged();

                if (isboolean) {
                    headRefreshRv.setLoadMoreComplete();
                } else {
                    headRefreshRv.setRefreshComplete();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("call", error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

}
