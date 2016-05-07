package me.khrystal.windowalertdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String INTENT_ACTION = "intent_action";

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (INTENT_ACTION.equals(intent.getAction())) {
                Log.e("MainActivity","receive");
                WindowUtils.showPopupWindow(MainActivity.this);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,new IntentFilter(INTENT_ACTION));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    public void showAlert(View view) {
        Intent intent = new Intent(MainActivity.this,SimpleService.class);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        intent.setAction(INTENT_ACTION);
        startService(intent);
        Log.e("MainActivity","click button");
        onBackPressed();
    }


    public void stopService(View view) {
        Intent intent = new Intent(MainActivity.this,SimpleService.class);
        intent.setAction(INTENT_ACTION);
        stopService(intent);
    }
}
