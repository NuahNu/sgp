package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class HpBar extends Sprite{
    private boolean isRight;
    private Ship ship;
    private static final float RATIO = 20;
    GaugeBar hull;
    GaugeBar shield;

    public HpBar(Ship ship, boolean is_right){
        super(R.mipmap.hp_bar, 0, 0, 385 * RATIO, 65 * RATIO);
        this.isRight = is_right;
        float xPos;
        int nullBitmapResId;
        int shieldBitmapResId;

        if(is_right){
            setBitmapResource(R.mipmap.hp_bar_right);
            xPos = Metrics.game_width - 11 * RATIO;
            nullBitmapResId = R.mipmap.hp_right;
            shieldBitmapResId = R.mipmap.shield_right;
            setPos(Metrics.game_width, 0);
        }else {
            xPos = 11 * RATIO;
            nullBitmapResId = R.mipmap.hp;
            shieldBitmapResId = R.mipmap.shield;
        }
        hull = new GaugeBar(nullBitmapResId, xPos, 0, 359, 65, RATIO, isRight);
        shield = new GaugeBar(shieldBitmapResId, xPos, 0, 359, 65, RATIO, isRight);

        this.ship = ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    protected void setSize(float width, float height) {
        if(isRight){
            dstRect.set(x - width, y, x, y + height);
        }else{
            dstRect.set(x, y, x + width, y + height);
        }
    }

    @Override
    public void update() {
        if(ship != null){
            float hullRatio = ship.getOwnHP().getHullRatio();
            float shieldRatio = ship.getOwnHP().getShieldRatio();
            hull.update(hullRatio);
            shield.update(shieldRatio);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(ship != null) {
            super.draw(canvas);
            hull.draw(canvas);
            shield.draw(canvas);
        }
    }
}
