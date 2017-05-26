package practice.code.com.sourcechina.fragment.kyrj;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.BaseFragemnt;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class ContainerVP extends BaseFragemnt{
    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.convp;
    }

    @Override
    protected void getOnclick() {
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        SortFragment sortFragment = new SortFragment();
        fragmentTransaction.replace(R.id.open_source_frame,sortFragment);
        fragmentTransaction.commit();



    }

    @Override
    protected void initLoad(View inflate1) {

    }

    @Override
    protected void getLoad(boolean b) {

    }

    @Override
    protected void sendData() {

    }
}
