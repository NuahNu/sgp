package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.AnimSprite;

public class Weapon extends AnimSprite {
    private float mass;

    // 함선을 기준으로한 위치
    //

    public Weapon(int bitmapResId, float cx, float cy, float width, float height, float fps, int frameCount) {
        super(bitmapResId, cx, cy, width, height, fps, frameCount);
    }


    public float getMass(){
        return mass;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void update() {
        super.update();
    }
}
