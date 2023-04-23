package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.projectile;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.PhysicalObject;

public class Projectile extends PhysicalObject {

    // 무기로부터 받는다.
    protected int projectileType; // 0 = 물리, 1 = 에너지 enum?
    protected int damage;
    protected int hp;               // 충돌시 죽냐 안 죽냐 판가름용.
    //------------------------

    public Projectile(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);

    }


}
