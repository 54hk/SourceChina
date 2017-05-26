package practice.code.com.sourcechina.fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.j256.ormlite.dao.Dao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.Activity.BaseActivity;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.fragment.messagecenter.MessageCenterFragment;
import practice.code.com.sourcechina.model.HttpUtil.VolleyUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.model.db.UserBean;
import practice.code.com.sourcechina.model.db.UserDBOpenHelper;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class MineFragment extends BaseFragemnt {

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
    @Bind(R.id.me_img_mymessage)
    ImageView meImgMymessage;
    @Bind(R.id.me_mymessage)
    TextView meMymessage;
    @Bind(R.id.me_click_mymessage)
    RelativeLayout meClickMymessage;
    @Bind(R.id.me_img_myblog)
    ImageView meImgMyblog;
    @Bind(R.id.me_myblog)
    TextView meMyblog;
    @Bind(R.id.me_click_myblog)
    RelativeLayout meClickMyblog;
    @Bind(R.id.me_img_myanswer)
    ImageView meImgMyanswer;
    @Bind(R.id.me_myanswer)
    TextView meMyanswer;
    @Bind(R.id.me_click_myanswer)
    RelativeLayout meClickMyanswer;
    @Bind(R.id.me_img_myactivity)
    ImageView meImgMyactivity;
    @Bind(R.id.me_myactivity)
    TextView meMyactivity;
    @Bind(R.id.me_click_myactivity)
    RelativeLayout meClickMyactivity;
    @Bind(R.id.me_img_mygroup)
    ImageView meImgMygroup;
    @Bind(R.id.me_click_mygroup)
    RelativeLayout meClickMygroup;
    @Bind(R.id.mine_linear)
    LinearLayout mineLinear;

    @Bind(R.id.zong_min)
    LinearLayout zongMin;
    private List<UserBean> userBeen;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            if (intent.getStringExtra("name").equals("kong")) {
                getkongMessage();
                zongMin.setVisibility(View.INVISIBLE);
            } else if (intent.getStringExtra("name").equals("login")) {
                getUserMessgae();
                zongMin.setVisibility(View.VISIBLE);
            }


        }
    };
    private ProgressDialog progressdialog;

    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.fragment_me;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void onHidden() {
        super.onHidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        mainBar.setVisibility(View.GONE);

    }

    @Override
    protected void hidden() {
        super.hidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        mainBar.setVisibility(View.GONE);

    }

    @Override
    protected void show() {
        super.show();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        mainBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initLoad(View inflate1) {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("login.wancheng");
        APP.activity.registerReceiver(broadcastReceiver, intentFilter);

        getUserMessgae();
    }

    public void getkongMessage() {

        meClickHeadimage.setImageResource(R.mipmap.ic_login_username);
        meClickNickname.setText("请点击登录");
        meClickIntegration.setText("积分 " + 0);
        myClickTextMyfans.setText("0");
        meClickTextAttention.setText("0");
        meClickTextMycollection.setText("0");
        meClickTextMytweet.setText("0");

    }

    public void getUserMessgae() {
        try {
            UserDBOpenHelper helper = new UserDBOpenHelper(APP.activity);
            Dao<UserBean, Integer> dao = helper.getUserDao();
            userBeen = dao.queryForAll();

            for (int i = 0; i < userBeen.size(); i++) {
                Log.e("MineUser", userBeen.get(i).toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userBeen.size() > 0) {
            Log.d("MineFragment", "n你怎么没有进s");
//                meClickHeadimage
            Log.d("MineFragment", userBeen.get(userBeen.size() - 1).getUid());
            Glide.with(APP.context).load(userBeen.get(userBeen.size() - 1).getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(meClickHeadimage) {
                @Override
                protected void setResource(Bitmap resource) {
                    super.setResource(resource);
                    RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(APP.activity.getResources(), resource);
                    roundedDrawable.setCircular(true);
                    meClickHeadimage.setImageDrawable(roundedDrawable);
                }
            });

//            Glide.with(APP.activity).load(userBeen.get(userBeen.size() - 1).getPortrait()).into(meClickHeadimage);
            meClickNickname.setText(userBeen.get(userBeen.size() - 1).getName());
            meClickIntegration.setText("积分 " + userBeen.get(userBeen.size() - 1).getScore());
            myClickTextMyfans.setText((userBeen.get(userBeen.size() - 1)).getFans());
            meClickTextAttention.setText((userBeen.get(userBeen.size() - 1)).getFollowers());
            meClickTextMycollection.setText((userBeen.get(userBeen.size() - 1)).getFavoritecount());


            Log.d("MineFragments", (userBeen.get(userBeen.size() - 1)).getGender());
            Log.d("MineFragments", (userBeen.get(userBeen.size() - 1)).getFollowers());
            Log.d("MineFragments", (userBeen.get(userBeen.size() - 1)).getLocation());
            Log.d("MineFragments", (userBeen.get(userBeen.size() - 1)).getScore());

        }
        if (meClickNickname.getText().equals("点击头像登录")) {

            meClickIntegration.setVisibility(View.GONE);
        }
        Log.d("MineFragment", "我就不进怎么没有进s");
        SharedPreferences preferences = APP.activity.getSharedPreferences("tweetsize", Context.MODE_PRIVATE);
        String tweets = preferences.getString("tweets", "");
        Log.d("MineFragmentsjjj", tweets);
        meClickTextMytweet.setText(tweets);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        APP.activity.unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void getSharedPreferences() {
        super.getSharedPreferences();


    }

    @Override
    protected void getLoad(boolean b) {


    }


    @Override
    protected void sendData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }




    private static int RESULT_LOAD_IMAGE = 1;
    @OnClick({R.id.me_click_setting, R.id.me_click_QRCode, R.id.me_click_headimage, R.id.me_click_sex, R.id.me_click_nickname, R.id.me_click_integration, R.id.me_click_text_mytweet, R.id.me_click_mytweet, R.id.me_click_text_mycollection, R.id.me_click_mycollection, R.id.me_click_text_attention, R.id.me_click_attention, R.id.my_click_text_myfans, R.id.my_click_myfans, R.id.me_relative, R.id.me_img_mymessage, R.id.me_mymessage, R.id.me_click_mymessage, R.id.me_img_myblog, R.id.me_myblog, R.id.me_click_myblog, R.id.me_img_myanswer, R.id.me_myanswer, R.id.me_click_myanswer, R.id.me_img_myactivity, R.id.me_myactivity, R.id.me_click_myactivity, R.id.me_img_mygroup, R.id.me_click_mygroup, R.id.mine_linear})
    public void onViewClicked(View view) {
        SharedPreferences loginClass = APP.activity.getSharedPreferences("loginClass", Context.MODE_PRIVATE);
        boolean loginboolean = loginClass.getBoolean("loginboolean", false);

        if (loginboolean == true) {

            switch (view.getId()) {
                case R.id.me_click_setting:
                    FragmentUtils.getFragment().init(APP.activity).getStart(SettingFragment.class).build();
                    break;
                case R.id.me_click_QRCode:
                    View inflate = LayoutInflater.from(APP.activity).inflate(R.layout.code_erwim, null);
                    ImageView viewById = (ImageView) inflate.findViewById(R.id.item_code);
                    viewById.setImageResource(R.mipmap.qrcode_oschina);
                    PopupWindow popupWindow = new PopupWindow(inflate, 400, 400);
                    popupWindow.setFocusable(true);
                    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.update();
                    popupWindow.showAtLocation(mineLinear, Gravity.CENTER, 0, 0);
                    break;
                case R.id.me_click_headimage:
                    Log.d("MineFragment", getClass().getName());
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    progressdialog = ProgressDialog.show(APP.context,"等待...","正在上传图片");

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                    break;
                case R.id.me_click_mytweet:
                    FragmentUtils.getFragment().init(APP.activity).getStart(TweetMineFragment.class).build();
                    break;
                case R.id.me_click_attention:
                    Bundle bundleG = new Bundle();
                    bundleG.putString("uid", userBeen.get(userBeen.size() - 1).getUid());
                    FragmentUtils.getFragment().init(APP.activity).getStart(GuanFragment.class).setParme(bundleG).build();
                    break;
                case R.id.my_click_myfans:
                    Bundle bundleF = new Bundle();
                    bundleF.putString("uid", userBeen.get(userBeen.size() - 1).getUid());
                    FragmentUtils.getFragment().init(APP.activity).getStart(FanFragment.class).setParme(bundleF).build();
                    break;
                case R.id.me_click_mymessage:
                    Bundle bundleM = new Bundle();
                    bundleM.putString("uid", userBeen.get(userBeen.size() - 1).getUid());
                    FragmentUtils.getFragment().init(APP.activity).getStart(MessageCenterFragment.class).setParme(bundleM).build();
                    break;
                case R.id.me_click_myactivity:
                    FragmentUtils.getFragment().init(APP.activity).getStart(ActionFragment.class).build();
                    break;

            }
        } else {

            switch (view.getId()) {
                case R.id.me_click_setting:
                    BaseActivity.getLoginMethod();
                    break;
                case R.id.me_click_QRCode:
                    BaseActivity.getLoginMethod();
                    break;
                case R.id.me_click_headimage:
                    BaseActivity.getLoginMethod();
                    break;
                case R.id.me_click_mytweet:
                    BaseActivity.getLoginMethod();
                    break;
                case R.id.me_click_attention:
                    BaseActivity.getLoginMethod();
                    break;
                case R.id.my_click_text_myfans:
                    BaseActivity.getLoginMethod();
                    break;
                case R.id.my_click_myfans:
                    BaseActivity.getLoginMethod();
                    break;
                case R.id.me_click_mymessage:
//                TODO  我的消息    @a
                    BaseActivity.getLoginMethod();
                    break;
                case R.id.me_click_myactivity:
                    BaseActivity.getLoginMethod();
                    break;

            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == APP.activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor =  APP.activity.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Log.d("PotrUpdateFragment", picturePath);
            cursor.close();

            BitmapFactory.Options options   = new BitmapFactory.Options();
            options.inSampleSize = 4;
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath, options);

            File file = new File(picturePath);

            if(file!=null){

//            HuanHeadUtil.getInstance().huanHead( ,file ,this);
                VolleyUtil.getInstance().doPOSTImg(Urls.HUANHEAD, file, "3471776", new ICallBack() {
                    @Override
                    public void success(String mgs) {
                        Log.d("MineFragmentImg", mgs);

                        progressdialog.dismiss();
                        Toast.makeText(APP.context, ""+mgs, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void fail(String mgs) {
                        Log.d("MineFragmentImg", mgs);

                        progressdialog.dismiss();
                        Toast.makeText(APP.context, ""+mgs, Toast.LENGTH_LONG).show();
                    }
                });

                Intent intent = new Intent();
                intent.setAction("mine.login");
                intent.putExtra("name","mine");
                APP.activity.sendBroadcast(intent);



            }else{
                Toast.makeText(APP.context, "请选择图片", Toast.LENGTH_SHORT).show();
            }


        }

    }


}
