package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;

public class Weapon extends Sprite{
    // 4/19 수업에서 사용한 방법을 적용
    protected Ship owner;
    protected float mass;

    protected int projectileType; // 0 = 물리, 1 = 에너지 enum?
    protected static Rect srcRect = new Rect();

    protected boolean isFiring;
    protected float coolTime;           // 무기의 쿨타임
    protected float remainingTime;      // 남은 쿨타임
    protected float firingTime;       // 탄 발사시간.
    protected int reloadingSprite;    // 재장전 스프라이트 수
    protected int firingSprite;       // 발사 스프라이트 수

    protected Rect[] rects = null;      // 그릴 이미지의 위치.

    // 데미지
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

    public float getMass(){
        return mass;
    }
    
    public void fire(){
        if(!isFiring) {
            if(remainingTime == coolTime){
                isFiring = true;
                remainingTime = 0;
                // update한 위치에 발사체를 생성한다.
                // 발사체 생성 위치를 따로 정해지 않았다면 이상하게 보일것.
            }
        }
    }

    public void draw(Canvas canvas, Vector2D weaponLocation) {
        // update한 위치에 그린다.
        canvas.save();
        canvas.rotate((float) Math.toDegrees( Math.toRadians(90)), x, y);       // 비행기랑 무기랑 이미지 각도가 다름.
        canvas.rotate((float) Math.toDegrees( owner.getRadian()), x, y);
        canvas.translate((float) weaponLocation.y, (float) -weaponLocation.x);  // 90도 회전 때문에 좌표계가 다름.
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
        canvas.restore();
        // 이때 객체의 위치값은 이미지의 중하 에 해당.
    }

    public void update() {
        checkCoolTime();
        selectRect();
        // owner의 위치에 weaponLocation을 더해 자신의 위치 갱신.?
        x = owner.getX();
        y = owner.getY();
        fixDstRect();
        // 발사체를 생성하는 위치도 여기서 계사능ㄹ 할까?
    }

    private void selectRect() {
        int rectIndex;
        if(isFiring){
            rectIndex = (int) ((remainingTime / firingTime) * (firingSprite)) + (reloadingSprite);
        }
        else{
            rectIndex = (int) ((remainingTime / coolTime) * (reloadingSprite+1));
        }
//        if(!isRightSide){
//            rectIndex = 11 - rectIndex;
//        }

        srcRect = rects[rectIndex];
    }

    private void checkCoolTime() {
        if(isFiring){
            if (remainingTime < firingTime) {
                remainingTime += BaseScene.frameTime;
                if(remainingTime > firingTime) {
                    isFiring = false;
                    remainingTime = 0;
                }
            }
        }
        else
            if(remainingTime < coolTime) {
                remainingTime += BaseScene.frameTime;
                if(remainingTime > coolTime)
                {
                    remainingTime = coolTime;
                    // test code
                    fire();
                }
            }
//        System.out.println("remainingTime : " + remainingTime);
    }

    protected void fixDstRect() {
        float half_width = width / 2;
        dstRect.set(x - half_width, y - height, x + half_width, y);
    }

}
