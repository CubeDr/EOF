package kr.ac.hanyang.eos.eof.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import kr.ac.hanyang.eos.eof.R;

/**
 * Created by space on 2017-09-06.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Context context;
    private SurfaceHolder holder;
    private GameThread thread;

    public GameView(Context context) {
        super(context);

        holder = getHolder();
        holder.addCallback(this);
        this.context = context;
        this.thread = new GameThread();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    class GameThread extends Thread {
        private Bitmap background;

        GameThread() {
            background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        }

        @Override
        public void run() {
            Canvas canvas;
            while(true) {
                canvas = holder.lockCanvas();

                try {
                    synchronized (holder) {
                        canvas.drawBitmap(background, 0, 0, null);
                    }
                } finally {
                    if(canvas != null) holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
