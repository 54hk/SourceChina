package practice.code.com.sourcechina.fragment.kyrj;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.Activity.ActivityMain;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.BaseFragemnt;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class ScanFragment extends BaseFragemnt implements QRCodeView.Delegate {


    private ZXingView mQRCodeView;


    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.scan_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        mQRCodeView = (ZXingView) inflate1.findViewById(R.id.zxingview);
        mQRCodeView.startSpot();
        mQRCodeView.setResultHandler(this);
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);

    }

    @Override
    protected void getLoad(boolean b) {

    }

    @Override
    protected void sendData() {

    }





    @Override
    public void onScanQRCodeSuccess(String result) {
//        if (result.contains("csdn.net")) {
//            startWebViewForResult(result, true, "", IntentKey.BACK);
//            vibrate();
//        }
        vibrate();

        mQRCodeView.stopSpot();
        if (!TextUtils.isEmpty(result)) {
            mQRCodeView.stopCamera();
            Toast.makeText(APP.activity, result, Toast.LENGTH_SHORT).show();
            Log.d("ScanActivity", result);
        } else {
            Toast.makeText(APP.activity, "链接无效,请重新扫描", Toast.LENGTH_SHORT).show();
            mQRCodeView.startSpot();
        }

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
    private void vibrate() {
        Vibrator vibrator = (Vibrator) APP.activity.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }


    @Override
    protected void hidden() {
        super.hidden();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainRg.setVisibility(View.GONE);
        mainBar.setVisibility(View.GONE);

    }

    @Override
    protected void show() {
        super.show();
        ActivityMain activity = (ActivityMain) APP.activity;
        RelativeLayout mainBar = activity.getMainBar();
        RadioGroup mainRg = activity.getMainRg();
        mainRg.setVisibility(View.VISIBLE);
        mainBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mQRCodeView.stopSpot();
        mQRCodeView.stopCamera();
    }
}
