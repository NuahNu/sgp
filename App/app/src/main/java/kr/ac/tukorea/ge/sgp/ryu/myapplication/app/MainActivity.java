package kr.ac.tukorea.ge.sgp.ryu.myapplication.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.tukorea.ge.sgp.ryu.myapplication.R;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.framework.GameView;
import kr.ac.tukorea.ge.sgp.ryu.myapplication.game.MainScene;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);

        new MainScene().pushScene();
    }
}