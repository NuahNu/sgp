package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final PlayerData playerData = new PlayerData();
    public enum Layer {
        bg1, bullet, CelestialBody, ship, bg2, ui, controller, COUNT
    }
//    bg1
//      우주배경
//    bg2
//      안개
//    ui
//      움직임 스틱, 무기 발사.         // 전력시스템
//    cnotroller
//      playerDate? 이것도 여기 넣도록 개조.
//          1. 조종중인 비행기가 죽으면 목록에서 다음 비행기를 가져오도록.
//          2. 다 죽으면 game over
//      AIcontroller
//            AI 조종을 이걸로.

    public MainScene(){
        initLayers(Layer.COUNT);

        //---- 이 작업을 controller의 playerData가 하게 만든다?
        // playerData는 모든 Scene에서 접근 가능해야함.
        // BaseScene에 있는게 맞지 않을까?
        ArrayList<Ship> shipList = playerData.getShips();
        Ship tmp = shipList.get(0);
        add(Layer.ship, tmp);
        //----
        add(Layer.controller, new CollisionChecker());
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
//                스틱을 터치해서 스틱의 각을 정한다.
//                스틱과 플레이어의 radian이 같으면 가속을 한다.
//                다르면 플레이어의 각을 스틱의 각으로 회전한다.
//                무기별 발사 버튼을 눌러 탄 발사.
//                float x = Metrics.toGameX(event.getX());
//                float y = Metrics.toGameY(event.getY());
//                fighter.setTargetPosition(x, y);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
