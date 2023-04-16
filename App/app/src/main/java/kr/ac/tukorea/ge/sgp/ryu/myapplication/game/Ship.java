package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;


import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.IBoxCollidable;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.Sprite;

public class Ship extends Sprite implements IBoxCollidable {
    protected RectF collisionRect = new RectF();
    private float radian;           // 각
    private float mass;             // 질량
    private Vector2D speed;         // 속도
    private Vector2D acceleration;  // 가속도
    private float turnRate;         // 선회율
                                    // 크기 - Sptrite에 Rect,width, height
    private Weapon WeaponList;      // 무기 배열
    private Facility FacilityList;  // 시설 배열

    public Ship(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);
        initWeapon();
        initFacility();
    }

    private void initWeapon() {

    }

    private void initFacility() {

    }

    protected void fixCollisionRect() {
        collisionRect.set(dstRect);
    }

    @Override
    public void update() {
        super.update();
        fixCollisionRect();
        UpdateAcceleration();
        UpdateSpeed();
    }

    private void UpdateSpeed() {

    }

    private void UpdateAcceleration() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate((float) Math.toDegrees( radian), x, y);
        super.draw(canvas);
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
