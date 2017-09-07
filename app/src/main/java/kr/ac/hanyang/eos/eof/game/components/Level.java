package kr.ac.hanyang.eos.eof.game.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by space on 2017-09-07.
 */

public abstract class Level {
    public int level;
    public String[] developers;

    private Bitmap background;

    public Level(int difficulty) {
        this(difficulty, new String[]{});
    }

    public Level(int difficulty, String[] developers) {
        this.level = difficulty;
        this.developers = developers;
    }

    protected final void setBackgroundImage(Bitmap background) {
        this.background = background;
        Log.v("Level", "Background set to " + background.toString());
    }

    final void onTouchEvent(MotionEvent event) {

    }

    public void draw(Canvas canvas) {
        //if(background != null)
            canvas.drawBitmap(background, 0, 0, null);
    }

    public abstract void startLevel();
}
