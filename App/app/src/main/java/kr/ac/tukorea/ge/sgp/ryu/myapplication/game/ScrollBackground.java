package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.graphics.Canvas;
import android.graphics.PointF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics;


public class ScrollBackground extends Sprite {
    private final PointF speed;
    private PointF scroll = new PointF(0,0);
    public ScrollBackground(int bitmapResId, PointF speed, float width, float height) {
        setBitmapResource(bitmapResId);
        this.width = width;
        this.height = height;
        fixDstRect();
        this.speed = speed;
    }
    @Override
    public void update() {
        scroll.x += speed.x * BaseScene.frameTime;
        scroll.y += speed.y * BaseScene.frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        float currX = 0;
        float currY;
        currX = scroll.x % width;
        if (currX > 0) currX -= width;
        while (currX < Metrics.game_width) {
            currY = 0;
            currY = scroll.y % height;
            if (currY > 0) currY -= height;
            while (currY < Metrics.game_height) {
                dstRect.offsetTo(currX,currY);
                super.draw(canvas);
                currY += height;
            }
            currX += width;
        }
    }
}
