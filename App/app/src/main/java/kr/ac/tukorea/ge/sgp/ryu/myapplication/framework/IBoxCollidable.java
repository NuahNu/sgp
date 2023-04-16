package kr.ac.tukorea.ge.sgp.ryu.myapplication.framework;

import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;

public interface IBoxCollidable {
    public RectF getCollisionRect();
    public float getRadian(); // int인가?
    public Vector2D getImpulse();
    public void addImpulse(Vector2D input);
}
