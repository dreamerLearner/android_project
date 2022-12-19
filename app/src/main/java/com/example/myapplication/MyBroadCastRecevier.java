package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCastRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("提示:",intent.getAction());
        if("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()))
            Toast.makeText(context, "点击成功", Toast.LENGTH_SHORT).show();
    }
}
