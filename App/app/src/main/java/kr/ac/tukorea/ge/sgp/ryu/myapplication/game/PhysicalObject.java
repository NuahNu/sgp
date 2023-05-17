package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;


import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene.frameTime;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;

public class PhysicalObject extends Sprite implements IBoxCollidable {
    protected RectF collisionRect = new RectF();
    protected float radian;           // 각
    protected float mass;             // 질량의 합.
    protected Vector2D speed = new Vector2D(0,0);         // 속도.
    protected float maxSpeed;         // 최대속도.
    protected final float DECELERATION_RATE = 1.5f;   // 감속 가속도 배율. 1보다 커야함. 1이면 충격에 따라...
    protected float acceleration;     // 가속도
                                    // 크기 - Sptrite에 Rect,width, height

    public PhysicalObject(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);
    }

    protected void renewalMassStatus(){}

    protected void fixCollisionRect() {
        collisionRect.set(dstRect);
    }

    @Override
    public void update() {
        super.update();
        UpdateSpeed();
        UpdateLocation();
        fixDstRect();           // 위치 갱신
        fixCollisionRect();     // 충돌박스 갱신. dstRect를 기반으로 하므로 먼저 불려야함.
    }

    private void UpdateLocation() {
        Vector2D delta = new Vector2D(speed);
        delta.multiply(frameTime);
        x += delta.x;
        y += delta.y;
    }

    private void UpdateSpeed() {
        Vector2D delta;
        // 가속은 addImpulse()로 구현

        // 감속
        if(speed.getLength() > maxSpeed) { // 속도제한보다 크면
            delta = new Vector2D(speed);
            delta.multiply(-(acceleration) / delta.getLength() * DECELERATION_RATE);
            delta.multiply(frameTime);
            speed.add(delta);
            if(speed.getLength() < maxSpeed){ // 더 낮아지면 안된다.
                speed.multiply(maxSpeed/speed.getLength());
            }

//            Vector2D tmp = new Vector2D(Math.cos(radian),Math.sin(radian));
//            tmp.multiply(-mass * acceleration * frameTime); // 가속도는 frameTime 곱해주고
//            this.addImpulse(tmp);
        }
    }

    public void addImpulse(Vector2D impulse){
        speed.multiply(mass);
        speed.add(impulse);
        speed.multiply(1/ mass);
    }

    public Vector2D getImpulse(){
        Vector2D value = new Vector2D(speed);
        value.multiply(mass);
        return value;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate((float) Math.toDegrees( radian), x, y);
        super.draw(canvas);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public float getRadian() {
        return radian;
    }

    public Vector2D getSpeed() {
        return speed;
    }

    public float getBiggerSize(){ return Math.max(width,height); }

}
