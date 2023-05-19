package com.jelly.blob;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

//Wrong touch coords are dispatched to all views that are involved in multitouch
//Sometimes button do not trigger it's onClick listener bcz wrong touch coords can be outside of button area
public class TouchButton extends Button {

    public TouchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println(event.toString());
        return super.onTouchEvent(MotionEvent.obtain(
                event.getDownTime(),
                event.getEventTime(),
                event.getAction(),
                50,50,
                event.getMetaState()
        ));
    }
}
