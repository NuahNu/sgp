package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;


import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.BaseScene.frameTime;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.IBoxCollidable;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.Sprite;

public class Ship extends Sprite implements IBoxCollidable {
    protected RectF collisionRect = new RectF();
    protected float radian;           // 각
    protected float mass;             // 질량의 총 합. 보통 계산은 이걸 이용.
    protected float shipMass;         // 함선의 질량. 함선별로 달라야 함.
    protected float enginePower;      // 엔진의 세기. 함선별로 달라야 함.
    protected Vector2D speed = new Vector2D(0,0);         // 속도.
    protected float maxSpeed;         // 최대속도. 함선별로 달라야 함.
    protected final float LIMIT_RATE = 0.8f;   // 플레이어가 조작하지 않을 때 최고 속도 비율
    protected final float DECELERATION_RATE = 1.5f;   // 감속 가속도 배율. 1보다 커야함. 1이면 충격에 따라...
    protected Vector2D acceleration = new Vector2D(0,0);  // 가속도
    protected float turnRate;         // 선회율
                                    // 크기 - Sptrite에 Rect,width, height
    protected ArrayList<Weapon> weaponList = new ArrayList<>();      // 무기 배열
    protected ArrayList<Facility> facilityList = new ArrayList<>();  // 시설 배열

    public Ship(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);
        initWeapon();
        initFacility();
        radian = 0;
    }

    protected void initWeapon() {}

    protected void initFacility() {}

    protected final void renewalStatus(){       // protected or private
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
        UpdateAcceleration();
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
        Vector2D delta = new Vector2D(acceleration);
        float limit;

        // 엔진을 사용중일때
        if(delta.getLength() > 0){
            // 속도 변화량 적용.
            delta.multiply(frameTime);
            speed.add(delta);

            limit = maxSpeed;
        }
        // 아닐때
        else {
            limit = maxSpeed * LIMIT_RATE;
        }
        // 감속
        if(speed.getLength() > limit) { // 속도제한보다 크면
            delta = new Vector2D(speed);
            delta.multiply(-(enginePower / mass) / delta.getLength() * DECELERATION_RATE);
            delta.multiply(frameTime);
            speed.add(delta);
            if(speed.getLength() < limit){ // 더 낮아지면 안된다.
                speed.multiply(limit/speed.getLength());
            }
        }
//        System.out.println("3. speed : " + speed);
    }

    private void UpdateAcceleration() {
        // 키 입력에 따라 각도가 달라짐.
        // 그냥 getImpulse로 구현 가능할지도?
        acceleration.set(0, 0); // 터치 이벤트에 반응해서 초기화해야함.
        if(true) {
            // 해당 동작이 있으면
            acceleration.x = (enginePower / mass) * Math.cos(radian);
            acceleration.y = (enginePower / mass) * Math.sin(radian);
        }
        // test code
    }

    public void addImpulse(Vector2D input){
        speed.multiply(mass);
        speed.add(input);
        speed.multiply(1/mass);
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
