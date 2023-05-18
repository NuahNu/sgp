package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon;

import android.graphics.Rect;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile.Beam;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class Beam_Weapon extends Weapon {
    public Beam_Weapon(Ship inputOwner, boolean is_rightSide) {
        super(R.mipmap.beam_fire_strip10, 0, 0, 28 * 1, 52 * 1, inputOwner);
        if(is_rightSide)
            setBitmapResource(R.mipmap.beam_fire_strip10);
        else
            setBitmapResource(R.mipmap.beam_fire_strip10_left);

        rects = new Rect[] {
                new Rect(  0,   0,   0+ 28,    52), //  재장전 1
                new Rect(  28 , 0,   28+ 28,   52),
                new Rect(  56 , 0,   56+ 28,   52),// 발사 1
                new Rect(  84 , 0,   84+ 28,   52),
                new Rect(  112, 0,   112+ 28,  52),
                new Rect(  140, 0,   140+ 28,  52),
                new Rect(  168, 0,   168+ 28,  52),
                new Rect(  196, 0,   196+ 28,  52),
                new Rect(  224, 0,   224+ 28,  52),
                new Rect(  252, 0,   252+ 28,  52),
        };
        maxBulletStock = 1;
        coolTime = 1;
        firingTime = 3;
        reloadingSprite = 2;
        firingSprite = 8;
        projectileType = 1;
    }
    @Override
    protected void makeProjectile() {
        BaseScene.getTopScene().add(MainScene.Layer.bullet, new Beam(R.mipmap.beam_red,this, firingTime, true, 9));
    }
}