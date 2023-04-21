package kr.ac.tukorea.ge.sgp.ryu.myapplication.game;

import android.graphics.Canvas;
import android.graphics.PointF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.scene.BaseScene;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.view.Metrics;


public class ScrollBackground extends Sprite {
    private final PointF speed;
    private final float height;
    private PointF scroll = new PointF(0,0);
    public ScrollBackground(int bitmapResId, PointF speed) {
        super(bitmapResId, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        this.height = bitmap.getHeight() * Metrics.game_width / bitmap.getWidth();
        this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
        setSize(Metrics.game_width, height);    // fits in game_width
//        setSize(width, Metrics.game_height);  // fits in game_height
        this.speed = speed;
    }
    @Override
    public void update() {
        scroll.x += speed.x * BaseScene.frameTime;
        scroll.y += speed.y * BaseScene.frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        PointF curr = new PointF(0,0);
        curr.x = scroll.x % width;
        curr.y = scroll.y % height;
        if (curr.x > 0) curr.x -= width;
        if (curr.y > 0) curr.y -= height;
        while (curr.y < Metrics.game_height) {
            while (curr.x < Metrics.game_width) {
                dstRect.offsetTo(curr.x,curr.y);
                super.draw(canvas);
                curr.y += height;
            }
        }
    }
}
