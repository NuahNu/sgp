package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.HP;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Boss_Beam;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Boss_Ion;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Boss_Laser;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Boss_Missile;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Laser;

public class Boss extends Ship {
    private int phase;
    private int[] bitmapResId = null;
    private static final float boss_width = 458;
    private static final float boss_height = 510;

    public Boss(float cx, float cy) {
        super(R.mipmap.boss_0, cx, cy, boss_width, boss_height);
        bitmapResId = new int[]{
                R.mipmap.boss_0,
                R.mipmap.boss_1,
                R.mipmap.boss_2,
        };
        phase = 0;
        // 기체 제원
        maxSpeed = 5;
        mass = 1;
        enginePower = 5;
        name = String.valueOf(R.string.boss_name);
        className = String.valueOf(R.string.flagShip);
        ownHP = new HP(R.mipmap.kestral_shields, cx, cy,610,680);
        ownHP.setHP(100,300);
        // 보스용 실드 이미지 구해야함.
    }
    private void changePhase(){
        if(phase > 2) return;
        weaponList.set(phase, null);
        weaponLocationList.set(phase, null);
        phase++;
        if(phase > 2) return;
        setBitmapResource(bitmapResId[phase]);
        ownHP.setHP(100,300);
    }

    @Override
    protected void initWeapon() {
        weaponList.add(new Boss_Ion(this));
        weaponList.add(new Boss_Beam(this));
        weaponList.add(new Boss_Laser(this));
        weaponList.add(new Boss_Missile(this));
    }
    protected void initWeaponLocation() {
        weaponLocationList.add(new Vector2D(91 - 14,-177));
        weaponLocationList.add(new Vector2D(91 - 14,177));
        weaponLocationList.add(new Vector2D(137 - 14,-90));
        weaponLocationList.add(new Vector2D(137 - 14,90));
    }

    @Override
    protected void initFacility() {
    }

    @Override
    protected void offsetArm(int i, Vector2D vec2) {
        vec2.add(weaponArmLength,0);
    }

    @Override
    protected Gib getGib(int index) {
        int bitmap = -1;
        switch (index){
            case 00:
                bitmap = R.mipmap.boss_0_gib0;
                break;
            case 01:
                bitmap = R.mipmap.boss_0_gib1;
                break;
            case 02:
                bitmap = R.mipmap.boss_0_gib2;
                break;
            case 10:
                bitmap = R.mipmap.boss_1_gib0;
                break;
            case 11:
                bitmap = R.mipmap.boss_1_gib1;
                break;
            case 12:
                bitmap = R.mipmap.boss_1_gib2;
                break;
            case 20:
                bitmap = R.mipmap.boss_2_gib0;
                break;
            case 21:
                bitmap = R.mipmap.boss_2_gib1;
                break;
            case 22:
                bitmap = R.mipmap.boss_2_gib2;
                break;
            case 23:
                bitmap = R.mipmap.boss_2_gib3;
                break;
        }
        Gib gib = new Gib(bitmap,x,y,boss_width,boss_height);
        // 무기
        switch (index){
            case 02:
                gib.addWeapon(new Boss_Ion(gib));
                gib.addWeaponLocation(weaponLocationList.get(0));
                break;
            case 12:
                gib.addWeapon(null);
                gib.addWeapon(new Boss_Beam(gib));
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(weaponLocationList.get(1));
                break;
            case 21:
                gib.addWeapon(null);
                gib.addWeapon(null);
                gib.addWeapon(new Boss_Laser(gib));
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(weaponLocationList.get(2));
                break;
            case 22:
                gib.addWeapon(null);
                gib.addWeapon(null);
                gib.addWeapon(null);
                gib.addWeapon(new Boss_Missile(gib));
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(null);
                gib.addWeaponLocation(weaponLocationList.get(3));
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
        switch (phase){
            case 0:
                BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(02));
                BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(01));
                BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(00));
//                changePhase();
                break;
            case 1:
                BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(12));
                BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(11));
                BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(10));
//                changePhase();
                break;
            case 2:
                BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(23));
                BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(22));
                BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(21));
                BaseScene.getTopScene().add(MainScene.Layer.gib, getGib(20));
                BaseScene.getTopScene().remove(MainScene.Layer.ship, this);
                break;
        }
        changePhase();
    }
}