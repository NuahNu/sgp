package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class HpUI extends UI {

    private HpBar playerHp;
    private HpBar enemyHp;

    public HpUI(){
        enemyHp = new HpBar(enemy, true);
        playerHp = new HpBar(player, false);
    }
    @Override
    public void update() {
        Ship oldEnemy = enemy;
        super.update();
        if(oldEnemy != enemy){
            enemyHp.setShip(enemy);
        }

        playerHp.update();
        enemyHp.update();
    }

    @Override
    public void draw(Canvas canvas) {
        playerHp.draw(canvas);
        enemyHp.draw(canvas);
    }

    @Override
    public void setPlayer(Ship input) {
        super.setPlayer(input);
        playerHp.setShip(player);
    }
}
