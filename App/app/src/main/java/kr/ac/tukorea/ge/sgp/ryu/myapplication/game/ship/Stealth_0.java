package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.HP;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Laser;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;

public class Stealth_0 extends Ship {
    private static final float stealth_width = 598;
    private static final float stealth_height = 364;

    public Stealth_0(float cx, float cy) {
        super(R.mipmap.stealth_0, cx, cy, stealth_width, stealth_height);
        // 기체 제원
        maxSpeed = 5;
        mass = 1;
        enginePower = 5;
        name = String.valueOf(R.string.stealth_0_name);
        className = String.valueOf(R.string.corvette);
        ownHP = new HP(R.mipmap.kestral_shields, cx, cy,800,562);
        ownHP.setHP(30, 200);
    }

    @Override
    protected void initWeapon() {
        weaponList.add(new Laser(this, true));
        weaponList.add(new Laser(this, false));
        weaponList.add(null);
        weaponList.add(new Laser(this, false));
    }
    protected void initWeaponLocation() {
        weaponLocationList.add(new Vector2D(150,37));
        weaponLocationList.add(new Vector2D(150,-37));
        weaponLocationList.add(null);
        weaponLocationList.add(new Vector2D(-65,-108));
    }

    @Override
    protected void initFacility() {
    }

    @Override
    protected Gib getGib(int index) {
        int bitmap = -1;
        switch (index){
            case 0:
                bitmap = R.mipmap.stealth_0_gib0;
                break;
            case 1:
                bitmap = R.mipmap.stealth_0_gib1;
                break;
            case 2:
                bitmap = R.mipmap.stealth_0_gib2;
                break;
            case 3:
                bitmap = R.mipmap.stealth_0_gib3;
                break;
            case 4:
                bitmap = R.mipmap.stealth_0_gib4;
                break;
            case 5:
                bitmap = R.mipmap.stealth_0_gib5;
                break;
        }
        Gib gib = new Gib(bitmap,x,y,stealth_width,stealth_height);
        // 무기
        switch (index){
            case 0:
                gib.addWeapon(null);
                gib.addWeapon(null);
                gib.addWeapon(null);
                gib.addWeapon(new Laser(gib, false));
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(weaponLocationList.get(3));
                break;
            case 1:
                gib.addWeapon(new Laser(gib, true));
                gib.addWeaponLocation(weaponLocationList.get(0));
                break;
            case 3:
                gib.addWeapon(null);
                gib.addWeapon(new Laser(gib, false));
                gib.addWeaponLocation(null);
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
