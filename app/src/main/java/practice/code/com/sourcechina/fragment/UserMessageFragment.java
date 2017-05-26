package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class UserMessageFragment extends BaseFragemnt implements ICallBack {

    @Bind(R.id.me_click_setting)
    ImageView meClickSetting;
    @Bind(R.id.me_click_QRCode)
    ImageView meClickQRCode;
    @Bind(R.id.me_click_headimage)
    ImageView meClickHeadimage;
    @Bind(R.id.me_click_sex)
    ImageView meClickSex;
    @Bind(R.id.me_click_nickname)
    TextView meClickNickname;
    @Bind(R.id.me_click_integration)
    TextView meClickIntegration;
    @Bind(R.id.me_click_text_mytweet)
    TextView meClickTextMytweet;
    @Bind(R.id.me_click_mytweet)
    RelativeLayout meClickMytweet;
    @Bind(R.id.me_click_text_mycollection)
    TextView meClickTextMycollection;
    @Bind(R.id.me_click_mycollection)
    RelativeLayout meClickMycollection;
    @Bind(R.id.me_click_text_attention)
    TextView meClickTextAttention;
    @Bind(R.id.me_click_attention)
    RelativeLayout meClickAttention;
    @Bind(R.id.my_click_text_myfans)
    TextView myClickTextMyfans;
    @Bind(R.id.my_click_myfans)
    RelativeLayout myClickMyfans;
    @Bind(R.id.me_relative)
    RelativeLayout meRelative;
    private String uid;
    private String nane;
    private String body;
    private String data;
    private String img;
    private String commentCount;
    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.user_message_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {

        nane = getParmes().getString("nane");
        meClickNickname.setText(nane);
        img = getParmes().getString("img");
        Glide.with(APP.context).load(img).into(meClickHeadimage);
        commentCount = getParmes().getString("commentCount");
        meClickTextMytweet.setText(commentCount);
    }

    @Override
    protected void getLoad(boolean b) {
//        NewsTweetUtil.getInstance().getUserMessage(uid,this);


    }

    @Override
    protected void sendData() {

    }

    @Override
    public void success(String mgs) {
//        Log.d("UserMessageFragment", mgs);
//        Toast.makeText(APP.activity, mgs + "", Toast.LENGTH_SHORT).show();
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
