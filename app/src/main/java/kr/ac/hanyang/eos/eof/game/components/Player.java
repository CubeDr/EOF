package kr.ac.hanyang.eos.eof.game.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by space on 2017-09-07.
 */

public abstract class Player {
    private final static int
        STATE_STARTING_ANIMATION = 0,
        STATE_MOVING = 1,
        STATE_ENDING_ANIMATION = 2;
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

    private int state = -1;

    // Starting animation
    private long animationStartTime;

    public Player(Bitmap playerImage) {
        this.playerImage = playerImage;
        w = playerImage.getWidth();
        h = playerImage.getHeight();
    }

    private final void beginStartAnimation() {
        state = STATE_STARTING_ANIMATION;
        animationStartTime = System.currentTimeMillis();
    }

    // Move player to the target position in instant
    protected final void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    final void onTouchEvent(MotionEvent event) {
        if(state != STATE_MOVING) return;

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

                if(lastX == -1) {
                    lastX = nx;
                    lastY = ny;
                }

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

        switch(state) {
            case STATE_STARTING_ANIMATION:
                int start = (int)(System.currentTimeMillis() - animationStartTime);

                if(start >= 1000) {
                    state = STATE_MOVING;
                    start = 1000;
                }

                x = cw/2;
                y = ch + h - start* h * 2 / 1000;

                break;
            case STATE_MOVING:
                break;
            case STATE_ENDING_ANIMATION:
                break;
        }

        canvas.drawBitmap(playerImage, x-w/2, y-h/2, null);
    }

    private final void startPlayer() {
        started = true;
        beginStartAnimation();
    }
}
