package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class Bullet extends Projectile{

    public Bullet(int bitmapResId, float cx, float cy, Weapon owner) {
        super(bitmapResId, cx, cy, 50, 20, 4, 4, owner);
        mass = 1;

    }
}
