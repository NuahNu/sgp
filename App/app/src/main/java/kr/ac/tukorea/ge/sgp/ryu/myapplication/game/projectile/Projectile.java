package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile;


import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene.frameTime;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.IDivisibleByTeam;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class Projectile extends AnimSprite implements IBoxCollidable , IProjectile, IDivisibleByTeam {
    protected float lifeTime = 3;
    // 무기로부터 받는다.
    protected int projectileType; // 0 = 물리, 1 = 에너지 enum?
    protected float damage;
    protected int hp;               // 충돌시 죽냐 안 죽냐 판가름용.
    protected float radian;           // 각
    //------------------------
    protected RectF collisionRect = new RectF();
    protected float mass;             // 질량의 합.
    protected Vector2D speed = new Vector2D(0,0);         // 속도.
    protected float BULLET_SPEED;
    private int team;

    public Projectile(int bitmapResId, float cx, float cy, float width, float height, float fps, int frameCount, Weapon owner) {
        super(bitmapResId, cx, cy, width, height, fps, frameCount);
        radian = owner.getOwner().getRadian();
        projectileType = owner.getProjectileType();
        team = owner.getTeam();
        damage = owner.getDamage();
    }

    protected void setSpeed(Ship owner) {
        this.speed = new Vector2D(owner.getSpeed());
        speed.add(BULLET_SPEED*Math.cos(radian),BULLET_SPEED*Math.sin(radian));
    }

    @Override
    public void update() {  // 미사일의 update에서는 먼저 radian을 변경하고 super를 불러야함. 아마도?
        super.update();
        UpdateLocation();
        fixDstRect();           // 위치 갱신
        fixCollisionRect();     // 충돌박스 갱신. dstRect를 기반으로 하므로 먼저 불려야함.
        lifeTime -= frameTime;
        if(lifeTime < 0){
            BaseScene.getTopScene().remove(MainScene.Layer.bullet, this);
        }
    }

    protected void fixCollisionRect() {
        collisionRect.set(dstRect);
    }

    private void UpdateLocation() {
        Vector2D delta = new Vector2D(speed);
        delta.multiply(frameTime);
        x += delta.x;
        y += delta.y;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate((float) Math.toDegrees(radian), x, y);
        super.draw(canvas);
        canvas.restore();
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public float getRadian() {
        return radian;
    }

    @Override
    public Vector2D getImpulse(){
        Vector2D value = new Vector2D(speed);
        value.multiply(mass);
        return value;
    }

    @Override
    public void addImpulse(Vector2D impulse){
//        impulse.multiply(frameTime);  // 버그나면 이게 문제
//        생각해보니 위치 갱신에서 speed에 frameTime을 곱하니까
//        그냥 더하는게 맞는듯

        speed.multiply(mass);
        speed.add(impulse);
        speed.multiply(1/ mass);
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
