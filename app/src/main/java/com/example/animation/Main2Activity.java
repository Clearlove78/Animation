package com.example.animation;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends Activity {

    private GameView gameview;

    private TextView tv_level, tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.two);

        tv_level = (TextView) findViewById(R.id.tv_level);
        tv_time = (TextView) findViewById(R.id.tv_time);

        gameview = (GameView) findViewById(R.id.gameview);
        gameview.setTimeEnabled(true);

        gameview.setOnGamemListener(new GameView.GamePintuListener() {
            @Override
            public void nextLevel(final int nextLevel) {

                new AlertDialog.Builder(Main2Activity.this).setTitle("Done").setMessage("Congratulation").setPositiveButton("Next level", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameview.nextLevel();
                        tv_level.setText("Level" + nextLevel);
                    }
                }).show();
            }

            @Override
            public void timechanged(int time) {
                tv_time.setText("Remain time：" + time);
            }

            @Override
            public void gameOver() {
                new AlertDialog.Builder(Main2Activity.this).setTitle("game over").setMessage("Sorry").setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameview.restartGame();
                    }
                }).setNegativeButton("exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        gameview.pauseGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameview.resumeGame();
    }
}

