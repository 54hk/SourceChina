package practice.code.com.sourcechina.fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.Activity.BaseActivity;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.entity.LoginEntity;
import practice.code.com.sourcechina.model.db.UserBean;
import practice.code.com.sourcechina.model.db.UserDBOpenHelper;

public class LoginFragment extends BaseFragemnt {


    ImageView loginTitle;
    EditText phoneOrEmial;
    EditText passwordd;
    TextView loginForgetMima;

    Button loginIn;
    Button receIn;
    @Bind(R.id.activity_login)
    LinearLayout activityLogin;
    @Bind(R.id.web_back)
    ImageView webBack;

    private String phone;
    private String password;
    boolean aBoolean;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            if (intent.getStringExtra("name").equals("mine")) {
                get2Login();
            }

        }
    };

    @Override
    protected int initView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initLoad(View inflate1) {
        loginTitle = (ImageView) inflate1.findViewById(R.id.login_title);
        phoneOrEmial = (EditText) inflate1.findViewById(R.id.phone_or_emial);
        passwordd = (EditText) inflate1.findViewById(R.id.password);
        loginForgetMima = (TextView) inflate1.findViewById(R.id.login_forget_mima);
        loginIn = (Button) inflate1.findViewById(R.id.login_in);
        receIn = (Button) inflate1.findViewById(R.id.rece_in);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("mine.login");
        APP.activity.registerReceiver(broadcastReceiver, intentFilter);



    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        APP.activity.unregisterReceiver(broadcastReceiver);

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
    protected void onHidden() {
        super.onHidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainBar.setVisibility(View.GONE);
        mainRg.setVisibility(View.GONE);

    }

    @Override
    protected void hidden() {
        super.hidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainBar.setVisibility(View.GONE);
        mainRg.setVisibility(View.GONE);

    }

    @Override
    protected void beginProgressDialog() {

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

    @OnClick({R.id.login_in, R.id.rece_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_in:
                get2Login();
                break;
            case R.id.rece_in:
                Toast.makeText(APP.activity, "............", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    public void get2Login(){
        phone = phoneOrEmial.getText().toString().trim();
        password = passwordd.getText().toString().trim();
//                LoginUtil.getInstance().goLogin(phone,password,this);
        Map<String, String> map = new HashMap<>();
        map.put("username", phone);
        map.put("pwd", password);
        map.put("keep_login", Urls.KEEP_LOGIN + "");
        doPOST(Urls.LOGINURL, map);
    }

    public void doPOST(String url, Map<String, String> map) {

        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        Set<String> strings = map.keySet();
        for (String key : strings) {
            String value = map.get(key);
            builder.add(key, value);
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.d("Login", e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                setCookie(response);
                String mgs = response.body().string();
                Log.i("TAG", mgs + "========================================================");


                Log.e("login", mgs);
                XStream xs = new XStream();
                xs.alias("oschina", LoginEntity.class);
                xs.alias("user", LoginEntity.UserBean.class);
                xs.alias("result", LoginEntity.ResultBean.class);
                xs.alias("notice", LoginEntity.NoticeBean.class);
                final LoginEntity loginEntity = (LoginEntity) xs.fromXML(mgs);
                Log.d("LoginFragment", mgs);

                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(APP.activity, loginEntity.getResult().getErrorMessage(), Toast.LENGTH_SHORT).show();
                        LoginEntity.UserBean user = loginEntity.getUser();


                        if (!user.getUid().isEmpty()) {
                            int i = 0;
                            try {
                                UserDBOpenHelper helper = new UserDBOpenHelper(APP.activity);
                                Dao<UserBean, Integer> dao = helper.getUserDao();
                                UserBean userBean = new UserBean();
//        int id, String uid, String location, String name, String followers,
//                String fans, String score, String portrait, String favoritecount, String gender) {
                                userBean.setFans(user.getFans());
                                userBean.setFavoritecount(user.getFavoritecount());
                                userBean.setFollowers(user.getFollowers());
                                userBean.setGender(user.getGender());
                                userBean.setLocation(user.getLocation());
                                userBean.setName(user.getName());
                                userBean.setPortrait(user.getPortrait());
                                userBean.setScore(user.getScore());
                                userBean.setUid(user.getUid());
                                Log.d("LoginFragment", user.getUid());


                                i = dao.create(userBean);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            Log.d("LoginFragment", "增加" + i + "条数据");
                        }


                        Intent intent = new Intent();
                        intent.setAction("login.wancheng");

                        intent.putExtra("name","login");
                        APP.activity.sendBroadcast(intent);


                        if (loginEntity.getResult().getErrorCode().equals("1")) {

                            SharedPreferences loginClass = APP.activity.getSharedPreferences("loginClass", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = loginClass.edit();
                            SharedPreferences.Editor editor = edit.putBoolean("loginboolean", true);
                            editor.commit();
                            final ProgressDialog progressDialog = ProgressDialog.show(APP.context,"温馨提示","正在登陆...");
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    FragmentUtils.getFragment().init(APP.activity).getStart(MineFragment.class).build();
                                }
                            },5000);
                        }
                    }
                });
            }


        });

    }
Handler handler = new Handler();

    public void setCookie(Response response) {
        Log.d("LoginFragment", "response:" + response);
        Headers headers = response.headers();
        String cookie = "";
        Set<String> names = headers.names();
        for (String key : names) {
            String value = headers.get(key);


            Log.d("LoginFragmentHHHHHHH", value);
            if (key.contains("Set-Cookie")) {
                cookie += value + ";";
            }
        }
        if (cookie.length() > 0) {
            cookie = cookie.substring(0, cookie.length() - 1);
        }
        Log.e("cookie", cookie);
        SharedPreferences data = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = data.edit();
        edit.putString("cookie", cookie);
        edit.commit();
    }


    @OnClick(R.id.web_back)
    public void onViewClicked() {
        BaseActivity.getBackManager();
    }
}
