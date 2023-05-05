package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon;

import android.graphics.Rect;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile.Bullet;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Boss;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class Boss_Ion extends Weapon {
    public Boss_Ion(Ship inputOwner) {
        super(R.mipmap.boss_ion_strip8, 0, 0, 33 * 1, 65 * 1, inputOwner);

        rects = new Rect[] {
                new Rect(  0,   0,   0 + 33,    65), //  재장전 1
                new Rect(  33,  0,   33+ 33,   65),
                new Rect(  66,  0,   66+ 33,   65),
                new Rect(  99,  0,   99+ 33,   65),
                new Rect(  132, 0,   132+ 33,  65),// 5
                new Rect(  165, 0,   165+ 33,  65),// 발사 1
                new Rect(  198, 0,   198+ 33,  65),
                new Rect(  231, 0,   231+ 33,  65),
        };
        maxBulletStock = 3;
        coolTime = 3;
        firingTime = 0.25f;
        reloadingSprite = 5;
        firingSprite = 3;
    }
    @Override
    protected void makeProjectile() {
        BaseScene.getTopScene().add(MainScene.Layer.bullet, new Bullet(R.mipmap.laser_heavy_strip4,x,y,this));
    }
}
