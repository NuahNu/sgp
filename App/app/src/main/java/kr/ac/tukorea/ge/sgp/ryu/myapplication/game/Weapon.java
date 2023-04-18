package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.AnimSprite;

public class Weapon extends AnimSprite {
    protected Object owner;
    private float mass;

    protected int projectileType; // 0 = 물리, 1 = 에너지 enum?

    // 함선을 기준으로한 위치
    //

    public Weapon(int bitmapResId, float cx, float cy, float width, float height, float fps, int frameCount, Object inputOwner) {
        super(bitmapResId, cx, cy, width, height, fps, frameCount);

        // 자식 클라스에서 결정.
        //-----------------------
        mass = 0;
        this.owner = inputOwner;
        //-----------------------
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
