package com.example.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyBroadCastRecevier broadCastRecevier;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadCastRecevier);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn);

        broadCastRecevier = new MyBroadCastRecevier();
        IntentFilter itFilter = new IntentFilter();
        itFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadCastRecevier, itFilter);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("提示","被点了");
//                broadCastRecevier = new MyBroadCastRecevier();
//                IntentFilter intentFilter = new IntentFilter("com.example.myapplication.MyBroadCastRecevier");
//                registerReceiver(broadCastRecevier,intentFilter);
//            }
//        });


    }

}