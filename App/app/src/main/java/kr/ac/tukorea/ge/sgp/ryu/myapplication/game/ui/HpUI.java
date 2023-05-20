package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class HpUI implements IGameObject {

    private HpBar playerHp;

    public HpUI(Ship player){
        playerHp = new HpBar(player);
    }
    @Override
    public void update() {
        playerHp.update();
    }

    @Override
    public void draw(Canvas canvas) {
        playerHp.draw(canvas);
    }
}
