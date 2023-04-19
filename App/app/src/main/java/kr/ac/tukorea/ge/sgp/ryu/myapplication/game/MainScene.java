package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final PlayerData playerData = new PlayerData();
    public enum Layer {
        bg1, bullet, CelestialBody, ship, bg2, ui, controller, COUNT
    }

    public MainScene(){
        initLayers(Layer.COUNT);

        ArrayList<Ship> shipList = playerData.getShips();
        Ship tmp = shipList.get(0);
        add(Layer.ship, tmp);
        add(Layer.controller, new CollisionChecker());
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
//                float x = Metrics.toGameX(event.getX());
//                float y = Metrics.toGameY(event.getY());
//                fighter.setTargetPosition(x, y);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
