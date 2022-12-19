package com.txzing.gesturestest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private MyGestureListener myGestureListener;
    private GestureLibrary gestureLibrary;
    private Context mContext;
    private GestureDetector mDetector;
    private final String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE"};
    private int REQUEST_EXTERNAL_STORAGE = 1;

    public void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有全部权限
            HashSet<Object> rs = new HashSet<>();
            for(int i = 0;i<PERMISSIONS_STORAGE.length;i++){
                int permission = ActivityCompat.checkSelfPermission(activity, PERMISSIONS_STORAGE[i]);
                rs.add(permission);
            }

            if (rs.contains(PackageManager.PERMISSION_DENIED)) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
                System.out.println("here1 ");
            }else {
                System.out.println("here2 ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42 && resultCode == Activity.RESULT_OK) {
            Uri uri;
            if (data != null) {
                uri = data.getData();
                Log.e("HeHe", "Uri: " + uri.toString());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent,42);
//                try {
//                    getBitmapFromUri(Uri.parse("file://image/*"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        });

    }
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
//        verifyStoragePermissions(this);
//
//        //获取手势编辑组件后，设置相关参数
//        GestureOverlayView gesture = (GestureOverlayView) findViewById(R.id.gesture);
//        gesture.setGestureColor(Color.GREEN);
//        gesture.setGestureStrokeWidth(5);
//
//        gestureLibrary = GestureLibraries.fromFile("mmt/sdcard/mygestures");
//        if(gestureLibrary.load()){
//            Toast.makeText(MainActivity.this, "手势库加载成功", Toast.LENGTH_SHORT).show();
//        }else
//            Toast.makeText(MainActivity.this, "手势库加载失败", Toast.LENGTH_SHORT).show();
//
//        gesture.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
//
//            @Override
//            public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
//
//
//                ArrayList<Prediction> recognize = gestureLibrary.recognize(gesture);
//                ArrayList<String> result = new ArrayList<>();
//                for(Prediction p:recognize){
//                    if(p.score>2.0){
//                        result.add("与"+p.name+"的匹配相似度为"+p.score);
//                    }
//                    if(result.size()>0){
//                        ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(
//                                mContext,android.R.layout.simple_dropdown_item_1line,result.toArray()
//                        );
//                        new AlertDialog.Builder(mContext).setAdapter(arrayAdapter,null).
//                                setPositiveButton("确定",null).show();
//                    }else {
//                        Toast.makeText(MainActivity.this, "无法匹配手势", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                Bitmap bitmap = gesture.toBitmap(352, 200, 10, 0xffff0000);


//                Intent intent = new Intent(MainActivity.this, MyActivity.class);
//                intent.putExtra("background", bitmap);
//                startActivity(intent);
            }
//        });


//        myGestureListener = new MyGestureListener();
//        mDetector = new GestureDetector(this,myGestureListener);
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        mDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }
//}