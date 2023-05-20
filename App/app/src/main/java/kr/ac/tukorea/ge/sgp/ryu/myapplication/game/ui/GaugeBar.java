package kr.ac.tukorea.ge.sgp.ryu.myapplication.game.ui;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects.Sprite;

public class GaugeBar extends Sprite{
    private Rect srcRect = new Rect();

    public GaugeBar(int bitmapResId, float cx, float cy, float width, float height){
        super(bitmapResId, cx, cy, width, height);
    }
    @Override
    protected void setSize(float width, float height) {
        dstRect.set(x, y, x + width, y + height);
    }

    public void update(float ratio) {
        srcRect.set(0, 0, (int) (width * ratio), (int) height);
        dstRect.set(x, y, x + width * ratio, y + height);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
