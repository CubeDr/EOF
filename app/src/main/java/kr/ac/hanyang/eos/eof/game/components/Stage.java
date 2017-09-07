package kr.ac.hanyang.eos.eof.game.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by space on 2017-09-07.
 */

public abstract class Stage {
    private int stage;
    private String developer;

    private boolean started = false;
    private long startTime;

    private Paint titlePaint;
    private int tX=-1, tW, tY;

    private Player player;

    public Stage(int stage, String developer) {
        this.stage = stage;
        this.developer = developer;

        titlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        titlePaint.setTextSize(80);
        titlePaint.setColor(Color.WHITE);
        tW = (int) titlePaint.measureText("Stage " + stage);
        tY = 80;
    }

    protected final void setPlayer(Player player) {
        this.player = player;
    }

    public final void drawStage(Canvas canvas) {
        if(!started) startStage();


        if(player != null) player.draw(canvas);

        if(tX == -1) tX = canvas.getWidth() - tW-10;
        canvas.drawText("Stage " + stage, tX, tY, titlePaint);

    }

    final void onTouchEvent(MotionEvent event) {
        if(player != null) player.onTouchEvent(event);
    }

    private final void startStage() {
        started = true;
        startTime = System.currentTimeMillis();
        onStart();
    }

    protected abstract void onStart();
}
