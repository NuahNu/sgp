package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.IDivisibleByTeam;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.IPartOfShip;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class Weapon extends Sprite implements IPartOfShip, IDivisibleByTeam {
    // 4/19 수업에서 사용한 방법을 적용
    protected Ship owner;
    protected float mass;

    protected int projectileType; // 0 = 물리, 1 = 에너지 enum?
    protected Rect srcRect = new Rect();
    protected int maxBulletStock;          // 저장 가능한 탄환 수
    protected int currentBulletStock;      // 현재 준비된(발사 가능한) 탄환 수
    protected boolean isFiring;
    protected float coolTime;           // 무기의 쿨타임
    protected float remainingTime;      // 남은 쿨타임
    protected float firingTime;       // 탄 발사시간.
    protected int reloadingSprite;    // 재장전 스프라이트 수
    protected int firingSprite;       // 발사 스프라이트 수

    protected Rect[] rects = null;      // 그릴 이미지의 위치.

    // 데미지
    protected float damage;
    protected float bulletSpeed = 3000;
    protected float bulletLifeTime = 2;
    private float radian;
    private int team;

    // 사거리
    // 투사체 옵션
    // 발사체 속도
    // 발사 오차
    // 발사체 체력
    public Weapon(int bitmapResId, float cx, float cy, float width, float height, Ship inputOwner) {
        super(bitmapResId, cx, cy, width, height);

        // 자식 클라스에서 결정.
        //-----------------------
        mass = 0;
        //-----------------------
        this.owner = inputOwner;
        isFiring = false;
        remainingTime = 0;
    }

    @Override
    public float getMass(){ return mass; }

    public void fire(){
        if(!isFiring) {
            if(currentBulletStock > 0){
                currentBulletStock--;
                isFiring = true;
                remainingTime = 0;
                // update한 위치에 발사체를 생성한다.
                makeProjectile();
            }
        }
    }

    @Override
    public Ship getOwner() {
        return owner;
    }

    protected void makeProjectile() {}

    public void draw(Canvas canvas, Vector2D weaponLocation) {
        // 위치 조정
        x = (float) (owner.getX() + Math.cos(owner.getRadian())*(weaponLocation.x+ height) - Math.sin(owner.getRadian())*weaponLocation.y);
        y = (float) (owner.getY()+ Math.sin(owner.getRadian())*(weaponLocation.x+ height) + Math.cos(owner.getRadian())*weaponLocation.y);
        fixDstRect();

        // update한 위치에 그린다.
        canvas.save();
        canvas.rotate((float) Math.toDegrees( Math.toRadians(90)), x, y);       // 비행기랑 무기랑 이미지 각도가 다름.
        canvas.rotate((float) Math.toDegrees( owner.getRadian()), x, y);
        this.radian = owner.getRadian();
//        canvas.translate((float) weaponLocation.y, (float) -weaponLocation.x);  // 90도 회전 때문에 좌표계가 다름.
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
        canvas.restore();
        // 이때 객체의 위치값은 이미지의 중하 에 해당.
    }

    @Override
    public void update(boolean powered) {
        checkCoolTime(powered);
        selectRect();
    }

    private void selectRect() {
        int rectIndex;
        if(isFiring){
            rectIndex = (int) ((remainingTime / firingTime) * (firingSprite - 1)) + (reloadingSprite);
        }
        else{ // 충전 끝나면 색 바뀌라고 이렇게 해놨는데 아니다 싶으면 reloadingSprite-1
            rectIndex = (int) ((remainingTime / coolTime) * (reloadingSprite));
        }
//        if(!isRightSide){
//            rectIndex = 11 - rectIndex;
//        }
        // 왜 버그가 나는지 모르겟네
        if(rectIndex > reloadingSprite+firingSprite)
            rectIndex = reloadingSprite+firingSprite-1;
        // -------------------

        srcRect = rects[rectIndex];
    }

    private void checkCoolTime(boolean weaponPowered) {
        if(!weaponPowered) return;
        if(isFiring){
            if (remainingTime < firingTime) {
                remainingTime += BaseScene.frameTime;
                if(remainingTime > firingTime) {
                    isFiring = false;
                    remainingTime = 0;
                    fire();
                }
            }
        }
        else
            if(remainingTime < coolTime) {
                remainingTime += BaseScene.frameTime;
                if(remainingTime > coolTime) {
                    remainingTime -= coolTime;
                    if(updateBulletStock()){
                        remainingTime = coolTime;
                        // test code
                        fire();
                        //-------
                    }
                }
            }
//        System.out.println("remainingTime : " + remainingTime);
    }

    private boolean updateBulletStock() {
        if(currentBulletStock < maxBulletStock){
            currentBulletStock++;
//            System.out.println("currentBulletStock : " + currentBulletStock);
        }
        if (currentBulletStock == maxBulletStock) {
            return true;
        }
        return false;
    }

    protected void fixDstRect() {
        float half_width = width / 2;
        dstRect.set(x - half_width, y, x + half_width, y + height);
    }

    public float getDamage(){ return damage; }

    public float getRadian() { return radian; }

    public int getProjectileType() { return projectileType; }

    public float getRange(){ return bulletLifeTime * bulletSpeed; }

    public float getBulletLifeTime() { return bulletLifeTime;  }

    public float getBulletSpeed() { return bulletSpeed; }

    @Override
    public int getTeam() { return team; }

    @Override
    public void setTeam(int team) { this.team = team; }
}
