package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import practice.code.com.sourcechina.R;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class MineFragment extends BaseFragemnt {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.mine_fragment_layout, null);

        return inflate;
    }

    @Override
    protected int initView() {
        return 0;
    }

    @Override
    protected void getOnclick() {

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
