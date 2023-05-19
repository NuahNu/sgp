package kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.util;

import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;

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

    // 중심점이 다른 경우 회전의 결과가 달라져 버그가 생길 수 있다.
    public static boolean OBB (IBoxCollidable obj1, IBoxCollidable obj2) {
        RectF r1 = obj1.getCollisionRect();
        RectF r2 = obj2.getCollisionRect();

        float r1_h = (r1.top - r1.bottom)/2;
        float r1_w = (r1.right - r1.left)/2;
        float r2_h = (r2.top - r2.bottom)/2;
        float r2_w = (r2.right - r2.left)/2;

        Vector2D dist = new Vector2D(Math.abs((r1.left+r1.right - r2.left-r2.right)/2), Math.abs((r1.top+r1.bottom - r2.top-r2.bottom)/2));
        Vector2D vec[] = new Vector2D[4];
        float radian;
        radian = obj1.getRadian();

        vec[0] = new Vector2D(Math.cos(radian), Math.sin(radian));
        vec[0].multiply(r1_w);
        vec[1] = new Vector2D(Math.cos(radian+Math.PI/2), Math.sin(radian+Math.PI/2));
        vec[1].multiply(r1_h);

        radian = obj2.getRadian();

        vec[2] = new Vector2D(Math.cos(radian), Math.sin(radian));
        vec[2].multiply(r2_w);
        vec[3] = new Vector2D(Math.cos(radian+Math.PI/2), Math.sin(radian+Math.PI/2));
        vec[3].multiply(r2_h);


        Vector2D unit;
        for(int i=0; i<4; i++){
            double sum = 0;
            unit = vec[i].getNormalized();
            for(int j=0; j<4; j++){
                double tmp = Vector2D.dot(vec[j], unit);
                sum += Math.abs(tmp);
            }
            if(Math.abs(Vector2D.dot(dist, unit)) > sum){
                return false;
            }
        }

        return true;
    }

}
