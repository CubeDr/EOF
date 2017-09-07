package kr.ac.hanyang.eos.eof.game.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by space on 2017-09-07.
 */

public abstract class Player {
    // Player image
    private Bitmap playerImage;
    // image width, height
    private int w, h;

    // Player position
    private int x, y;

    // Set image of the player to be drawn
    protected void setPlayerImage(Bitmap playerImage) {
        this.playerImage = playerImage;
        w = playerImage.getWidth();
        h = playerImage.getHeight();
    }

    // Move player to the target position in instant
    protected void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Draw player
    private void draw(Canvas canvas) {
        canvas.drawBitmap(playerImage, x-w/2, y-h/2, null);
    }
}
