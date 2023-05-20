package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class HpBar extends Sprite{
    private Ship player;
    GaugeBar hull = new GaugeBar(R.mipmap.hp, 11 * 10, 0, 385 * 10, 65 * 10);
    GaugeBar shield = new GaugeBar(R.mipmap.shield, 11 * 10, 0, 385 * 10, 65 * 10);

    public HpBar(Ship player){
        super(R.mipmap.hp_bar, 0, 0, 385 * 10, 65 * 10);

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
