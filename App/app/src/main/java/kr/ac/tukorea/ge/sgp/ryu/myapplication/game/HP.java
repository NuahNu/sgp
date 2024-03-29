package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene.frameTime;

import android.graphics.Canvas;
import android.graphics.Paint;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;

public class HP extends Sprite implements IGameObject {
    private static final long RECOVER_COOL_TIME = 3 * 1000;
    private float maxHull;
    private float currentHull;
    private float maxShield;
    private float currentShield;
    private boolean hullRecovery;
    private boolean shieldRecovery;
    private float hullRecoveryRate;
    private float shieldRecoveryRate;
    private static Paint shieldPaint;
    private long lastDamagedTime;


    public HP(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);

        if(shieldPaint == null){
            shieldPaint = new Paint();
        }
        lastDamagedTime = System.currentTimeMillis();
    }

    public void setHP(int hull, int shield){
        maxHull = currentHull = hull;
        maxShield = currentShield = shield;
        setRecovery(0, shield / 10);
    }

    public void setRecovery(int hull, int shield){
        hullRecovery = hull != 0;
        shieldRecovery = shield != 0;
        hullRecoveryRate = hull;
        shieldRecoveryRate = shield;
    }

    public boolean getDamage(int damageType, float amount ){
        // 한 프레임에 데미지를 전부 기억해놨다가 update에서 전부 처리?
        lastDamagedTime = System.currentTimeMillis();
        if(damageType == 0){
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
        long now = System.currentTimeMillis();
        if(now - lastDamagedTime > RECOVER_COOL_TIME){
            recovering();
        }
        shieldPaint.setAlpha((int) (currentShield * 255 / maxShield));
        fixDstRect();
    }

    public void setPos(float x, float y){ this.x = x; this.y = y; }

    private void recovering() {
        // 피해를 받으면 회복 지연 기능 추가?
        if(hullRecovery){
            currentHull += hullRecoveryRate * frameTime;
            if(currentHull > maxHull){
                currentHull = maxHull;
            }
        }
        if(shieldRecovery){
            currentShield += shieldRecoveryRate * frameTime;
            if( currentShield > maxShield ){
                currentShield = maxShield;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, shieldPaint);
    }
    public boolean shieldExist(){ return maxShield != 0; }

    public float getHullRatio(){ return currentHull / maxHull; }
    public float getShieldRatio(){ return currentShield / maxShield; }
}
