package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene.frameTime;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;

public class Gib extends Ship {

    private float gibTurnRate;

    public Gib(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);
        maxSpeed = 0;
        mass = 1;
        weaponPowered = false;
        // 랜덤 방향으로 발산.
        gibTurnRate = (float) (0.05 - Math.random()*0.1);
        acceleration = 5;//??
    }

    public void setSpeed(Vector2D input) {
        speed = new Vector2D(input);
        // 로직상 여기서 해야함. 뭔가 방법이 없을까?
        addImpulse(new Vector2D(100 - Math.random()*200,100 - Math.random()*200));
    }

    public void setRadian(float input) { radian = input; }

    public void setWeaponArmLength(float input) { weaponArmLength = input; }

    public void addWeapon(Weapon w){ weaponList.add(w); }

    public void addWeaponLocation(Vector2D wl){ weaponLocationList.add(wl); }

    @Override
    public void update() {
        super.update();
        radian+= gibTurnRate*frameTime;
        // 속도가 0이 되면 삭제.
        if (speed.getLength() < 1){
            BaseScene.getTopScene().remove(MainScene.Layer.gib, this);
        }
    }
}
