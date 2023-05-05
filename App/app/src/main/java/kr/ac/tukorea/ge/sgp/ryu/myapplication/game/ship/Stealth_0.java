package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.HP;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Laser;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;

public class Stealth_0 extends Ship {
    public Stealth_0(float cx, float cy) {
        super(R.mipmap.stealth_0, cx, cy, 598, 364);
        // 기체 제원
        maxSpeed = 5;
        mass = 1;
        enginePower = 5;
        name = String.valueOf(R.string.stealth_0_name);
        className = String.valueOf(R.string.corvette);
        ownHP = new HP(R.mipmap.kestral_shields, cx, cy,800,562);
        ownHP.setHP(30, 200);
    }

    @Override
    protected void initWeapon() {
        weaponList.add(new Laser(this, true));
        weaponList.add(new Laser(this, false));
        weaponList.add(null);
        weaponList.add(new Laser(this, false));
    }
    protected void initWeaponLocation() {
        weaponLocationList.add(new Vector2D(150,37));
        weaponLocationList.add(new Vector2D(150,-37));
        weaponLocationList.add(null);
        weaponLocationList.add(new Vector2D(-65,-108));
    }

    @Override
    protected void initFacility() {
    }

//    @Override
//    protected void offsetArm(int i, Vector2D vec2) {
//        if(i ==0)
//            vec2.add(0,weaponArmLength);
//        else
//            vec2.add(0,-weaponArmLength);
//    }
}
