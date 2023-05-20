package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class HpBar extends Sprite{
    private Ship player;
    private static final float RATIO = 25;
    GaugeBar hull = new GaugeBar(R.mipmap.hp, 11 * RATIO, 0, 359, 65, RATIO);
    GaugeBar shield = new GaugeBar(R.mipmap.shield, 11 * RATIO, 0, 359, 65, RATIO);

    public HpBar(Ship player){
        super(R.mipmap.hp_bar, 0, 0, 385 * RATIO, 65 * RATIO);

        this.player = player;
    }

    @Override
    protected void setSize(float width, float height) {
        dstRect.set(x, y, x + width, y + height);
    }

    @Override
    public void update() {
        float hullRatio = player.getOwnHP().getHullRatio();
        float shieldRatio = player.getOwnHP().getShieldRatio();
        hull.update(hullRatio);
        shield.update(shieldRatio);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        hull.draw(canvas);
        shield.draw(canvas);
    }
}
