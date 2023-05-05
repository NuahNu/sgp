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

    protected void renewalStatus(){}

    protected void fixCollisionRect() {
        collisionRect.set(dstRect);
    }

    @Override
    public void update() {
        super.update();
//        UpdateAcceleration();
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
            delta.multiply(-(acceleration / mass) / delta.getLength() * DECELERATION_RATE);
            delta.multiply(frameTime);
            speed.add(delta);
            if(speed.getLength() < maxSpeed){ // 더 낮아지면 안된다.
                speed.multiply(maxSpeed/speed.getLength());
            }
        }
//        System.out.println("3. speed : " + speed);
    }

//    private void UpdateAcceleration() {
//         키 입력에 따라 각도가 달라짐.
//         그냥 getImpulse로 구현 가능할지도?
//        acceleration.set(0, 0); // 터치 이벤트에 반응해서 초기화해야함.
//        if(false) {
//             해당 동작이 있으면
//            acceleration.x = (enginePower / shipMass) * Math.cos(radian);
//            acceleration.y = (enginePower / shipMass) * Math.sin(radian);
//        }
//    }

    public void addImpulse(Vector2D impulse){
//        impulse.multiply(frameTime);  // 버그나면 이게 문제
//        생각해보니 위치 갱신에서 speed에 frameTime을 곱하니까
//        그냥 더하는게 맞는듯

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
}
