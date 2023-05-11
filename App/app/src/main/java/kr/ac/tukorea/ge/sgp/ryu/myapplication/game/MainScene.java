package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics.game_height;
import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics.game_width;

import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Button;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.camera.Camera;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.camera.CameraSetter;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Kestrel_0;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final PlayerData playerData = new PlayerData();
    private Ship player;
    private Camera camera;
    private CameraSetter cameraSetter;

    public enum Layer {
        bg1, CelestialBody, bullet, ship, gib, bg2, ui, touch, controller, COUNT
    }
//    bg1
//      이 레이어의 0번은 camera 여야함.
//      우주배경
//    bg2
//      이 레이어의 마지막은 cameraSetter 여야함.
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
//        bg1,
        camera = new Camera();
        add(Layer.bg1,camera);
        int rectSize = 100;
        add(Layer.bg1, new ScrollBackground(R.mipmap.background_20,new PointF(0,0),12 * rectSize,12 * rectSize));

//        CelestialBody,
//        bullet,
//        ship,
        //---- 이 작업을 controller의 playerData가 하게 만든다?
        // playerData는 모든 Scene에서 접근 가능해야함.
        // BaseScene에 있는게 맞지 않을까?
        ArrayList<Ship> shipList = playerData.getShips();
        player = shipList.get(2); // K, S, B
        add(Layer.ship, player);
//        player = shipList.get(1); // K, S, B
//        add(Layer.ship, player);
//        player = shipList.get(0); // K, S, B
//        add(Layer.ship, player);
        add(Layer.ship, new Kestrel_0(game_width/4, game_height/2));

//        gib,
//        bg2,
        cameraSetter = new CameraSetter(camera);
        cameraSetter.setPlayer(player);
        add(Layer.bg2,cameraSetter);

//        ui,
//        touch,
        add(Layer.touch, new AnalogStick(1000.0f, 7000.0f, 3000.0f, new AnalogStick.Callback() {
            @Override
            public boolean onTouch(float radian) {
                Log.d(TAG, "AnalogStick. radian = "+ radian);
//                player.slide(action == Button.Action.pressed);
                return true;
            }
        }));
//        controller,
        add(Layer.controller, new CollisionChecker());

//        COUNT
    }
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
//                스틱을 터치해서 스틱의 각을 정한다.
//                스틱과 플레이어의 radian이 같으면 가속을 한다.
//                다르면 플레이어의 각을 스틱의 각으로 회전한다.
//                무기별 발사 버튼을 눌러 탄 발사.
//                float x = Metrics.toGameX(event.getX());
//                float y = Metrics.toGameY(event.getY());
//                fighter.setTargetPosition(x, y);
//                return true;
//        }
//        return super.onTouchEvent(event);
//    }
    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
}
