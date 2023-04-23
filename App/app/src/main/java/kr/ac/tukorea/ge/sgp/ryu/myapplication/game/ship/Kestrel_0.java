package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Laser;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;

public class Kestrel_0 extends Ship {
    public Kestrel_0(float cx, float cy) {
        super(R.mipmap.kestrel, cx, cy, 598, 364);
        // 기체 제원
        maxSpeed = 5;
        mass = 1;
        enginePower = 5;
    }

    @Override
    protected void initWeapon() {
        weaponList.add(new Laser(this, true));
        weaponList.add(new Laser(this, false));
    }
    protected void initWeaponLocation() {
        weaponLocationList.add(new Vector2D(290,54));
        weaponLocationList.add(new Vector2D(290,-54));
    }

    @Override
    protected void initFacility() {
    }

//    @Override
//    public void update() {
//    }
//
//    @Override
//    public void draw(Canvas canvas) {
//        super.draw(canvas);
//    }
//
//    @Override
//    public RectF getCollisionRect() {
//        return super.getCollisionRect();
//    }
//
//    @Override
//    public float getRadian() {
//        return super.getRadian();
//    }
}
