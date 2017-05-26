package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.Shake.ShakeFragment;
import practice.code.com.sourcechina.fragment.kyrj.ScanFragment;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class FindFragment extends BaseFragemnt {
    @Bind(R.id.open_source_software)
    RelativeLayout openSourceSoftware;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.scan_launch)
    RelativeLayout scanLaunch;
    @Bind(R.id.shake_find_lin)
    RelativeLayout shakeFindLin;
    @Bind(R.id.bian_img)
    ImageView bianImg;
    @Bind(R.id.textView4)
    ImageView textView4;
    @Bind(R.id.open_img)
    ImageView openImg;
    @Bind(R.id.sao_img)
    ImageView saoImg;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.xian_img)
    ImageView xianImg;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.xian_relative)
    RelativeLayout xianRelative;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.find_fragment;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void show() {
        super.show();
        ActivityMain activity = (ActivityMain) APP.activity;
        RadioGroup mainRg = activity.getMainRg();
        RelativeLayout mainBar = activity.getMainBar();
        mainRg.setVisibility(View.VISIBLE);
        mainBar.setVisibility(View.VISIBLE);

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

    @OnClick(R.id.open_source_software)
    public void onViewClicked() {

        FragmentUtils.getFragment().init(APP.activity).getStart(OpenSourceSoftwareFragment.class).build();
    }


    @OnClick(R.id.scan_launch)
    public void onScanClicked() {
        FragmentUtils.getFragment().init(APP.activity).getStart(ScanFragment.class).build();
    }

    @OnClick(R.id.shake_find_lin)
    public void onshakeClicked() {
        FragmentUtils.getFragment().init(APP.activity).getStart(ShakeFragment.class).build();
    }

    @OnClick(R.id.xian_relative)
    public void onxianClicked() {
        FragmentUtils.getFragment().init(APP.activity).getStart(ActionFragment.class).build();
    }
}
