package practice.code.com.sourcechina.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public abstract class BaseFragemnt extends Fragment{

    boolean abolean = false;
    public Bundle bundle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(initView(),container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beginProgressDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getSharedPreferences();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initLoad(view);
        getOnclick();
        sendData();
        getLoad(abolean);


    }

    @Override
    public void onResume() {
        super.onResume();
        refish();
        hidden();
        
        Log.d("BaseFragssssemnt", "你hi啊的");

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void getSharedPreferences(){

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            //如果隐藏 就让他 显示
            show();
        }else{
            onHidden();
            //如果显示就让他隐藏
        }
    }

    protected void onHidden(){

    }

    protected void show(){

    };

protected void hidden(){
    //在  onResume 中调用  原因是当进入页面之后首先将他隐藏

}


    protected abstract void  beginProgressDialog();


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

    protected void refish(){

    };

//    protected abstract void acceptBundle(Bundle bundle);
    public void Parmes(Bundle bundle){
       this.bundle=bundle;
    }

    public Bundle getParmes(){
        return bundle;
    }



}
