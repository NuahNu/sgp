package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.HP;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Laser;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;

public class Kestrel_0 extends Ship {
    public Kestrel_0(float cx, float cy) {
        super(R.mipmap.kestrel_0, cx, cy, 598, 364);
        // 기체 제원
        maxSpeed = 5;
        mass = 1;
        enginePower = 5;
        name = String.valueOf(R.string.kestrel_0_name);
        className = String.valueOf(R.string.cruiser);
        ownHP = new HP(R.mipmap.kestral_shields, cx, cy,800,503);
        ownHP.setHP(50,100);
    }

    @Override
    protected void initWeapon() {
        weaponList.add(new Laser(this, true));
        weaponList.add(new Laser(this, false));
        weaponList.add(new Laser(this, true));
        weaponList.add(new Laser(this, false));
    }
    protected void initWeaponLocation() {
        weaponLocationList.add(new Vector2D(210,46));
        weaponLocationList.add(new Vector2D(210,-46));
        weaponLocationList.add(new Vector2D(46,76));
        weaponLocationList.add(new Vector2D(46,-76));
    }

    @Override
    protected void initFacility() {
    }
}
