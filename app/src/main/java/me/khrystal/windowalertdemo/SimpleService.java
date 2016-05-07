package me.khrystal.windowalertdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/5/7
 * update time:
 * email: 723526676@qq.com
 */
public class SimpleService extends Service{

    public class LocalBinder extends Binder{
        String stringToSend = "test String";
        SimpleService getService(){
            Log.e("SimpleService","getService");
            return SimpleService.this;
        }
    }

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 2000:
                    WindowUtils.showPopupWindow(getApplicationContext());
                    break;
                default:break;
            }
        }
    };

    private final IBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("SimpleService","onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("SimpleService","onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("SimpleService","onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("SimpleService","onStartCommand");
        if (intent.getAction().equals(MainActivity.INTENT_ACTION)&&mHandler!=null) {
            mHandler.sendEmptyMessageDelayed(2000,3000);
        }
        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("SimpleService","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"stop Service",Toast.LENGTH_SHORT).show();
    }
}
