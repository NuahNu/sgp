package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.graphics.Rect;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;

public class Laser extends Weapon{
    private boolean isRightSide;

    public Laser(Ship inputOwner, boolean is_rightSide) {
        super(R.mipmap.laser1_strip12, 100, 100, 16 * 2, 60 * 2, inputOwner);
        if(is_rightSide)
            setBitmapResource(R.mipmap.laser1_strip12);
        else
            setBitmapResource(R.mipmap.laser1_strip12_left);

        rects = new Rect[] {
                new Rect(  0, 0,   0 + 16, 60), //  재장전 1
                new Rect(  16, 0,   16 + 16, 60),
                new Rect(  32, 0,   32 + 16, 60),
                new Rect(  48, 0,   48 + 16, 60),
                new Rect(  64, 0,   64 + 16, 60),// 5
                new Rect(  80, 0,   80 + 16, 60),// 발사 1
                new Rect(  96, 0,   96 + 16, 60),
                new Rect(  112, 0,   112 + 16, 60),
                new Rect(  128, 0,   128 + 16, 60),
                new Rect(  144, 0,   144 + 16, 60),
                new Rect(  160, 0,   160 + 16, 60),
                new Rect(  176, 0,   176 + 16, 60),
                new Rect(  192, 0,   192 + 16, 60),// 7
        };
        maxBulletStock = 3;
        isRightSide = is_rightSide;
        coolTime = 3;
        firingTime = 0.25f;
        reloadingSprite = 5;
        firingSprite = 7;
    }

    @Override
    public void update() {

        super.update();
    }
}
