package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.HP;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Laser;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile.Bullet;

public class Kestrel_0 extends Ship {
    private static final float kestral_width = 598;
    private static final float kestral_height = 364;

    public Kestrel_0(float cx, float cy) {
        super(R.mipmap.kestrel_0, cx, cy, kestral_width, kestral_height);
        // 기체 제원
        mass = 1;
        // PhysicalObject의 acceleration에 엔진 파워 적용.
        name = String.valueOf(R.string.kestrel_0_name);
        className = String.valueOf(R.string.cruiser);
        ownHP = new HP(R.mipmap.kestral_shields, cx, cy,800,503);
        ownHP.setHP(50,100);
    }
    @Override
    protected void initStatus() {
        enginePower = 500;
    }
    @Override
    protected void initWeapon() {
        weaponList.add(new Laser(this, true));
        weaponList.add(new Laser(this, false));
        weaponList.add(new Laser(this, true));
        weaponList.add(new Laser(this, false));
    }
    protected void initWeaponLocation() {
        weaponLocationList.add(new Vector2D(210,46));
        weaponLocationList.add(new Vector2D(210,-46));
        weaponLocationList.add(new Vector2D(46,76));
        weaponLocationList.add(new Vector2D(46,-76));
    }

    @Override
    protected void initFacility() {
    }

    @Override
    protected Gib getGib(int index) {
        int bitmap = -1;
        switch (index){
            case 0:
                bitmap = R.mipmap.kestrel_0_gib0;
                break;
            case 1:
                bitmap = R.mipmap.kestrel_0_gib1;
                break;
            case 2:
                bitmap = R.mipmap.kestrel_0_gib2;
                break;
            case 3:
                bitmap = R.mipmap.kestrel_0_gib3;
                break;
            case 4:
                bitmap = R.mipmap.kestrel_0_gib4;
                break;
            case 5:
                bitmap = R.mipmap.kestrel_0_gib5;
                break;
        }
        Gib gib = new Gib(bitmap,x,y,kestral_width,kestral_height);
        // 무기
        switch (index){
            case 4:
                gib.addWeapon(null);
                gib.addWeapon(null);
                gib.addWeapon(new Laser(gib, true));
                gib.addWeapon(new Laser(gib, false));
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(weaponLocationList.get(2));
                gib.addWeaponLocation(weaponLocationList.get(3));
                break;
            case 5:
                gib.addWeapon(new Laser(gib, true));
                gib.addWeapon(new Laser(gib, false));
                gib.addWeaponLocation(weaponLocationList.get(0));
                gib.addWeaponLocation(weaponLocationList.get(1));
                break;
        }
        // 기타 필요한 정보들.
        gib.setWeaponArmLength(weaponArmLength);
        gib.setRadian(radian);
        gib.setSpeed(speed);

        return gib;
    }

    @Override
    protected void whenDeath() {
        // gib들 생성.
        BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(5));
        BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(4));
        BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(3));
        BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(2));
        BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(1));
        BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(0));
        // 자신 삭제
        BaseScene.getTopScene().remove(MainScene.Layer.ship, this);

    }
}
