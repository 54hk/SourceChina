package practice.code.com.sourcechina.fragment.Shake;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thoughtworks.xstream.XStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.Activity.FragmentUtils;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.entity.ShakeBean;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.fragment.WebViewShakeFragment;
import practice.code.com.sourcechina.model.HttpUtil.VolleyUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;
import practice.code.com.sourcechina.util.DataUtils;

public class ShakeFragment extends BaseFragemnt {

    @Bind(R.id.shake_img)
    ImageView shakeImg;
    @Bind(R.id.shake_title)
    TextView shakeTitle;
    @Bind(R.id.shake_xian)
    RelativeLayout shakeXian;
    @Bind(R.id.activity_shake_fragment)
    RelativeLayout activityShakeFragment;
    @Bind(R.id.shake_time)
    TextView shakeTime;
    @Bind(R.id.imasss)
    ImageView imasss;
    @Bind(R.id.find_but_gift)
    RadioButton findButGift;
    @Bind(R.id.find_but_info)
    RadioButton findButInfo;
    @Bind(R.id.shake_txt)
    TextView shakeTxt;
    private SensorManager sensorManager;
    private Vibrator vibrator;

    private static final String TAG = "TestSensorActivity";
    private static final int SENSOR_SHAKE = 10;
    public static final int GIFT_SHAKE = 5;
    private SoundPool pool;
    private int audio;


    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.activity_shake_fragment;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        sensorManager = (SensorManager) getContext().getSystemService(getContext().SENSOR_SERVICE);
        vibrator = (Vibrator) getContext().getSystemService(getContext().VIBRATOR_SERVICE);

        pool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
        audio = pool.load(APP.activity, R.raw.tanke, 1);
        shakeTxt.setText("摇一摇抢礼品");
        findButGift.setChecked(true);

    }

    @Override
    protected void getLoad(boolean b) {

        shakeXian.setVisibility(View.GONE);
    }

    @Override
    protected void sendData() {

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
    protected void show() {
        super.show();
        ActivityMain activity = (ActivityMain) APP.activity;
        RadioGroup mainRg = activity.getMainRg();
        RelativeLayout mainBar = activity.getMainBar();
        mainRg.setVisibility(View.VISIBLE);
        mainBar.setVisibility(View.VISIBLE);

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
    public void onResume() {
        super.onResume();
        if (sensorManager != null) {// 注册监听器
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            // 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (sensorManager != null) {// 取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    /**
     * 重力感应监听
     */
    private SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            Log.i(TAG, "x轴方向的重力加速度" + x + "；y轴方向的重力加速度" + y + "；z轴方向的重力加速度" + z);
            // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
            int medumValue = 19;// 三星 i9250怎么晃都不会超过20，没办法，只设置19了
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
                vibrator.vibrate(200);
                Message msg = new Message();
                if(shakeTxt.getText().equals("摇一摇获取资讯")){

                    msg.what = SENSOR_SHAKE;
                    handler.sendMessage(msg);
                }else{

                    msg.what = GIFT_SHAKE;
                    handler.sendMessage(msg);

                }

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    /**
     * 动作执行
     */
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SENSOR_SHAKE:
                    VolleyUtil.getInstance().doGET(Urls.YAOYIYO, null, new ICallBack() {
                        @Override
                        public void success(String mgs) {
                            Log.i(TAG, "检测到摇晃，执行操作！");
                            shakeXian.setVisibility(View.VISIBLE);

                            XStream xStream = new XStream();
                            xStream.alias("oschina", ShakeBean.class);


                            final ShakeBean shakeBean = (ShakeBean) xStream.fromXML(mgs);

                            Glide.with(getContext()).load(shakeBean.getImage()).into(shakeImg);
                            shakeTitle.setText(shakeBean.getDetail());
                            shakeTime.setText(DataUtils.getDate(shakeBean.getPubDate()));

                            shakeXian.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("url", shakeBean.getUrl());
                                    FragmentUtils.getFragment().init(APP.activity).getStart(WebViewShakeFragment.class).setParme(bundle).build();
                                }
                            });

                        }

                        @Override
                        public void fail(String mgs) {

                        }
                    });

                    break;

                case GIFT_SHAKE:
                    shakeXian.setVisibility(View.GONE);
                    pool.play(audio, 1, 1, 0, 0, 1);
                    break;
            }
        }

    };


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

    @OnClick({R.id.find_but_gift, R.id.find_but_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.find_but_gift:
                shakeTxt.setText("摇一摇抢礼品");
                break;
            case R.id.find_but_info:
                shakeTxt.setText("摇一摇获取资讯");
                break;
        }
    }
}
