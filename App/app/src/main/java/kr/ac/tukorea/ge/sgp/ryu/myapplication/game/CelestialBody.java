package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;

public class CelestialBody extends Sprite implements IBoxCollidable {
    protected float pullingForce;   // 중력. 이건 아마 행성에만 있을듯.

    public CelestialBody(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);
    }

    @Override
    public RectF getCollisionRect() {
        return null;
    }

    @Override
    public float getRadian() {
        return 0;
    }

    @Override
    public Vector2D getImpulse() {
        return null;
    }

    @Override
    public void addImpulse(Vector2D input) {

    }
}
