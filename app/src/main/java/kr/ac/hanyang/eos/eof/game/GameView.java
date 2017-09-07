package kr.ac.hanyang.eos.eof.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

import kr.ac.hanyang.eos.eof.game.components.Level;
import kr.ac.hanyang.eos.eof.game.example.ExampleLevel;

/**
 * Created by space on 2017-09-06.
 */

public class GameView extends SurfaceView implements View.OnTouchListener, SurfaceHolder.Callback {
    private Context context;
    private SurfaceHolder holder;
    private GameThread thread;

    private Level[] levels;
    int currentLevel;

    public GameView(Context context) {
        super(context);

        holder = getHolder();
        holder.addCallback(this);
        this.context = context;
        this.thread = new GameThread();

        levels = new Level[] {
            new ExampleLevel()
        };
        currentLevel = 0;

        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(levels != null && currentLevel>=0 && currentLevel < levels.length && levels[currentLevel]!=null)
            levels[currentLevel].onTouchEvent(event);
        return true;
    }

    @Override public void surfaceCreated(SurfaceHolder holder) {
        thread.start();
    }
    @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }
    @Override public void surfaceDestroyed(SurfaceHolder holder) { }

    class GameThread extends Thread {

        @Override
        public void run() {
            Canvas canvas;
            while(true) {
                canvas = holder.lockCanvas();

                try {
                    synchronized (holder) {
                        if(levels!=null && currentLevel >= 0 && currentLevel < levels.length && levels[currentLevel]!=null) {
                            levels[currentLevel].draw(canvas);
                        }
                    }
                } finally {
                    if(canvas != null) holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
