package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class Bullet extends Projectile{

    public Bullet(int bitmapResId, float cx, float cy, Ship owner) {
        super(bitmapResId, cx, cy, 50, 20, 4, 4, owner);
        mass = 1;
        BULLET_SPEED = 1000;
        setSpeed(owner);
    }

    @Override
    protected void setSpeed(Ship owner) {
        super.setSpeed(owner);
    }
}
