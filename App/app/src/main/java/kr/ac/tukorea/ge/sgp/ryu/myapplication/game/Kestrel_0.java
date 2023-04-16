package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;

public class Kestrel_0 extends Ship {
    public Kestrel_0(float cx, float cy) {
        super(R.mipmap.kestrel, cx, cy, 598/200, 364/200);

        maxSpeed = 5;
        mass = 1;
        enginePower = 5;
    }

    @Override
    protected void initWeapon() {
    }

    @Override
    protected void initFacility() {
    }

//    @Override
//    public void update() {
//        super.update();
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
