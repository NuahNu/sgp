package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.camera;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class CameraSetter implements IGameObject{
    private final float MAX_SCALE = 10f;
    private final float MIN_SCALE = 1f;
    private Camera camera;
    private Ship player = null;


    public CameraSetter(Camera camera){
        this.camera = camera;
    }

    public void setPlayer(Ship input){
        // 플레이어를 정해준다.
        // 플레이어가 죽거나 변하는 등 계속 바뀔 예정이다.
        this.player = input;
    }

    @Override
    public void update() {
        // ship들을 읽어와 위치와 배율을 정한다.
        // 목표 좌표를 바꿔준다.
        float new_X = 0;
        float new_Y = 0;
        float new_scale = MAX_SCALE;

        if(player == null){
            new_X = camera.getCurrent_X();
            new_Y = camera.getCurrent_Y();
        } else{
            new_X = player.getX();
            new_Y = player.getY();
            // 플레이어와 가장 가까운 적의 위치의 중앙값을 구한다.
            // 떨어진 거리와 화면 비율을 계산해 scale을 정한다.

            // 위치를 구하면 해당 위치에서 화면의 절반 길이만큼 빼준다.
            new_X -= Metrics.game_width / (new_scale* 2);
            new_Y -= Metrics.game_height / (new_scale* 2);
        }





        camera.setTarget_X(new_X);
        camera.setTarget_Y(new_Y);
        camera.setTarget_scale(new_scale);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.restore();
    }
}
