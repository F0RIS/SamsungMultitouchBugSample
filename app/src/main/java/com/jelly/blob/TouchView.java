package com.jelly.blob;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchView extends View {
    private final Paint paint;

    float x = 50;
    float y = 50;

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
        this.paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, 80, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println(event);
        x = event.getX();
        y = event.getY();
        invalidate();
        return true;
    }
}
