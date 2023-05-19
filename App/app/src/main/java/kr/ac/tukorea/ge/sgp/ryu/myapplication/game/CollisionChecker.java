package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile.IProjectile;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile.Projectile;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;


public class CollisionChecker implements IGameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<IGameObject> ships = scene.getObjectsAt(MainScene.Layer.ship);
        ArrayList<IGameObject> projectiles = scene.getObjectsAt(MainScene.Layer.bullet);

        // 함선끼리 충돌
        for (int si_1 = ships.size() - 1; si_1 >= 0; si_1--) {
            Ship ship_1 = (Ship) ships.get(si_1);
            for (int si_2 = ships.size() - 1; si_2 >= 0; si_2--) {
                Ship ship_2 = (Ship) ships.get(si_2);
                if (ship_1 != ship_2 && CollisionHelper.OBB(ship_1, ship_2)) {
                    Log.d(TAG, "Collision !!");
                    break;
                }
            }
        }
        // 탄, 함선 충돌
        for (int s = ships.size() - 1; s >= 0; s--) {
            Ship ship = (Ship) ships.get(s);
            for (int pj = projectiles.size() - 1; pj >= 0; pj--) {
                Projectile projectile = (Projectile) projectiles.get(pj);
                if (ship.getTeam() != projectile.getTeam() && CollisionHelper.OBB(ship, projectile)) {
                    Log.d(TAG, "Kaboom !!"+ship.getClass().getSimpleName()+", "+projectile.getClass().getSimpleName());
//                    Log.d(TAG, "Kaboom !!"+ship.getTeam()+", "+projectile.getTeam());
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
