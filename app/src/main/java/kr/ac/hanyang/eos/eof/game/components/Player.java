package kr.ac.hanyang.eos.eof.game.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by space on 2017-09-07.
 */

public abstract class Player {
    // Player image
    private Bitmap playerImage;
    // image width, height
    private int w, h;

    private boolean started = false;

    // Player position
    private int x=Integer.MAX_VALUE, y=Integer.MAX_VALUE;
    // Canvas size (Player movement boundary)
    private int cw, ch;

    // Movement location
    private float lastX=-1, lastY;

    public Player(Bitmap playerImage) {
        this.playerImage = playerImage;
        w = playerImage.getWidth();
        h = playerImage.getHeight();
    }

    private final void beginStartAnimation() {

    }

    // Move player to the target position in instant
    protected final void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    final void onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                lastX = lastY = -1;
                break;
            case MotionEvent.ACTION_MOVE:
                float nx = event.getX();
                float ny = event.getY();

                x += nx - lastX;
                y += ny - lastY;

                // Check boundary
                if(x < w/2) x = w/2;
                if(y < h/2) y = h/2;
                if(x > cw-w/2) x = cw-w/2;
                if(y > ch-h/2) y = ch-h/2;

                lastX = nx;
                lastY = ny;
                break;
        }
    }

    // Draw player
    public final void draw(Canvas canvas) {
        if(!started) {
            cw = canvas.getWidth();
            ch = canvas.getHeight();
            startPlayer();
        }

        if(x == Integer.MAX_VALUE && y == Integer.MAX_VALUE) {
            x = canvas.getWidth()/2;
            y = canvas.getHeight()/2;
        }

        canvas.drawBitmap(playerImage, x-w/2, y-h/2, null);
    }

    private final void startPlayer() {
        started = true;
    }
}
