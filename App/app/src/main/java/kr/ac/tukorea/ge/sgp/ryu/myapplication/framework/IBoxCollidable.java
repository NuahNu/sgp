package kr.ac.tukorea.ge.sgp.ryu.myapplication.framework;

import android.graphics.RectF;

public interface IBoxCollidable {
    public RectF getCollisionRect();
    public float getRadian(); // int인가?
}
