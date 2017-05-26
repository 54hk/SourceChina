package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.adapter.FindUserAdapter;
import practice.code.com.sourcechina.adapter.FindWhatTbAdapter;
import practice.code.com.sourcechina.entity.FindUserBean;
import practice.code.com.sourcechina.entity.FindWhatBean;
import practice.code.com.sourcechina.model.HttpUtil.FindWhatUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class FindUserFragment extends BaseFragemnt implements ICallBack {


    @Bind(R.id.find_what_pull)
    PullToRefreshRecyclerView findWhatPull;
    private String content;
    List<FindUserBean.UserBean> resList;
    private FindUserAdapter adapter;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.find_what_tab_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        resList = new ArrayList<>();
        adapter = new FindUserAdapter(APP.activity,resList);
        findWhatPull.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        findWhatPull.setLayoutManager(linearLayoutManager);


    }

    @Override
    protected void getLoad(boolean b) {
        FindWhatUtil.getInstance().getFindUser(content,this);

        Log.d("FindUserFragment", getContent());
    }

    @Override
    protected void sendData() {

    }

    @Override
    public void success(String mgs) {
        Log.d("FindUserFragment", mgs);
       XStream xStream = new XStream();
        xStream.alias("oschina", FindUserBean.class);
        xStream.alias("user", FindUserBean.UserBean.class);
        FindUserBean findUserBean = (FindUserBean) xStream.fromXML(mgs);
        List<FindUserBean.UserBean> users = findUserBean.getUsers();
        resList.addAll(users);
        adapter.notifyDataSetChanged();
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