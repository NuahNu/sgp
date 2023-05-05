package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene.frameTime;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IGameObject;

public class HP implements IGameObject {
    private int maxHull;
    private int currentHull;
    private int maxShield;
    private int currentShield;
    private boolean hullRecovery;
    private boolean shieldRecovery;
    private int hullRecoveryRate;
    private int shieldRecoveryRate;

    public HP(){
        setHP(100,100);
        setRecovery(0,10);
    }

    public void setHP(int hull, int shield){
        maxHull = currentHull = hull;
        maxShield = currentShield = shield;
        hullRecoveryRate = hull / 10;
        shieldRecoveryRate = shield / 10;
    }

    public void setRecovery(int hull, int shield){
        hullRecovery = hull != 0;
        shieldRecovery = hull != 0;
        hullRecoveryRate = hull;
        shieldRecoveryRate = shield;
    }

    public boolean getDamage(boolean damageType, int amount ){
        // 한 프레임에 데미지를 전부 기억해놨다가 update에서 전부 처리?
        if(damageType){
            // 물리
            currentHull -= amount * 0.8;
            currentShield -= amount * 0.2;
        }else {
            // 에너지
            currentHull -= amount * 0.2;
            currentShield -= amount * 0.8;
        }
        if(currentShield < 0){
            currentHull += currentShield;
            currentShield = 0;
        }
        if(currentHull < 0){
            currentHull = 0;
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        recovering();
    }

    private void recovering() {
        // 피해를 받으면 회복 지연 기능 추가?
        if(hullRecovery){
            currentHull += hullRecoveryRate * frameTime;
        }
        if(shieldRecovery){
            currentShield += shieldRecoveryRate * frameTime;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        // UI를 통해 구현. 아마도?
    }
}
