package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.facility;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.part_of_ship.IPartOfShip;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class Facility implements IPartOfShip {
    private float mass;
    protected Ship owner;

    public Facility(){
        mass = 0;
    }

    @Override
    public float getMass(){ return mass; }

    @Override
    public Ship getOwner() { return owner; }

    @Override
    public void update(boolean powered) {

    }

}
