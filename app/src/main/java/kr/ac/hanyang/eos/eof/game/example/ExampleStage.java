package kr.ac.hanyang.eos.eof.game.example;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import kr.ac.hanyang.eos.eof.game.components.Stage;

/**
 * Created by space on 2017-09-07.
 */

public class ExampleStage extends Stage {

    public ExampleStage() {
        super(1, "김현이");
    }

    @Override
    public void drawStage(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setTextSize(200);

        canvas.drawText("STAGE!!", 100, 300, paint);
    }
}
