package kr.ac.tukorea.ge.sgp.ryu.myapplication.framework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.BuildConfig;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Ship;

public class BaseScene {
    private static ArrayList<BaseScene> stack = new ArrayList<>();
    public static float frameTime;
    protected static Handler handler = new Handler();
    private static Paint bboxPaint;

    public static BaseScene getTopScene() {
        int top = stack.size() - 1;
        if (top < 0) return null;
        return stack.get(top);
    }

    public static void popAll() {
        while (!stack.isEmpty()) {
            BaseScene scene = getTopScene();
            scene.popScene();
        }
    }

    public int pushScene() {
        stack.add(this);
        return stack.size();
    }

    public void popScene() {
        stack.remove(this);
        // TODO: additional callback should be called
    }

    public int add(IGameObject gobj) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                objects.add(gobj);
            }
        });
        return objects.size();
    }

    public int count() {
        return objects.size();
    }
    public void update(long elapsedNanos) {
        frameTime = elapsedNanos / 1_000_000_000f;
        for (IGameObject gobj : objects) {
            gobj.update();
        }
    }

    public void draw(Canvas canvas) {
        for (IGameObject gobj : objects) {
            gobj.draw(canvas);
        }

        if (BuildConfig.DEBUG) {
            if (bboxPaint == null) {
                bboxPaint = new Paint();
                bboxPaint.setStyle(Paint.Style.STROKE);
                bboxPaint.setColor(Color.RED);
            }
            for (IGameObject gobj : objects) {
                if (gobj instanceof Ship) {
                    Ship tmp = ((Ship) gobj);
                    RectF rect = tmp.getCollisionRect();
                    float x = tmp.x;
                    float y = tmp.y;
                    float radian = tmp.getRadian();

                    canvas.save();
                    canvas.rotate((float) Math.toDegrees( radian), x, y);
                    canvas.drawRect(rect, bboxPaint);
                    canvas.restore();
                }
            }
        }
    }

    protected ArrayList<IGameObject> objects = new ArrayList<>();

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void remove(IGameObject gobj) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                objects.remove(gobj);
            }
        });
    }
}