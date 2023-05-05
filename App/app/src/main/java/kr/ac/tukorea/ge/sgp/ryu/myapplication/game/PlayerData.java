package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Boss;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Kestrel_0;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Stealth_0;

public class PlayerData {

    private ArrayList<Ship> shipList= new ArrayList<> (); // 하나 이상 사용할 계획 없음.
    private int score = 0;
//    사용 가능한 부품의 배열.


    public PlayerData(){
//        Ship tmp = new Kestrel_0(450, 800);
//        Ship tmp = new Stealth_0(450, 800);
        Ship tmp = new Boss(450, 800);
        shipList.add(tmp);
    }
    public ArrayList<Ship> getShips(){
        return shipList;
    }
}
