package com.txzing.gesturestest;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyGestureListener implements GestureDetector.OnGestureListener {

    private final String TAG = "手势:";
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.e(TAG,"按下");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.e(TAG,"手指按下一段时间,不过还没到长按");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.e(TAG,"手指离开屏幕的一瞬间");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.e(TAG,"滑动");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.e(TAG,"长按并且没有松开");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG, "onFling:迅速滑动，并松开");
        return false;
    }
}
