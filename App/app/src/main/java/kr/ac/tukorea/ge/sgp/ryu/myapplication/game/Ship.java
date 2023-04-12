package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;


import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.IBoxCollidable;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.Metrics;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.Sprite;

public class Ship extends Sprite implements IBoxCollidable {

    private static final float FIGHTER_X = 4.5f;
    private static final float FIGHTER_Y = 14.8f;
    private static final float FIGHTER_WIDTH = 3f;
    private static final float FIGHTER_HEIGHT = 3f;
    private float dx, dy;
    protected RectF collisionRect = new RectF();
    private float radian;

    public Ship() {
        super(R.mipmap.kestrel_cruiser, FIGHTER_X, FIGHTER_Y, FIGHTER_WIDTH, FIGHTER_HEIGHT);

        Random r = new Random();

        // 이건 임시
        this.x = r.nextFloat() * 6.0f;
        this.y = r.nextFloat() * 6.0f;
        fixDstRect();
        //

        this.dx = r.nextFloat() * 1.0f;
        this.dy = r.nextFloat() * 1.0f;
    }

    @Override
    public void update() {
        super.update();

        collisionRect.set(dstRect);

        radian += 0.25f * Math.PI* BaseScene.frameTime;

        //dstRect.offset(dx * BaseScene.frameTime, dy * BaseScene.frameTime);
        if (dx > 0) {
            if (dstRect.right > Metrics.game_width) {
                dx = -dx;
            }
        } else {
            if (dstRect.left < 0) {
                dx = -dx;
            }
        }
        if (dy > 0) {
            if (dstRect.bottom > Metrics.game_height) {
                dy = -dy;
            }
        } else {
            if (dstRect.top < 0) {
                dy = -dy;
            }
        }
        x = (dstRect.left+ dstRect.right) /2;
        y = (dstRect.top+ dstRect.bottom) /2;
    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        canvas.save();
        canvas.rotate((float) Math.toDegrees( radian), x, y);
        canvas.drawBitmap(bitmap, null, dstRect, null);
        canvas.restore();
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public float getRadian() {
        return radian;
    }
}
