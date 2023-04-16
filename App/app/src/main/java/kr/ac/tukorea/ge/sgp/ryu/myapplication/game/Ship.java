package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;


import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.BaseScene.frameTime;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.IBoxCollidable;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.Sprite;

public class Ship extends Sprite implements IBoxCollidable {
    protected RectF collisionRect = new RectF();
    private float radian;           // 각
    private float mass;             // 질량의 총 합. 보통 계산은 이걸 이용.
    private float shipMass;         // 함선의 질량. 함선별로 달라야 함.
    private float enginePower;      // 엔진의 세기. 함선별로 달라야 함.
    private Vector2D speed;         // 속도.
    private float maxSpeed;         // 최대속도. 함선별로 달라야 함.
    private final float LIMIT_RATE = 0.8f;   // 플레이어가 조작하지 않을 때 최고 속도 비율
    private final float DECELERATION_TIME = 1f;   // 감속에 걸리는 시간.
    private Vector2D acceleration;  // 가속도
    private float turnRate;         // 선회율
                                    // 크기 - Sptrite에 Rect,width, height
    private ArrayList<Weapon> weaponList;      // 무기 배열
    private ArrayList<Facility> facilityList;  // 시설 배열

    public Ship(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);
        initWeapon();
        initFacility();
        radian = 0;
    }

    private void initWeapon() {}

    private void initFacility() {}

    private final void renewalStatus(){
        // 질량 갱신
        mass = shipMass;
        for(Weapon w : weaponList)
            mass += w.getMass();
        for(Facility w : facilityList)
            mass += w.getMass();
        // 최대 속도 갱신
        // 선회율 갱신
    }

    protected void fixCollisionRect() {
        collisionRect.set(dstRect);
    }

    @Override
    public void update() {
        super.update();
        acceleration.set(0, 0); //
        fixCollisionRect();
        UpdateAcceleration();
        UpdateSpeed();
        UpdateLocation();
    }

    private void UpdateLocation() {
        Vector2D delta = speed;
        delta.multiply(frameTime);
        x += delta.x;
        y += delta.y;
    }

    private void UpdateSpeed() {
        Vector2D delta = acceleration;

        // 엔진을 사용중일때
        if(delta.getLength() > 0){
            // 속도 변화량 적용.
            delta.multiply(frameTime);
            speed.add(delta);
            // 최대속도보다 높으면
            if(speed.getLength() > maxSpeed) {
                delta = speed;
                delta.multiply(-(speed.getLength() - maxSpeed) / DECELERATION_TIME);
            }
        }
        // 아닐때
        else {
            // LIMIT_RATE 보다 높으면
            if(speed.getLength() > maxSpeed * LIMIT_RATE) {
                delta = speed;
                delta.multiply(-(speed.getLength() - maxSpeed * LIMIT_RATE) / DECELERATION_TIME);   // 버그 있으면 일단 -0.2로
            }
        }
        delta.multiply(frameTime);
        speed.add(delta);
    }

    private void UpdateAcceleration() {
        // 키 입력에 따라 각도가 달라짐.
        if(false) {
            // 해당 동작이 있으면
            acceleration.x = (enginePower / mass) * Math.cos(radian);
            acceleration.y = (enginePower / mass) * Math.sin(radian);
        }
        // test code
    }

    public void getImpulse(Vector2D input){
        speed.multiply(mass);
        speed.add(input);
        speed.multiply(1/mass);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate((float) Math.toDegrees( radian), x, y);
        for(Weapon w : weaponList)
            w.draw(canvas);
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
