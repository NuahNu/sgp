package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene.frameTime;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.IDivisibleByTeam;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;

public class Beam extends Sprite implements IProjectile, IDivisibleByTeam {
    private static final int NUM_OF_SPRITE = 5;
    private static final float TIME_LAG = 0.2f;
    private int projectileType;
    private float damage;
    private boolean flag;
    private float maxLifeTime;
    private float currentLifeTime;
    private Rect[] rects = null;
    private Rect srcRect = new Rect();;
    private Weapon owner;
    private int team;

    public Beam(int bitmapResId, Weapon owner, float firingTime,boolean flag, float thickness) {
        super(bitmapResId,0,0,9000,thickness);

        rects = new Rect[] {
                new Rect(  0,   0,   0 + 9,  9),
                new Rect(  9,   0,   9 + 9,  9),
                new Rect(  18,  0,   18+ 9,  9),
                new Rect(  27,  0,   27+ 9,  9),
                new Rect(  36,  0,   36+ 9,  9),
        };
        this.owner = owner;
        this.flag = flag;
        this.maxLifeTime = firingTime;
        damage = owner.getDamage();
        projectileType = owner.getProjectileType();
        team = owner.getTeam();
    }

    private void selectRect() {
        int rectIndex;
        if(currentLifeTime < TIME_LAG){
            rectIndex = (int) ((currentLifeTime / TIME_LAG) * (NUM_OF_SPRITE - 2));
        }
        else if(currentLifeTime > maxLifeTime - TIME_LAG){
            rectIndex = NUM_OF_SPRITE - 2 - (int)(((maxLifeTime - currentLifeTime) / TIME_LAG) * (NUM_OF_SPRITE - 2));
        }
        else{
            rectIndex = 4;
        }
        if(rectIndex > NUM_OF_SPRITE - 1)
            rectIndex = 4;
        // -------------------

        srcRect = rects[rectIndex];
    }

    @Override
    protected void fixDstRect() {
        float half_height = height / 2;
        dstRect.set(x - 30, y - half_height, x + width - 30, y + half_height);
    }

    @Override
    public void update() {
        selectRect();
        currentLifeTime += frameTime;
        if(currentLifeTime > maxLifeTime){
            BaseScene.getTopScene().remove(MainScene.Layer.bullet, this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(flag){
            x = owner.getX();
            y = owner.getY();
            fixDstRect();
        }

        canvas.save();
//        canvas.rotate((float) Math.toDegrees( Math.toRadians(90)), x, y);       // 비행기랑 무기랑 이미지 각도가 다름.
        canvas.rotate((float) Math.toDegrees( owner.getRadian()), x, y);
//        canvas.translate((float) weaponLocation.y, (float) -weaponLocation.x);  // 90도 회전 때문에 좌표계가 다름.
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
        canvas.restore();
    }

    @Override
    public float getDamage() {
        return damage;
    }

    @Override
    public int getProjectileType() { return projectileType; }

    @Override
    public int getTeam() { return team; }

    @Override
    public void setTeam(int team) { this.team = team; }
}
