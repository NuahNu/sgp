package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene.frameTime;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.facility.Facility;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.PhysicalObject;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;

public class Ship extends PhysicalObject {
    protected String name;
    protected String className;
    protected float shipMass;         // 비행기만의 질량.
    protected float enginePower;      // 엔진의 세기.
    protected final float LIMIT_RATE = 0.8f;   // 플레이어가 조작하지 않을 때 최고 속도 비율

    protected float turnRate;         // 선회율
    protected ArrayList<Weapon> weaponList = new ArrayList<>();      // 무기 배열
    protected ArrayList<Vector2D> weaponLocationList = new ArrayList<>();      // 무기 배열
    protected ArrayList<Facility> facilityList = new ArrayList<>();  // 시설 배열
    // 나중에 Facility로 확인하도록 변경.
    private boolean weaponPowered;
    protected float weaponArmLength;
    private final float maxWeaponArmLength = 14f;
    //---------------------------
    public Ship(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);

        radian = 0;

        initWeapon();
        initFacility();
        initWeaponLocation();
        renewalStatus();
    }

    protected void initWeapon() {}
    protected void initWeaponLocation() {}

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
        // 나중에 Facility 중 weaponSystem으로 확인하도록 변경.
        weaponPowered = true;
        weaponArmLength = 0;
        //---------------------------
    }

    public void applyLimits(){
        maxSpeed *= LIMIT_RATE;
    }
    public void liftLimits(){
        maxSpeed /= LIMIT_RATE;
    }
    public float getWeaponArmLength(){return weaponArmLength;}

    @Override
    public void update() {
        super.update();
        for(Weapon w : weaponList)
            w.update();
        // test code
//        Vector2D tmp = new Vector2D();
//        tmp.x = (enginePower / mass) * Math.cos(radian);
//        tmp.y = (enginePower / mass) * Math.sin(radian);
//         얘는 가속이니까 frametime을 곱해야할듯?
//        tmp.multiply(frameTime);
//        addImpulse(tmp);
        radian += Math.toRadians(30 * frameTime);
        // 나중에 Facility 중 weaponSystem으로 확인하도록 변경.
        updateWeaponArm();
        //---------------------------
    }

    private void updateWeaponArm() {
        if (weaponPowered) {
            if(weaponArmLength < maxWeaponArmLength)
                weaponArmLength += maxWeaponArmLength * frameTime;
            if (weaponArmLength > maxWeaponArmLength)
            {
                weaponArmLength = maxWeaponArmLength;
                // test code
                weaponPowered = false;
            }
        }
        else {
            if(weaponArmLength > 0)
                weaponArmLength -= maxWeaponArmLength * frameTime;
            if(weaponArmLength < 0)
            {
                weaponArmLength = 0;
                //test code
                weaponPowered = true;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int i = 0;
        Vector2D Vec2;
        for(Weapon w : weaponList)
        {
            Vec2 = new Vector2D(weaponLocationList.get(i));
            offsetArm(i, Vec2);

            w.draw(canvas, Vec2);
            i++;
        }
    }

    protected void offsetArm(int i, Vector2D vec2) {
        if(i%2 ==0)
            vec2.add(0,weaponArmLength);
        else
            vec2.add(0,-weaponArmLength);
    }
}
