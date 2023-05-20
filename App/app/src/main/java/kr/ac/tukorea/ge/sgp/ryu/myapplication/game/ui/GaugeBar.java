package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;

public class GaugeBar extends Sprite{
    private final float ratio;
    private Rect srcRect = new Rect();

    public GaugeBar(int bitmapResId, float cx, float cy, float width, float height, float ratio){
        super(bitmapResId, cx, cy, width, height);
        this.ratio = ratio;
    }
    @Override
    protected void setSize(float width, float height) {
        dstRect.set(x, y, x + width * ratio, y + height * ratio);
    }

    public void update(float ratio) {
        srcRect.set(0, 0, Math.round(width * ratio), Math.round(height));
//        System.out.println("(width * ratio) " + (ratio));
        dstRect.set(x, y, x + width * this.ratio * ratio, y + height * this.ratio);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
