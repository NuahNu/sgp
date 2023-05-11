package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.ITouchable;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Button;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics;

public class AnalogStick extends Sprite implements ITouchable {
    private static final String TAG = AnalogStick.class.getSimpleName();
    private final Callback callback;
    public enum Action {
        pressed, moved, released,
    }
    private Sprite stick;
    private final float STICK_RATIO = 0.34f;

    private float DEFAULT_X;
    private float DEFAULT_Y;

    public interface Callback {
        public boolean onTouch(float radian);
    }
    public AnalogStick(float cx, float cy, float size, Callback callback) {
        super(R.mipmap.analog_stick_1, cx, cy, size, size);
        this.callback = callback;

        stick =  new Sprite(R.mipmap.analog_stick_2, cx, cy, STICK_RATIO * size, STICK_RATIO * size);
        DEFAULT_X = cx;
        DEFAULT_Y = cy;

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float touched_x = Metrics.toGameX(e.getX());
        float touched_y = Metrics.toGameY(e.getY());
        int action = e.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
//                if (!dstRect.contains(touched_x, touched_y)) {
//                    return false;
//                }
                this.setPos(touched_x,touched_y);
                break;
            case MotionEvent.ACTION_MOVE:
                stick.setPos(touched_x,touched_y);
                break;
            case MotionEvent.ACTION_UP:
                setPos(DEFAULT_X,DEFAULT_Y);
                stick.setPos(this.x,this.y);
                break;
        }
        callback.onTouch((float) Math.atan2(stick.getX() - this.x, stick.getY() - this.y));
//        Log.d(TAG, "Button.onTouch(" + System.identityHashCode(this) + ", " + e.getAction() + ", " + e.getX() + ", " + e.getY());
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        stick.draw(canvas);
    }
}
