package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;

public class GaugeBar extends Sprite{
    private final float ratio;
    private boolean isRight;
    private Rect srcRect = new Rect();

    public GaugeBar(int bitmapResId, float cx, float cy, float width, float height, float ratio, boolean is_right){
        super(bitmapResId, cx, cy, width, height);
        this.ratio = ratio;
        this.isRight = is_right;
    }
    @Override
    protected void setSize(float width, float height) {
        if(isRight){
            dstRect.set(x - width * ratio, y, x, y + height * ratio);
        }else {
            dstRect.set(x, y, x + width * ratio, y + height * ratio);
        }
    }

    public void update(float ratio) {
        if(isRight){
            srcRect.set(Math.round(width * (1 - ratio)), 0, (int) width, Math.round(height));
            dstRect.set(x - width * this.ratio * ratio, y, x, y + height * this.ratio);
        }else {
            srcRect.set(0, 0, Math.round(width * ratio), Math.round(height));
            dstRect.set(x, y, x + width * this.ratio * ratio, y + height * this.ratio);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
