package kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.objects;

import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimSprite extends Sprite {
    protected Rect srcRect = new Rect();    // 이미지에서 그릴 위치
    protected int frameCount;
    protected float fps;
    protected long createdOn;               // 생성시점
    protected int frameWidth, frameHeight;  // srcRect의 가로 세로
    public AnimSprite(int bitmapResId, float cx, float cy, float width, float height, float fps, int frameCount) {
        super(bitmapResId, cx, cy, width, height);
        if(fps == 0) return;
        this.fps = fps;
        int imageWidth = bitmap.getWidth();
        frameHeight = bitmap.getHeight();
        if (frameCount == 0) {
            frameWidth = frameHeight;
            this.frameCount = imageWidth / frameHeight;
        } else {
            frameWidth = imageWidth / frameCount;
            this.frameCount = frameCount;
        }
        srcRect.set(0, 0, frameWidth, frameHeight);
        createdOn = System.currentTimeMillis();
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        int frameIndex = Math.round(time * fps) % frameCount;
        srcRect.set(frameIndex * frameWidth, 0, (frameIndex + 1) * frameWidth, frameHeight);
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
