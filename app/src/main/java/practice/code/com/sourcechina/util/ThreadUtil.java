package practice.code.com.sourcechina.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class ThreadUtil  {

    Handler handler = new Handler(Looper.getMainLooper());

    public void onRunUIThread(Runnable runnable){
        handler.post(runnable);
    }

    public void onRunStuThread(Runnable runnable){
        new Thread(runnable).start();
    }

}
