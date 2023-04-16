package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.BaseScene.frameTime;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Ship extends PhysicalObject{
    protected float shipMass;         // 비행기만의 질량.
    protected float enginePower;      // 엔진의 세기.
    protected final float LIMIT_RATE = 0.8f;   // 플레이어가 조작하지 않을 때 최고 속도 비율

    protected float turnRate;         // 선회율
    protected ArrayList<Weapon> weaponList = new ArrayList<>();      // 무기 배열
    protected ArrayList<Facility> facilityList = new ArrayList<>();  // 시설 배열

    public Ship(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);

        radian = 0;

        initWeapon();
        initFacility();
        renewalStatus();
    }

    protected void initWeapon() {}

    protected void initFacility() {}

    protected final void renewalStatus(){       // mass가 Ship에서 정해지지 않음.
        // 질량 갱신
        mass = shipMass;
        for(Weapon w : weaponList)
            mass += w.getMass();
        for(Facility w : facilityList)
            mass += w.getMass();
        // 최대 속도 갱신
        // 선회율 갱신
        // PhysicalObject의 acceleration에 엔진 파워 적용.
        acceleration = enginePower;
    }
    public void applyLimits(){
        maxSpeed *= LIMIT_RATE;
    }
    public void liftLimits(){
        maxSpeed /= LIMIT_RATE;
    }
    @Override
    public void update() {
        super.update();

        // test code
        Vector2D tmp = new Vector2D();
        tmp.x = (enginePower / mass) * Math.cos(radian);
        tmp.y = (enginePower / mass) * Math.sin(radian);
        addImpulse(tmp);
    }

    @Override
    public void draw(Canvas canvas) {

        for(Weapon w : weaponList)
            w.draw(canvas);
        super.draw(canvas);
    }
}
