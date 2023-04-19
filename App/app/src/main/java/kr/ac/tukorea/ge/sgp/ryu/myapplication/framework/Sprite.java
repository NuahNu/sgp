package kr.ac.tukorea.ge.sgp.ryu.myapplication.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Sprite implements IGameObject {
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, width, height;
    public Sprite(int bitmapResId, float cx, float cy, float width, float height) {
        // 위치
        this.x = cx;
        this.y = cy;
        // 크기
        this.width = width;
        this.height = height;
        if (bitmapResId != 0) {
            setBitmapResource(bitmapResId);
        }
        fixDstRect();
    }

    protected void setBitmapResource(int bitmapResId) {
        bitmap = BitmapPool.get(bitmapResId);
    }

    protected void fixDstRect() {
        float half_width = width / 2;
        float half_height = height / 2;
        dstRect.set(x - half_width, y - half_height, x + half_width, y + half_height);
    }

    public  float getX(){return x;}
    public  float getY(){return y;}

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
