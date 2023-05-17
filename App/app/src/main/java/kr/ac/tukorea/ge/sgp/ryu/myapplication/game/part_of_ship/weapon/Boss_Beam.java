package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon;

import android.graphics.Rect;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile.Beam;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile.Bullet;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Boss;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class Boss_Beam extends Weapon {
    public Boss_Beam(Ship inputOwner) {
        super(R.mipmap.boss_beam_strip8, 0, 0, 33 * 1, 65 * 1, inputOwner);

        rects = new Rect[] {
                new Rect(  0,   0,   0 + 33,    65), //  재장전 1
                new Rect(  231, 0,   231+ 33,  65),
                new Rect(  198, 0,   198+ 33,  65),
                new Rect(  165, 0,   165+ 33,  65),
                new Rect(  132, 0,   132+ 33,  65),
                new Rect(  99,  0,   99+ 33,   65),
                new Rect(  33,  0,   33+ 33,   65),
                new Rect(  66,  0,   66+ 33,   65),// 발사 1
                new Rect(  99,  0,   99+ 33,   65),
                new Rect(  132, 0,   132+ 33,  65),
                new Rect(  165, 0,   165+ 33,  65),
                new Rect(  198, 0,   198+ 33,  65),
                new Rect(  231, 0,   231+ 33,  65),
        };
        maxBulletStock = 1;
        coolTime = 1;
        firingTime = 3;
        reloadingSprite = 7;
        firingSprite = 6;
    }
    @Override
    protected void makeProjectile() {
        BaseScene.getTopScene().add(MainScene.Layer.bullet, new Beam(R.mipmap.beam_red,this, firingTime, true, 9));
    }
}
