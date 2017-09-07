package kr.ac.hanyang.eos.eof.game.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import kr.ac.hanyang.eos.eof.game.GameView;

/**
 * Created by space on 2017-09-07.
 */

public abstract class Level {
    public int level;
    public String[] developers;

    private Bitmap background;
    private boolean isBackgroundResized = false;

    private Paint scorePaint;

    private int score = 0;

    public Level(int difficulty) {
        this(difficulty, new String[]{});
    }

    public Level(int difficulty, String[] developers) {
        this.level = difficulty;
        this.developers = developers;

        scorePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scorePaint.setTextSize(80);
        scorePaint.setColor(Color.WHITE);
    }

    protected final void setBackgroundImage(Bitmap background) {
        this.background = background;
    }

    protected final void setScore(int score) {
        this.score = score;
    }

    protected final void addScore(int score) {
        this.score += score;
    }

    protected final int getScore() {
        return score;
    }

    final void onTouchEvent(MotionEvent event) {

    }

    public void draw(Canvas canvas) {
        drawBackground(canvas);
        drawScore(canvas);
    }

    private void drawBackground(Canvas canvas) {
        if(background != null) {
            if(!isBackgroundResized) {
                background = Bitmap.createScaledBitmap(background, canvas.getWidth(), canvas.getHeight(), false);
                isBackgroundResized = true;
            }

            canvas.drawBitmap(background, 0, 0, null);
        }
    }

    private void drawScore(Canvas canvas) {
        canvas.drawText(String.valueOf(score), 20, 40, scorePaint);
        score++;
    }

    public abstract void startLevel();
}
