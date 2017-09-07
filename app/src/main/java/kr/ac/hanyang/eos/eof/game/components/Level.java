package kr.ac.hanyang.eos.eof.game.components;

import android.graphics.Canvas;

/**
 * Created by space on 2017-09-07.
 */

public abstract class Level {
    public int level;
    public String[] developers;


    public Level(int level) {
        this(level, new String[]{});
    }

    public Level(int level, String[] developers) {
        this.level = level;
        this.developers = developers;
    }

    public abstract void startLevel();
    public abstract void drawLevel(Canvas canvas);
}
