package kr.ac.hanyang.eos.eof.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.ac.hanyang.eos.eof.R;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(gameView = new GameView(this));
    }
}
