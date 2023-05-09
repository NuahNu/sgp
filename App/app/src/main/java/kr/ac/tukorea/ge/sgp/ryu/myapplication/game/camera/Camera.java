package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.camera;

import static kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene.frameTime;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics;

public class Camera implements IGameObject {
    private float current_X, current_Y, target_X, target_Y;
    private float current_scale, target_scale;
    private final float EPSILON = 0.1f;
    private final float RATIO = 1f;

    public Camera(){
        // 정 중앙의 좌표. 스케일의 최대값이 10.
        current_scale = target_scale = 10;
        target_X = current_X = Metrics.game_width / 2 - Metrics.game_width / (current_scale* 2);
        target_Y = current_Y = Metrics.game_height / 2 -  Metrics.game_height / (current_scale* 2);
    }

    @Override
    public void update() {
        // 현재 좌표를 목표 좌표로 바꿔준다.
        // 위치와 배율을 바꾼다.
        if(Math.abs(current_X - target_X) > EPSILON){
            current_X -= (current_X - target_X);// * frameTime * RATIO;
//            System.out.println(current_X - target_X);
        }
        if(Math.abs(current_Y - target_Y) > EPSILON){
            current_Y -= (current_Y - target_Y);// * frameTime * RATIO;
        }
        if(Math.abs(current_scale - target_scale) > EPSILON){
            current_scale -= (current_scale - target_scale);// * frameTime * RATIO;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.scale(current_scale, current_scale);
        canvas.translate(-current_X, -current_Y);
    }

    public float getCurrent_X(){ return current_X; }
    public float getCurrent_Y(){ return current_Y; }
    public float getCurrent_scale(){ return current_scale; }
    public void setTarget_X(float input){ target_X = input; }
    public void setTarget_Y(float input){ target_Y = input; }
    public void setTarget_scale(float input){ target_scale = input; }
}
