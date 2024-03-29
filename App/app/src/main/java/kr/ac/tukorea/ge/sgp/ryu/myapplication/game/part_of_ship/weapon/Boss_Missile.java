package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon;

import android.graphics.Rect;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile.Bullet;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile.Missile;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Boss;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class Boss_Missile extends Weapon {
    public Boss_Missile(Ship inputOwner) {
        super(R.mipmap.boss_missile_strip3, 0, 0, 33 * 1, 65 * 1, inputOwner);

        rects = new Rect[] {
                new Rect(  0,   0,   0 + 33,    65), //  재장전 1
                new Rect(  33,  0,   33+ 33,   65),
                new Rect(  66,  0,   66+ 33,   65),// 발사 1
        };
        maxBulletStock = 3;
        coolTime = 3;
        firingTime = 0.25f;
        reloadingSprite = 2;
        firingSprite = 1;
        projectileType = 0;
        damage = 10;
        bulletSpeed = 500;
        bulletLifeTime = 10;
    }
    @Override
    protected void makeProjectile() {
        BaseScene.getTopScene().add(MainScene.Layer.bullet, new Missile(R.mipmap.missile_1,x,y,this));
    }
}