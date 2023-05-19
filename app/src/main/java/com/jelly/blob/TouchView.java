package com.jelly.blob;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;


public class TouchView extends View {

    private float touchScale = 1f;
    private int globalTouchCount = 1;

    private final Paint paint;

    final int TOUCH_RADIUS = 80;
    final int MAX_TOUCHES = 10;

    FloatPoint touches[] = new FloatPoint[MAX_TOUCHES];
    int colors[] = new int[]{
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.CYAN,
            Color.MAGENTA,
            Color.WHITE,
            Color.GRAY,
            Color.LTGRAY,
            Color.DKGRAY,
    };

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
        this.paint.setColor(Color.RED);

        for (int i = 0; i < touches.length; i++) {
            touches[i] = new FloatPoint();
        }

        detectTouchScale();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < touches.length; i++) {
            FloatPoint point = touches[i];
            if (!point.isEmpty()) {
                this.paint.setColor(colors[i]);
                canvas.drawCircle(point.x, point.y, TOUCH_RADIUS, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (int i = 0; i < touches.length; i++) {
            touches[i].set(0, 0);
        }
        int pointerCount = event.getPointerCount();

        //all touches dispatched to this view, do not apply touchScale in this case
        if (pointerCount == globalTouchCount) {
            for (int i = 0; i < pointerCount && i < touches.length; i++) {
                touches[i].set(event.getX(i), event.getY(i));
            }
        } else {
            //Some touches dispatched to different view so we should apply touch scale
            for (int i = 0; i < pointerCount && i < touches.length; i++) {
                if (globalTouchCount > 1) {
                    touches[i].set(event.getX(i) / touchScale, event.getY(i) / touchScale);
                }
            }
        }

        invalidate();
        return true;
    }

    private void detectTouchScale() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Point outSmallestSize = new Point();
        Point outLargestSize = new Point();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getCurrentSizeRange(outSmallestSize, outLargestSize);

        this.touchScale = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels) / (float) outLargestSize.x;
    }

    public void setGlobalTouchCount(int pointerCount) {
        this.globalTouchCount = pointerCount;
    }

    class FloatPoint {
        float x, y;

        public FloatPoint() {
            this.x = 0;
            this.y = 0;
        }

        public void set(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public boolean isEmpty() {
            return x == 0 && y == 0;
        }
    }
}
