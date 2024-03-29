package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ship.Ship;

public class CameraSetter extends UI {
    private final float MAX_SCALE = 10f;
    private final float SCALE_LIMIT = 3f;// 휴대폰마다 화면 크기가 다르니 이건 좀 수정해야함.
    private final float MIN_SCALE = 1f;
    private Camera camera;


    public CameraSetter(Camera camera){
        this.camera = camera;
    }

    @Override
    public void update() {
        super.update();
        // ship들을 읽어와 위치와 배율을 정한다.
        // 목표 좌표를 바꿔준다.
        float new_X;
        float new_Y;
        float new_scale;

        if(player == null){
//            플레이어가 없다 == 죽었다.  둘 다 없는 경우도 포함됨.
//            new_X = camera.getCurrent_X();
//            new_Y = camera.getCurrent_Y();
//
//            camera.setTarget_X(new_X);
//            camera.setTarget_Y(new_Y);
//            camera.setTarget_scale(new_scale);
            return;

        } else if (enemy == null){
//            선택된 적이 없다. == 모든 적이 죽었다.
            new_X = player.getX();
            new_Y = player.getY();
            new_scale = MAX_SCALE;
        } else{
//            둘 다 존재한다.
//            플레이어와 가장 가까운 적의 위치의 중앙값을 구한다.
            new_X = (player.getX() + enemy.getX()) / 2;
            new_Y = (player.getY() + enemy.getY()) / 2;

            float cameraPadding = Math.max(player.getBiggerSize(), enemy.getBiggerSize());

//            떨어진 거리와 화면 비율을 계산해 scale을 정한다.
            float dist_X = Math.abs(player.getX() - enemy.getX()) + cameraPadding;
            float dist_Y = Math.abs(player.getY() - enemy.getY()) + cameraPadding;
            float dist = Math.max(dist_X, dist_Y / Metrics.game_height * Metrics.game_width);// 비율 적용.

//            new_scale = -((Metrics.game_width)*1.0f / (MAX_SCALE - MIN_SCALE)) / dist + MAX_SCALE;
            new_scale = Metrics.game_width / dist;

            //new_scale = MIN_SCALE;

            // 너무 작으면 안보인다. 제한을 걸어야 한다.
            if(new_scale < SCALE_LIMIT){
                new_scale = SCALE_LIMIT;
                // 플레이어가 보이도록 x,y값을 수정해야한다.
                if(dist_X > Metrics.game_width / SCALE_LIMIT) {
                    if (player.getX() > enemy.getX()){
                        new_X = player.getX() - (Metrics.game_width / SCALE_LIMIT) / 2 + cameraPadding / 2;
                    }else{
                        new_X = player.getX() + (Metrics.game_width / SCALE_LIMIT) / 2 - cameraPadding / 2;
                    }
                }
                if(dist_Y > Metrics.game_height / SCALE_LIMIT) {
                    if(player.getY() > enemy.getY()){
                        new_Y = player.getY() - (Metrics.game_height / SCALE_LIMIT) / 2 + cameraPadding / 2;
                    }else{
                        new_Y = player.getY() + (Metrics.game_height / SCALE_LIMIT) / 2 - cameraPadding / 2;
                    }
                }
            }else if(new_scale > MAX_SCALE ) {  // 반대의 경우도 제한해야한다.
                new_scale = MAX_SCALE;
            }
//            System.out.println("dist        "+dist);
//
        }
//        System.out.println("new_scale   "+new_scale);

        // 위치를 구하면 해당 위치에서 화면의 절반 길이만큼 빼준다.
        new_X -= Metrics.game_width / (new_scale* 2);
        new_Y -= Metrics.game_height / (new_scale* 2);

        camera.setTarget_X(new_X);
        camera.setTarget_Y(new_Y);
        camera.setTarget_scale(new_scale);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.restore();
    }
}
