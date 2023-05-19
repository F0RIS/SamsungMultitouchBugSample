package com.jelly.blob;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TouchView touchView;
    private TouchButton btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touchView = findViewById(R.id.touch_view);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(v -> System.out.println("click " + new Date().getTime()));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int pointerCount = ev.getPointerCount();
        touchView.setGlobalTouchCount(pointerCount);
        return super.dispatchTouchEvent(ev);
    }
}