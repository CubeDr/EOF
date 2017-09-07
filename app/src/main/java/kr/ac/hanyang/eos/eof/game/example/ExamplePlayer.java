package kr.ac.hanyang.eos.eof.game.example;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import kr.ac.hanyang.eos.eof.R;
import kr.ac.hanyang.eos.eof.game.Engine;
import kr.ac.hanyang.eos.eof.game.components.Player;

/**
 * Created by space on 2017-09-07.
 */

public class ExamplePlayer extends Player {

    public ExamplePlayer() {
        super(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Engine.getResources(), R.drawable.explayer), 100, 100, false));
    }
}
