package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class UI implements IGameObject{

    protected Ship player;
    protected Ship enemy;

    protected void selectEnemy() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<IGameObject> ships = scene.getObjectsAt(MainScene.Layer.ship);

        float min_dist = Float.MAX_VALUE;
        enemy = null;

        for(IGameObject s : ships){
            if (s != player){
                Ship ship = (Ship) s;

                float x_ = Math.abs((ship.getX() - player.getX()));
                float y_ =  Math.abs((ship.getY() - player.getY()));
                float dist = x_ * x_ + y_ * y_;
                if(min_dist > dist) {
                    min_dist = dist;
                    enemy = ship;
                }
            }
        }
    }

    @Override
    public void update() {
        selectEnemy();
    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void setPlayer(Ship input){
        // 플레이어를 정해준다.
        // 플레이어가 죽거나 변하는 등 계속 바뀔 예정이다.
        this.player = input;
    }
}
