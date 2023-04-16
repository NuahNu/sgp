package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.IGameObject;

public class MainScene extends BaseScene {

    private static final String TAG = MainScene.class.getSimpleName();
    private final PlayerData playerData = new PlayerData();

    public MainScene(){

        ArrayList<Ship> shipList = playerData.getShips();

        Ship tmp = shipList.get(0);
        objects.add(tmp);
    }

    @Override
    public void update(long elapsedNanos) {
        super.update(elapsedNanos);
        checkCollision();
    }

    private void checkCollision() {
        for (IGameObject o1 : objects) {
            if (!(o1 instanceof PhysicalObject)) {
                continue;
            }
            PhysicalObject enemy = (PhysicalObject) o1;
//            boolean removed = false;
            for (IGameObject o2 : objects) {
                if (!(o2 instanceof PhysicalObject)) {
                    continue;
                }
                PhysicalObject bullet = (PhysicalObject) o2;
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
