package practice.code.com.sourcechina.fragment.PortraitUpdate;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.cantance.Urls;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.model.HttpUtil.HuanHeadUtil;
import practice.code.com.sourcechina.model.HttpUtil.VolleyUtil;
import practice.code.com.sourcechina.model.bis.ICallBack;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class PotrUpdateFragment extends BaseFragemnt implements ICallBack {

    Button buttonl;
    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void beginProgressDialog() {

    }

    @Override
    protected int initView() {
        return R.layout.poupdate_fragmen_layout;
    }

    @Override
    protected void getOnclick() {

    }

    @Override
    protected void initLoad(View inflate1) {
        buttonl = (Button) inflate1.findViewById(R.id.huan_head);
        buttonl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void getLoad(boolean b) {

    }

    @Override
    protected void sendData() {

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


            VolleyUtil.getInstance().doPOSTImg( Urls.HUANHEAD,file ,"3471776",this );
        }

    }


    @Override
    public void success(String mgs) {

        Log.d("PotrUpdateFragment", mgs);
        Toast.makeText(APP.context, ""+mgs, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void fail(String mgs) {
        Log.d("PotrUpdateFragment", mgs);
        Toast.makeText(APP.context, ""+mgs, Toast.LENGTH_SHORT).show();
    }
}
