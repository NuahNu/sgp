package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public interface IPartOfShip {
    public float getMass(); // { mass = 0; }
    public Ship getOwner(); // { return owner; }
    public void update(boolean powered);

}
