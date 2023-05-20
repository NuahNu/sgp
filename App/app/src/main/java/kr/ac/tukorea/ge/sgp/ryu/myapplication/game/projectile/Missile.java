package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene.frameTime;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class Missile extends Projectile{
    float targetRadian;
    private float turnRate = (float) Math.toRadians(180);   // 속도 반비례하도록

    public Missile(int bitmapResId, float cx, float cy, Weapon owner) {
        super(bitmapResId, cx, cy, 32, 80, 1, 1, owner);
        mass = 1;
        BULLET_SPEED = 1500;
        setSpeed(owner.getOwner());
    }

    @Override
    public void update() {
        updateTargetRadian();
        updateRadian();
        updateSpeed();
        super.update();
    }

    private void updateSpeed() {
        float length = (float) speed.getLength();
        speed.set(Math.cos(radian), Math.sin(radian));
        speed.multiply(length);
    }

    private void updateRadian() {
        if(Math.abs(this.radian - targetRadian) > Math.toRadians(180)) {
            if (targetRadian > this.radian)
                this.radian += Math.toRadians(360);
            else
                this.radian -= Math.toRadians(360);
        }
        if(Math.abs(this.radian - targetRadian) > 0.001){
            if(radian > targetRadian){
                this.radian -= frameTime * turnRate;
                if(radian < targetRadian)
                    radian = targetRadian;
            }
            else{
                this.radian += frameTime * turnRate;
                if(radian > targetRadian)
                    radian = targetRadian;
            }
        }
    }

    private void updateTargetRadian() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<IGameObject> ships = scene.getObjectsAt(MainScene.Layer.ship);

        float min_dist = Float.MAX_VALUE;
        Ship min_ship = null;

        for (int s = ships.size() - 1; s >= 0; s--) {
            Ship ship = (Ship) ships.get(s);

            if(team != ship.getTeam()){
                float x_ = Math.abs((ship.getX() - x));
                float y_ =  Math.abs((ship.getY() - y));
                float dist = x_ * x_ + y_ * y_;
                if(min_dist > dist) {
                    min_dist = dist;
                    min_ship = ship;
                }
            }
        }
        if(min_ship == null){
            return;
        }
        else {
            targetRadian = -(float) ( Math.atan2(min_ship.getX() - this.x, min_ship.getY() - this.y) - Math.toRadians(90));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        radian += Math.toRadians(90);
        super.draw(canvas);
        radian -= Math.toRadians(90);
    }
}
