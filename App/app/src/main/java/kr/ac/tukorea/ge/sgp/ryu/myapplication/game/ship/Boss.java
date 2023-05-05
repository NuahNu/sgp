package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.Vector2D;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Laser;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.weapon.Weapon;

public class Boss extends Ship {
    private int phase;
    private int[] bitmapResId = null;

    public Boss(float cx, float cy) {
        super(R.mipmap.boss_0, cx, cy, 458, 510);
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
    }
    private void changePhase(){
        phase++;
        // 삭제 하기 전에 get 해서 GIb에 넘겨줘야할지도?
        weaponList.remove(0);
        weaponLocationList.remove(0);
    }

    @Override
    protected void initWeapon() {
        weaponList.add(new Laser(this, true));
        weaponList.add(new Laser(this, false));
    }
    protected void initWeaponLocation() {
        weaponLocationList.add(new Vector2D(150,39));
        weaponLocationList.add(new Vector2D(150,-39));
    }

    @Override
    protected void initFacility() {
    }

    @Override
    protected void offsetArm(int i, Vector2D vec2) {
        vec2.add(weaponArmLength,0);
    }
}