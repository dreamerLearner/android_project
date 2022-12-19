package com.txzing.gesturestest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Bitmap background = (Bitmap) extras.get("background");
        Log.e("ground",background.toString());
        ImageView image = findViewById(R.id.image);
        image.setImageBitmap(background);
        new AlertDialog.Builder(this).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MyActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(MyActivity.this,MainActivity.class));
                Toast.makeText(MyActivity.this, "提交失败", Toast.LENGTH_SHORT).show();

            }
        }).show();
    }
}
