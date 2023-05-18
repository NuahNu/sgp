package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics.game_height;
import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics.game_width;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Boss;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Kestrel_0;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Stealth_0;

public class PlayerData {

    private static ArrayList<Ship> shipList= null; // 하나 이상 사용할 계획 없음.
    private static int score = 0;
//    사용 가능한 부품의 배열.


    public PlayerData(){    // 필요한가
        initShipList();
    }

    private static void initShipList(){
        if(shipList == null){
            shipList= new ArrayList<> ();

            Ship tmp = new Kestrel_0(game_width/2, game_height/2);
            shipList.add(tmp);
            tmp = new Stealth_0(game_width/2, game_height/2);
            shipList.add(tmp);
            tmp = new Boss(game_width/2, game_height/2);
            shipList.add(tmp);
        }
    }

    private static void resetShipList(){
        shipList= new ArrayList<> ();

        Ship tmp = new Kestrel_0(game_width/2, game_height/2);
        shipList.add(tmp);
        tmp = new Stealth_0(game_width/2, game_height/2);
        shipList.add(tmp);
        tmp = new Boss(game_width/2, game_height/2);
        shipList.add(tmp);
    }

    private static void resetScore(){
        score = 0;
    }

    public static void reset(){
        resetShipList();
        resetScore();
    }
    public static ArrayList<Ship> getShips(){
        return shipList;
    }
}
