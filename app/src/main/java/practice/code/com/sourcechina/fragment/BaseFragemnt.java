package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public abstract class BaseFragemnt extends Fragment{

    boolean abolean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(initView(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initLoad(view);
        getOnclick();
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoad(abolean);
        sendData();

    }
//   初始化布局
    protected abstract int initView();
//    设置监听
    protected abstract void getOnclick();
//  初始化 加载（布局
    protected abstract void initLoad(View inflate1);
//    进行网络请求
    protected abstract void getLoad(boolean b);
//    进行数据的传送
    protected abstract void sendData();

}
