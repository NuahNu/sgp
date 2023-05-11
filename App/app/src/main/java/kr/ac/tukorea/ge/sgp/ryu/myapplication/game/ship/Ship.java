package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene.frameTime;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Button;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.AnalogStick;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.HP;
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
    protected boolean weaponPowered;
    protected float weaponArmLength;
    private final float maxWeaponArmLength = 14f;
    protected HP ownHP = null;
    private float targetRadian;
    private boolean engineFlag;

    //---------------------------
    public Ship(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);


        initWeapon();
        initFacility();
        initWeaponLocation();
        renewalMassStatus();
        // 나중에 Facility 중 weaponSystem으로 확인하도록 변경.
        weaponPowered = true;
        weaponArmLength = 0;
        //---------------------------
        radian = 0;
        engineFlag = false;
    }

    protected void initWeapon() {}

    protected void initWeaponLocation() {}

    protected void initFacility() {}

    protected final void renewalMassStatus(){       // mass가 Ship에서 정해지지 않음.
        // 질량 갱신
        mass = shipMass;
        for(Weapon w : weaponList){
            if(w == null)
                continue;
            mass += w.getMass();
        }
        for(Facility f : facilityList){
            if(f == null)
                continue;
            mass += f.getMass();
        }
        // 최대 속도 갱신
        maxSpeed = enginePower* 2;
        // 선회율 갱신
        turnRate = (float) Math.toRadians(180);

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
        for(Weapon w : weaponList){
            if(w == null)
                continue;
            w.update(weaponPowered);
        }
        if (ownHP != null) {

            // 나중에 Facility 중 weaponSystem으로 확인하도록 변경.
            updateWeaponArm();
            ownHP.setPos(x, y);
            ownHP.update();
            updateRadian();
        }
//---------------------------
        // test code
//        getDamage(false,50*frameTime);
    }

    private void updateRadian() {
        float sign;
        if(Math.abs(this.radian - targetRadian) > Math.toRadians(180)) {
            if (targetRadian > this.radian)
                this.radian += Math.toRadians(360);
            else
                this.radian -= Math.toRadians(360);
        }
        if(Math.abs(this.radian - targetRadian) > 0.001){
            if(radian > targetRadian){
                this.radian -= frameTime * turnRate;
                if(radian < targetRadian)
                    radian = targetRadian;
            }
            else{
                this.radian += frameTime * turnRate;
                if(radian > targetRadian)
                    radian = targetRadian;
            }
        }
        if(Math.abs(this.radian - targetRadian) < 0.1){
            if(engineFlag){
                Vector2D tmp = new Vector2D(Math.cos(radian),Math.sin(radian));
                tmp.multiply(mass*enginePower * frameTime); // 가속도는 frameTime 곱해주고
                addImpulse(tmp);
            }
        }
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
        int i = 0;
        Vector2D Vec2;
        for(Weapon w : weaponList) {
            if(w == null) {
                i++;
                continue;
            }
            Vec2 = new Vector2D(weaponLocationList.get(i));
            offsetArm(i, Vec2);

            w.draw(canvas, Vec2);
            i++;
        }
        super.draw(canvas);
        if (ownHP != null){
            if(ownHP.shieldExist()){
                // 실드를 그린다.
                //canvas.drawBitmap(bitmap, null, dstRect, null);
                ownHP.draw(canvas);
            }
        }

        canvas.restore();
    }

    protected void offsetArm(int i, Vector2D vec2) {
        if(i%2 ==0)
            vec2.add(0,weaponArmLength);
        else
            vec2.add(0,-weaponArmLength);
    }

    public void getDamage(boolean damageType, float amount ) {
        if (ownHP != null) {
            if( ownHP.getDamage(damageType, amount) ){
                whenDeath();
            }
        }
    }

    protected void whenDeath() {
        // 각 함선별로 Gib를 생성.
    }

    protected Gib getGib(int index) {
        return null;
    }

    public void radianInput(AnalogStick.Action action, float radian){
        switch (action){
            case pressed:
                this.engineFlag = true;
//                targetRadian = radian;
                break;
            case moved:
                targetRadian = radian;
                break;
            case released:
                this.engineFlag = false;
//                targetRadian = this.radian; // 보류가능
                break;
        }
    }
}
