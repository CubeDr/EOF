package kr.ac.hanyang.eos.eof.game.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.hanyang.eos.eof.game.GameView;

/**
 * Created by space on 2017-09-07.
 */

public abstract class Level {
    private int level;
    private String[] developers;

    private Bitmap background;
    private boolean isBackgroundResized = false;

    private boolean started = false;

    private long startTime;
    private boolean isStarting = false;
    private Paint titlePaint;
    private Paint titleShadowPaint;

    int endX;
    int midX = -1;
    int startX = -1;
    int y = -1;

    private Paint scorePaint;
    private int score = 0;

    private ArrayList<Stage> stages;
    private Stage currentStage;

    public Level(int difficulty) {
        this(difficulty, new String[]{});
    }

    public Level(int difficulty, String[] developers) {
        this.level = difficulty;
        this.developers = developers;

        titlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        titlePaint.setTextSize(150);
        titlePaint.setColor(Color.GRAY);

        titleShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        titleShadowPaint.setTextSize(150);
        titleShadowPaint.setColor(Color.DKGRAY);

        endX = - (int) titlePaint.measureText("Level");

        scorePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scorePaint.setTextSize(80);
        scorePaint.setColor(Color.WHITE);

        stages = new ArrayList<>();
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

    protected final void addStage(Stage stage) {
        stages.add(stage);
        if(currentStage == null)
            currentStage = stage;
    }

    public final void onTouchEvent(MotionEvent event) {
        if(currentStage != null)
            currentStage.onTouchEvent(event);
    }

    public final void draw(Canvas canvas) {
        if(!started) startLevel();

        drawBackground(canvas);
        drawScore(canvas);
        if(isStarting) drawStartingAnimation(canvas);
        else {
            if(currentStage != null)
                currentStage.drawStage(canvas);
        }
    }

    private final void drawBackground(Canvas canvas) {
        if(background != null) {
            if(!isBackgroundResized) {
                background = Bitmap.createScaledBitmap(background, canvas.getWidth(), canvas.getHeight(), false);
                isBackgroundResized = true;
            }

            canvas.drawBitmap(background, 0, 0, null);
        }
    }

    private final void drawScore(Canvas canvas) {
        canvas.drawText(String.valueOf(score), 20, 80, scorePaint);
        score++;
    }

    private final void drawStartingAnimation(Canvas canvas) {
        if (!isStarting) return;

        int passed = (int) (System.currentTimeMillis() - startTime);
        if (passed >= 2000)
            isStarting = false;

        if (y == -1) {
            midX = canvas.getWidth() / 2 + endX / 2;
            startX = canvas.getWidth() - endX;
            y = canvas.getHeight() / 2;
        }

        int x;
        if (passed <= 1000)
            x = -1000;
        else if (passed <= 1100) {
            passed -= 1000;
        x = (startX * (100 - passed) + midX * passed) / 100;
        } else if( passed <= 1900 )
            x = midX;
        else {
            passed -= 1900;
            x = ( midX*(100-passed) + endX*passed ) / 100;
        }

        canvas.drawText("Level", x+5, y+5, titleShadowPaint);
        canvas.drawText("Level", x, y, titlePaint);
    }

    private final void startLevel() {
        started = true;
        startTime = System.currentTimeMillis();
        isStarting = true;
        onStart();
    }

    protected abstract void onStart();
}
