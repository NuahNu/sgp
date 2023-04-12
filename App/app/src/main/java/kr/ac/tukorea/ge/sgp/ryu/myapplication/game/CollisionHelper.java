package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.IBoxCollidable;

public class CollisionHelper {
    private static final String TAG = CollisionHelper.class.getSimpleName();

    public static boolean AABB(IBoxCollidable obj1, IBoxCollidable obj2) {
        RectF r1 = obj1.getCollisionRect();
        RectF r2 = obj2.getCollisionRect();

        if (r1.left > r2.right) return false;
        if (r1.top > r2.bottom) return false;
        if (r1.right < r2.left) return false;
        if (r1.bottom < r2.top) return false;

        return true;
    }

    public static boolean OBB (IBoxCollidable obj1, IBoxCollidable obj2) {
        RectF r1 = obj1.getCollisionRect();
        RectF r2 = obj2.getCollisionRect();

        Vector2D dist = new Vector2D(Math.abs((r1.left+r1.right - r2.left-r2.right)/2), Math.abs((r1.top+r1.bottom - r2.top-r2.bottom)/2));
        Vector2D vec[] = new Vector2D[4];
        float radian;
        radian = obj1.getRadian();

        vec[0] = new Vector2D(Math.cos(radian), Math.sin(radian));
        vec[1] = new Vector2D(Math.cos(radian+Math.PI/2), Math.sin(radian+Math.PI/2));

        radian = obj2.getRadian();

        vec[2] = new Vector2D(Math.cos(radian), Math.sin(radian));
        vec[3] = new Vector2D(Math.cos(radian+Math.PI/2), Math.sin(radian+Math.PI/2));

        Vector2D unit;
        for(int i=0; i<4; i++){
            double sum = 0;
            unit = vec[i].getNormalized();
            for(int j=0; j<4; j++){
                sum += Math.abs(Vector2D.dot(vec[j], unit));
            }
            if(Math.abs(Vector2D.dot(dist, unit)) > sum){
                return false;
            }
        }
        return true;
    }

}
