package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.util.Log;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.IGameObject;

public class MainScene extends BaseScene {

    private static final String TAG = MainScene.class.getSimpleName();

    public MainScene(){

    }

    @Override
    public void update(long elapsedNanos) {
        super.update(elapsedNanos);
        checkCollision();
    }

    private void checkCollision() {
        for (IGameObject o1 : objects) {
            if (!(o1 instanceof Ship)) {
                continue;
            }
            Ship enemy = (Ship) o1;
//            boolean removed = false;
            for (IGameObject o2 : objects) {
                if (!(o2 instanceof Ship)) {
                    continue;
                }
                Ship bullet = (Ship) o2;
                if (enemy != bullet && CollisionHelper.OBB(enemy, bullet)) {
                    Log.d(TAG, "Collision !!");
//                    remove(bullet);
//                    remove(enemy);
//                    removed = true;
                    break;
                }
            }
        }
    }
}