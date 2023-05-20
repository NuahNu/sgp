package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics.game_height;
import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics.game_width;

import android.graphics.PointF;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.camera.Camera;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.camera.CameraSetter;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Kestrel_0;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui.HpBar;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui.HpUI;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
//    private final PlayerData playerData = new PlayerData();
    private static Ship player;
    private HpUI hpUI;
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
        PlayerData.initShipList();
        ArrayList<Ship> shipList = PlayerData.getShips();
        player = shipList.get(2); // K, S, B
        add(Layer.ship, player);
//        player = shipList.get(1); // K, S, B
//        add(Layer.ship, player);
//        player = shipList.get(0); // K, S, B
//        add(Layer.ship, player);
        Ship enemy = new Kestrel_0(game_width*2/5, game_height/2);
        enemy.setTeam(1);
        add(Layer.ship, enemy);

//        gib,
//        bg2,
        cameraSetter = new CameraSetter(camera);
        cameraSetter.setPlayer(player);
        add(Layer.bg2,cameraSetter);

//        ui,
        hpUI = new HpUI(player);
        add(Layer.ui, hpUI);
//        touch,
        add(Layer.touch, new AnalogStick(1000.0f, 7000.0f, 3000.0f, new AnalogStick.Callback() {
            @Override
            public boolean onTouch(AnalogStick.Action action, float radian) {
//                Log.d(TAG, "AnalogStick. radian = "+ radian);
                player.radianInput(action, radian);
                return true;
            }
        }));
//        controller,
        add(Layer.controller, new CollisionChecker());

//        COUNT
    }
    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
    public Ship getPlayer(){ return player; }
}
