package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import java.util.ArrayList;

public class PlayerData {

    private ArrayList<Ship> shipList= new ArrayList<> (); // 하나 이상 사용할 계획 없음.
    private int score = 0;
//    사용 가능한 부품의 배열.


    public PlayerData(){
        Ship tmp = new Kestrel_0(3, 3);
        shipList.add(tmp);
    }
    public ArrayList<Ship> getShips(){
        return shipList;
    }
}
