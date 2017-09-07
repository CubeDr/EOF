package kr.ac.hanyang.eos.eof.game.example;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import kr.ac.hanyang.eos.eof.R;
import kr.ac.hanyang.eos.eof.game.Engine;
import kr.ac.hanyang.eos.eof.game.components.Level;

/**
 * Created by space on 2017-09-07.
 */

public class ExampleLevel extends Level {

    public ExampleLevel() {
        super(0, new String[]{ "김현이" });
        setBackgroundImage(BitmapFactory.decodeResource(Engine.getResources(), R.drawable.background));
    }

    @Override
    public void startLevel() {

    }
}
