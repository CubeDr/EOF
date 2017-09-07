package kr.ac.hanyang.eos.eof.game;

import android.graphics.Canvas;

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

    public abstract void drawStage(Canvas canvas);
}
