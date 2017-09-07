package kr.ac.hanyang.eos.eof.game.components;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by space on 2017-09-07.
 */

public abstract class Stage {
    public int stage;
    public String developer;

    public Stage(int stage, String developer) {
        this.stage = stage;
        this.developer = developer;
    }

    final void onTouchEvent(MotionEvent event) {

    }

    public abstract void drawStage(Canvas canvas);
}
